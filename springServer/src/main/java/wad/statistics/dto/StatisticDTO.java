package wad.statistics.dto;

import lombok.Data;

@Data
public class StatisticDTO<X, Y> {

    private X xAxis;

    private Y yAxis;
}
