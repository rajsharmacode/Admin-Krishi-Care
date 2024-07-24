package com.example.admin.RajModel;

public class CultivationModel {

    String calkey,caltdisc,caltimg,caltname;

    public CultivationModel() {
    }

    public CultivationModel(String calkey, String caltdisc, String caltimg, String caltname) {
        this.calkey = calkey;
        this.caltdisc = caltdisc;
        this.caltimg = caltimg;
        this.caltname = caltname;
    }

    public String getCalkey() {
        return calkey;
    }

    public void setCalkey(String calkey) {
        this.calkey = calkey;
    }

    public String getCaltdisc() {
        return caltdisc;
    }

    public void setCaltdisc(String caltdisc) {
        this.caltdisc = caltdisc;
    }

    public String getCaltimg() {
        return caltimg;
    }

    public void setCaltimg(String caltimg) {
        this.caltimg = caltimg;
    }

    public String getCaltname() {
        return caltname;
    }

    public void setCaltname(String caltname) {
        this.caltname = caltname;
    }
}
