package wad.statistics;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wad.phone.entity.Phone;
import wad.phone.repository.PhonesRepository;
import wad.purchase.entity.Purchase;
import wad.purchase.repository.PurchaseRepository;
import wad.statistics.dto.StatisticDTO;

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

    @Autowired
    private PhonesRepository phonesRepository;

    public List<StatisticDTO<String, Double>> getMonthlySales() {
        return IterableUtils.toList(purchaseRepository.findAll())
                .stream()
                .map(this::statisticFromPurchase)
                .collect(groupingBy(StatisticDTO::getXAxis))
                .entrySet().stream()
                .map(this::statisticFromEntry)
                .sorted(comparingInt(determineMonthOfStatistic()))
                .collect(toList());

    }

    private ToIntFunction<StatisticDTO<String, Double>> determineMonthOfStatistic() {
        return a -> Month.valueOf(a.getXAxis().toUpperCase()).getValue();
    }

    private StatisticDTO<String, Double> statisticFromPurchase(Purchase p) {
        var s = new StatisticDTO<String, Double>();
        s.setXAxis(monthNameOfPurchase(p));

        Phone purchasedPhone = phonesRepository.findById(p.getPhoneId()).orElseThrow();
        s.setYAxis(purchasedPhone.getPrice());

        return s;
    }

    private String monthNameOfPurchase(Purchase p) {
        return LocalDate.ofInstant(p.getPurchaseDate().toInstant(), ZoneId.systemDefault())
                .getMonth()
                .getDisplayName(TextStyle.FULL, Locale.ENGLISH);
    }

    private StatisticDTO<String, Double> statisticFromEntry(Entry<String, List<StatisticDTO<String, Double>>> entry) {
        var s = new StatisticDTO<String, Double>();
        s.setXAxis(entry.getKey());
        s.setYAxis(yAxisSumOfStatistics(entry.getValue()));
        return s;
    }

    private Double yAxisSumOfStatistics(List<StatisticDTO<String, Double>> statisticDTOs) {
        return statisticDTOs.stream().map(StatisticDTO::getYAxis).reduce(Double::sum).get();
    }
}
