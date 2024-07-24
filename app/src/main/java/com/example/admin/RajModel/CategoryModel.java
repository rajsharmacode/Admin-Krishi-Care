package com.example.admin.RajModel;

public class CategoryModel {
    String catimage,catname,userkey;

    public CategoryModel() {
    }

    public CategoryModel(String catimage, String catname, String userkey) {
        this.catimage = catimage;
        this.catname = catname;
        this.userkey = userkey;
    }

    public String getCatimage() {
        return catimage;
    }

    public void setCatimage(String catimage) {
        this.catimage = catimage;
    }

    public String getCatname() {
        return catname;
    }

    public void setCatname(String catname) {
        this.catname = catname;
    }

    public String getUserkey() {
        return userkey;
    }

    public void setUserkey(String userkey) {
        this.userkey = userkey;
    }
}
