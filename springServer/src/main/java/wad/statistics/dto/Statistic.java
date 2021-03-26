package wad.statistics.dto;

import lombok.Data;

@Data
public class Statistic<X, Y> {

    private X xAxis;

    private Y yAxis;
}
