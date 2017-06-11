package bean;

import java.util.Date;

/**
 * Created by 50210 on 2017/6/10.
 */
public class Comment_Maoyan {
    private String id;
    private String userId;
    private String nickName;
    private Date time;
    private int approve;
    private String avatarurl;
    private String content;
    private double score;
    private int reply;
    private int oppose;
    private String movieId;

    public void setId(String id) {
        this.id = id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void setApprove(int approve) {
        this.approve = approve;
    }

    public void setAvatarurl(String avatarurl) {
        this.avatarurl = avatarurl;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public void setReply(int reply) {
        this.reply = reply;
    }

    public void setOppose(int oppose) {
        this.oppose = oppose;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getNickName() {
        return nickName;
    }

    public Date getTime() {
        return time;
    }

    public int getApprove() {
        return approve;
    }

    public String getAvatarurl() {
        return avatarurl;
    }

    public String getContent() {
        return content;
    }

    public double getScore() {
        return score;
    }

    public int getReply() {
        return reply;
    }

    public int getOppose() {
        return oppose;
    }

    public String getMovieId() {
        return movieId;
    }
}
