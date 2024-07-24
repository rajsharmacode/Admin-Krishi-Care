package com.example.admin.Model;

public class CommunityModel {

    String Content;
    String ImgURL;
    String TimeDate;

    public CommunityModel() {

    }

    public CommunityModel(String content, String imgURL, String timeDate) {
        Content = content;
        ImgURL = imgURL;
        TimeDate = timeDate;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getImgURL() {
        return ImgURL;
    }

    public void setImgURL(String imgURL) {
        ImgURL = imgURL;
    }

    public String getTimeDate() {
        return TimeDate;
    }

    public void setTimeDate(String timeDate) {
        TimeDate = timeDate;
    }
}
