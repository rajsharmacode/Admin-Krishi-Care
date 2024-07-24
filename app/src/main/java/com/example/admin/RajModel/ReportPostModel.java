package com.example.admin.RajModel;

public class ReportPostModel {

    String commdisc,commimage,commques,postid,uid;

    public ReportPostModel() {
    }

    public ReportPostModel(String commdisc, String commimage, String commques, String postid, String uid) {
        this.commdisc = commdisc;
        this.commimage = commimage;
        this.commques = commques;
        this.postid = postid;
        this.uid = uid;
    }

    public String getCommdisc() {
        return commdisc;
    }

    public void setCommdisc(String commdisc) {
        this.commdisc = commdisc;
    }

    public String getCommimage() {
        return commimage;
    }

    public void setCommimage(String commimage) {
        this.commimage = commimage;
    }

    public String getCommques() {
        return commques;
    }

    public void setCommques(String commques) {
        this.commques = commques;
    }

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
