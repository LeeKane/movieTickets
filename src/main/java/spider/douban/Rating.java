package spider.douban;

/**
 * Created by wangpan on 2017/6/10.
 */
public class Rating {
    private int max;
    private int min;
    private double average;
    private int star;

    public double getAverage() {
        return average;
    }

    public int getMax() {
        return max;
    }

    public int getMin() {
        return min;
    }

    public int getStar() {
        return star;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setStar(int star) {
        this.star = star;
    }
}
