package com.example.admin.Model;

public class Model {
    String Tittle;
    String purl;



    public Model() {

    }

    public Model(String categoryName, String purl) {
        Tittle = categoryName;
        this.purl = purl;
    }


    public String getTittle() {
        return Tittle;
    }

    public void setTittle(String tittle) {
        Tittle = tittle;
    }

    public String getPurl() {
        return purl;
    }

    public void setPurl(String purl) {
        this.purl = purl;
    }
}
