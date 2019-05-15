package wad.statistics.dto;

public class Statistic<X, Y> {

    private X xAxis;
    private Y yAxis;

    public X getxAxis() {
        return xAxis;
    }

    public void setxAxis(X xAxis) {
        this.xAxis = xAxis;
    }

    public Y getyAxis() {
        return yAxis;
    }

    public void setyAxis(Y yAxis) {
        this.yAxis = yAxis;
    }
}
