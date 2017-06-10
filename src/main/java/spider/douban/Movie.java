package spider.douban;

import java.util.List;

/**
 * Created by wangpan on 2017/6/10.
 */
public class Movie {
    private Rating rating;
    private int reviews_count;
    private int wish_count;
    private String countries;
    private String genres;
    private int collect_count;
    private String summary;
    private int comments_count;
    private int ratings_count;
    private String aka;
    private String title;
    private List<Cast> casts;
    private String original_title;
    private String subtype;
    private List<Cast> directors;
    private String year;
    private Image images;
    private String alt;
    private String id;

    public Movie(){
        super();
    }

    public String getAka() {
        return aka;
    }

    public List<Cast> getCasts() {
        return casts;
    }

    public String getCountries() {
        return countries;
    }

    public String getGenres() {
        return genres;
    }

    public List<Cast> getDirectors() {
        return directors;
    }

    public Image getImages() {
        return images;
    }

    public int getCollect_count() {
        return collect_count;
    }

    public int getComments_count() {
        return comments_count;
    }

    public int getReviews_count() {
        return reviews_count;
    }

    public int getRatings_count() {
        return ratings_count;
    }

    public int getWish_count() {
        return wish_count;
    }

    public Rating getRating() {
        return rating;
    }

    public String getId() {
        return id;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getSubtype() {
        return subtype;
    }

    public String getSummary() {
        return summary;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getAlt() {
        return alt;
    }

    public void setCountries(String countries) {
        this.countries = countries;
    }

    public void setAka(String aka) {
        this.aka = aka;
    }

    public void setCasts(List<Cast> casts) {
        this.casts = casts;
    }

    public void setCollect_count(int collect_count) {
        this.collect_count = collect_count;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public void setDirectors(List<Cast> directors) {
        this.directors = directors;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setImages(Image images) {
        this.images = images;
    }

    public void setRatings_count(int ratings_count) {
        this.ratings_count = ratings_count;
    }

    public void setReviews_count(int reviews_count) {
        this.reviews_count = reviews_count;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public void setWish_count(int wish_count) {
        this.wish_count = wish_count;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
