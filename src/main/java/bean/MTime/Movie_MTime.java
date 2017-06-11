package bean.MTime;

import java.util.Date;

/**
 * Created by 50210 on 2017/6/11.
 */
public class Movie_MTime {
    private String movieTitle;
    private double ratingFinal;
    private int Usercount;
    private double totalBoxOffice;
    private String totalBoxOfficeUnit;
    private double todayBoxOffice;
    private String todayBoxOfficeUnit;
    private int showDays;
    private Date endDate;
    private boolean isRelease;
    private String id;
    private String maoyanId;

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public void setRatingFinal(double ratingFinal) {
        this.ratingFinal = ratingFinal;
    }

    public void setUsercount(int usercount) {
        Usercount = usercount;
    }

    public void setTotalBoxOffice(double totalBoxOffice) {
        this.totalBoxOffice = totalBoxOffice;
    }

    public void setTotalBoxOfficeUnit(String totalBoxOfficeUnit) {
        this.totalBoxOfficeUnit = totalBoxOfficeUnit;
    }

    public void setTodayBoxOffice(double todayBoxOffice) {
        this.todayBoxOffice = todayBoxOffice;
    }

    public void setTodayBoxOfficeUnit(String todayBoxOfficeUnit) {
        this.todayBoxOfficeUnit = todayBoxOfficeUnit;
    }

    public void setShowDays(int showDays) {
        this.showDays = showDays;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setRelease(boolean release) {
        isRelease = release;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMaoyanId(String maoyanId) {
        this.maoyanId = maoyanId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public double getRatingFinal() {
        return ratingFinal;
    }

    public int getUsercount() {
        return Usercount;
    }

    public double getTotalBoxOffice() {
        return totalBoxOffice;
    }

    public String getTotalBoxOfficeUnit() {
        return totalBoxOfficeUnit;
    }

    public double getTodayBoxOffice() {
        return todayBoxOffice;
    }

    public String getTodayBoxOfficeUnit() {
        return todayBoxOfficeUnit;
    }

    public int getShowDays() {
        return showDays;
    }

    public Date getEndDate() {
        return endDate;
    }

    public boolean isRelease() {
        return isRelease;
    }

    public String getId() {
        return id;
    }

    public String getMaoyanId() {
        return maoyanId;
    }
}
