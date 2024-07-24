package com.example.admin.RajModel;

public class CommentModel {

    String commentuser,personid;

    public CommentModel() {
    }

    public CommentModel(String commentuser, String personid) {
        this.commentuser = commentuser;
        this.personid = personid;
    }

    public String getCommentuser() {
        return commentuser;
    }

    public void setCommentuser(String commentuser) {
        this.commentuser = commentuser;
    }

    public String getPersonid() {
        return personid;
    }

    public void setPersonid(String personid) {
        this.personid = personid;
    }
}
