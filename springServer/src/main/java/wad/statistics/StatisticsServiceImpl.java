package wad.statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wad.statistics.dto.Statistic;
import wad.user.entities.Purchase;
import wad.user.entities.PurchaseRepository;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.StreamSupport;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Override
    public List<Statistic<String, Double>> getMonthlySales() throws Exception {
        return StreamSupport.stream(purchaseRepository.findAll().spliterator(), false)
                .map(this::statisticFromPurchase)
                .collect(groupingBy(Statistic::getxAxis))
                .entrySet().stream()
                .map(this::statisticFromEntry)
                .sorted(comparingInt(a -> Month.valueOf(a.getxAxis().toUpperCase()).getValue()))
                .collect(toList());

    }

    private Statistic<String, Double> statisticFromPurchase(Purchase p) {
        var s = new Statistic<String, Double>();

        s.setxAxis(LocalDate.ofInstant(p.getPurchaseDate().toInstant(), ZoneId.systemDefault())
                            .getMonth()
                            .getDisplayName(TextStyle.FULL, Locale.ENGLISH));
        s.setyAxis(p.getPrice());

        return s;
    }

    private Statistic<String, Double> statisticFromEntry(Map.Entry<String, List<Statistic<String, Double>>> entry) {
        var s = new Statistic<String, Double>();
        s.setxAxis(entry.getKey());
        s.setyAxis(entry.getValue().stream().map(Statistic::getyAxis).reduce(Double::sum).get());
        return s;
    }
}
