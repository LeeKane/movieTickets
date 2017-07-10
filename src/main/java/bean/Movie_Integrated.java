package bean;

import java.util.List;

/**
 * Created by USERZHANG on 2017/7/9.
 */
public class Movie_Integrated {
    private  String id;
    private String name;
    private String showinfo;
    private double scoredouban;
    private double scoremaoyan;
    private double scoremtime;
    private String scm;
    private String ver;
    private String showdate;
    private String description;
    private String dir;
    private String star;
    private String cat;
    private String img;
    private int dur;
    private List<Comment_Integrated> comments;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getScm() {
        return scm;
    }

    public void setScm(String scm) {
        this.scm = scm;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShowinfo() {
        return showinfo;
    }

    public void setShowinfo(String showinfo) {
        this.showinfo = showinfo;
    }

    public double getScoredouban() {
        return scoredouban;
    }

    public void setScoredouban(double scoredouban) {
        this.scoredouban = scoredouban;
    }

    public double getScoremaoyan() {
        return scoremaoyan;
    }

    public void setScoremaoyan(double scoremaoyan) {
        this.scoremaoyan = scoremaoyan;
    }

    public double getScoremtime() {
        return scoremtime;
    }

    public void setScoremtime(double scoremtime) {
        this.scoremtime = scoremtime;
    }

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public String getShowdate() {
        return showdate;
    }

    public void setShowdate(String showdate) {
        this.showdate = showdate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getDur() {
        return dur;
    }

    public void setDur(int dur) {
        this.dur = dur;
    }

    public List<Comment_Integrated> getComments() {
        return comments;
    }

    public void setComments(List<Comment_Integrated> comments) {
        this.comments = comments;
    }
}
