package wad.statistics;

import wad.statistics.dto.Statistic;

import java.util.List;

public interface StatisticsService {

    List<Statistic<String, Double>> getMonthlySales() throws Exception;
}
