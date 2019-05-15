package wad.statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wad.statistics.dto.Statistic;

import java.util.List;

@RestController
@RequestMapping("/statistics/")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;


    @GetMapping("monthly-sales")
    public List<Statistic<String, Double>> getMonthlySales() throws Exception {
        return statisticsService.getMonthlySales();
    }


}
