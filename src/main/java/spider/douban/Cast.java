package spider.douban;

/**
 * Created by wangpan on 2017/6/10.
 */
public class Cast {
    private String alt;
    private Image avatars;
    private String name;
    private String id;

    public Image getAvatars() {
        return avatars;
    }

    public String getAlt() {
        return alt;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public void setAvatars(Image avatars) {
        this.avatars = avatars;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
