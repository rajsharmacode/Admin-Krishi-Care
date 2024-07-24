package com.example.admin.RajModel;

public class UserModel {
    String proemail,proimage,proname,pronumber,prostate,userkey;

    public UserModel() {
    }

    public UserModel(String proemail, String proimage, String proname, String pronumber, String prostate, String userkey) {
        this.proemail = proemail;
        this.proimage = proimage;
        this.proname = proname;
        this.pronumber = pronumber;
        this.prostate = prostate;
        this.userkey = userkey;
    }

    public String getProemail() {
        return proemail;
    }

    public void setProemail(String proemail) {
        this.proemail = proemail;
    }

    public String getProimage() {
        return proimage;
    }

    public void setProimage(String proimage) {
        this.proimage = proimage;
    }

    public String getProname() {
        return proname;
    }

    public void setProname(String proname) {
        this.proname = proname;
    }

    public String getPronumber() {
        return pronumber;
    }

    public void setPronumber(String pronumber) {
        this.pronumber = pronumber;
    }

    public String getProstate() {
        return prostate;
    }

    public void setProstate(String prostate) {
        this.prostate = prostate;
    }

    public String getUserkey() {
        return userkey;
    }

    public void setUserkey(String userkey) {
        this.userkey = userkey;
    }
}
