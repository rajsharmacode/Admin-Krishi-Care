package com.example.admin.RajModel;

public class CropPracticesModel {

    String readdata,readimg,readkey,readname;

    public CropPracticesModel() {
    }

    public CropPracticesModel(String readdata, String readimg, String readkey, String readname) {
        this.readdata = readdata;
        this.readimg = readimg;
        this.readkey = readkey;
        this.readname = readname;
    }

    public String getReaddata() {
        return readdata;
    }

    public void setReaddata(String readdata) {
        this.readdata = readdata;
    }

    public String getReadimg() {
        return readimg;
    }

    public void setReadimg(String readimg) {
        this.readimg = readimg;
    }

    public String getReadkey() {
        return readkey;
    }

    public void setReadkey(String readkey) {
        this.readkey = readkey;
    }

    public String getReadname() {
        return readname;
    }

    public void setReadname(String readname) {
        this.readname = readname;
    }
}
