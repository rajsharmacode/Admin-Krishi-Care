package com.example.admin.RajModel;

public class ItemoModel {
    String catkey,itemdisc,itemid,itemimg,itemname,itemprice,itemqnt;

    public ItemoModel() {
    }

    public ItemoModel(String catkey, String itemdisc, String itemid, String itemimg, String itemname, String itemprice, String itemqnt) {
        this.catkey = catkey;
        this.itemdisc = itemdisc;
        this.itemid = itemid;
        this.itemimg = itemimg;
        this.itemname = itemname;
        this.itemprice = itemprice;
        this.itemqnt = itemqnt;
    }

    public String getCatkey() {
        return catkey;
    }

    public void setCatkey(String catkey) {
        this.catkey = catkey;
    }

    public String getItemdisc() {
        return itemdisc;
    }

    public void setItemdisc(String itemdisc) {
        this.itemdisc = itemdisc;
    }

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    public String getItemimg() {
        return itemimg;
    }

    public void setItemimg(String itemimg) {
        this.itemimg = itemimg;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getItemprice() {
        return itemprice;
    }

    public void setItemprice(String itemprice) {
        this.itemprice = itemprice;
    }

    public String getItemqnt() {
        return itemqnt;
    }

    public void setItemqnt(String itemqnt) {
        this.itemqnt = itemqnt;
    }
}
