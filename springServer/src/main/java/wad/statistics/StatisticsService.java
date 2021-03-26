package wad.statistics;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wad.statistics.dto.Statistic;
import wad.user.entities.Purchase;
import wad.user.repositories.PurchaseRepository;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.function.ToIntFunction;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@Service
public class StatisticsService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    public List<Statistic<String, Double>> getMonthlySales() throws Exception {
        return IterableUtils.toList(purchaseRepository.findAll())
                .stream()
                .map(this::statisticFromPurchase)
                .collect(groupingBy(Statistic::getXAxis))
                .entrySet().stream()
                .map(this::statisticFromEntry)
                .sorted(comparingInt(determineMonthOfStatistic()))
                .collect(toList());

    }

    private ToIntFunction<Statistic<String, Double>> determineMonthOfStatistic() {
        return a -> Month.valueOf(a.getXAxis().toUpperCase()).getValue();
    }

    private Statistic<String, Double> statisticFromPurchase(Purchase p) {
        var s = new Statistic<String, Double>();
        s.setXAxis(monthNameOfPurchase(p));
        s.setYAxis(p.getPrice());

        return s;
    }

    private String monthNameOfPurchase(Purchase p) {
        return LocalDate.ofInstant(p.getPurchaseDate().toInstant(), ZoneId.systemDefault())
                .getMonth()
                .getDisplayName(TextStyle.FULL, Locale.ENGLISH);
    }

    private Statistic<String, Double> statisticFromEntry(Entry<String, List<Statistic<String, Double>>> entry) {
        var s = new Statistic<String, Double>();
        s.setXAxis(entry.getKey());
        s.setYAxis(yAxisSumOfStatistics(entry.getValue()));
        return s;
    }

    private Double yAxisSumOfStatistics(List<Statistic<String, Double>> statistics) {
        return statistics.stream().map(Statistic::getYAxis).reduce(Double::sum).get();
    }
}
