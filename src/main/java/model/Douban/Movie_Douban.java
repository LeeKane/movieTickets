package model.Douban;

/**
 * Created by wangpan on 2017/6/10.
 */
public class Movie_Douban {
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
    private String casts; //castid列表 id1,id2....
    private String original_title;
    private String subtype;
    private String directors; //同cast
    private String year;
    private String images_large;
    private String images_middle;
    private String images_small;
    private String alt;
    private String id;

    public void setReviews_count(int reviews_count) {
        this.reviews_count = reviews_count;
    }

    public void setWish_count(int wish_count) {
        this.wish_count = wish_count;
    }

    public void setCountries(String countries) {
        this.countries = countries;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public void setCollect_count(int collect_count) {
        this.collect_count = collect_count;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }

    public void setRatings_count(int ratings_count) {
        this.ratings_count = ratings_count;
    }

    public void setAka(String aka) {
        this.aka = aka;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCasts(String casts) {
        this.casts = casts;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public void setDirectors(String directors) {
        this.directors = directors;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setImages_large(String images_large) {
        this.images_large = images_large;
    }

    public void setImages_middle(String images_middle) {
        this.images_middle = images_middle;
    }

    public void setImages_small(String images_small) {
        this.images_small = images_small;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getReviews_count() {
        return reviews_count;
    }

    public int getWish_count() {
        return wish_count;
    }

    public String getCountries() {
        return countries;
    }

    public String getGenres() {
        return genres;
    }

    public int getCollect_count() {
        return collect_count;
    }

    public String getSummary() {
        return summary;
    }

    public int getComments_count() {
        return comments_count;
    }

    public int getRatings_count() {
        return ratings_count;
    }

    public String getAka() {
        return aka;
    }

    public String getTitle() {
        return title;
    }

    public String getCasts() {
        return casts;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getSubtype() {
        return subtype;
    }

    public String getDirectors() {
        return directors;
    }

    public String getYear() {
        return year;
    }

    public String getImages_large() {
        return images_large;
    }

    public String getImages_middle() {
        return images_middle;
    }

    public String getImages_small() {
        return images_small;
    }

    public String getAlt() {
        return alt;
    }

    public String getId() {
        return id;
    }
}
