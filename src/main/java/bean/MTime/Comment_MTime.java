package bean.MTime;

import java.util.Date;

/**
 * Created by 50210 on 2017/6/12.
 */
public class Comment_MTime {
    private String nickName;
    private String avtarurl;
    private Date time;
    private String content;
    private int approve;
    private int reply;
    private String id;
    private String mTimeId;

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setAvtarurl(String avtarurl) {
        this.avtarurl = avtarurl;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setApprove(int approve) {
        this.approve = approve;
    }

    public void setReply(int reply) {
        this.reply = reply;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setmTimeId(String mTimeId) {
        this.mTimeId = mTimeId;
    }

    public String getNickName() {
        return nickName;
    }

    public String getAvtarurl() {
        return avtarurl;
    }

    public Date getTime() {
        return time;
    }

    public String getContent() {
        return content;
    }

    public int getApprove() {
        return approve;
    }

    public int getReply() {
        return reply;
    }

    public String getId() {
        return id;
    }

    public String getmTimeId() {
        return mTimeId;
    }
}
