package spider.douban;

/**
 * Created by wangpan on 2017/6/11.
 */
public class Author {
    private String id;
    private String uid;
    private String name;
    private String signature;
    private String alt;
    private String avatar;

    public String getAlt() {
        return alt;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSignature() {
        return signature;
    }

    public String getUid() {
        return uid;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
