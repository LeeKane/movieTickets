package model.Douban;

/**
 * Created by wangpan on 2017/6/11.
 */
public class Comment_Douban {
    private String id;

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    private String movieId;
    private String date;
    private String authorId;
    private String content;
    private int useful;
    private int value;
    private String subject_id;

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthorId() {
        return authorId;
    }

    public int getUseful() {
        return useful;
    }

    public int getValue() {
        return value;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }

    public String getId() {
        return id;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setValue(int rating) {
        this.value = rating;
    }

    public void setUseful(int useful) {
        this.useful = useful;
    }

    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }
}
