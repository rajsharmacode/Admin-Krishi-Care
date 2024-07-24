package com.example.admin.RajModel;

public class OrderStatusModel {

    String catkey,itemdisc,itemid,itemimg,itemname,itemprice,itemqnt,orderid,orderstats,ordertime,userid;

    public OrderStatusModel() {
    }

    public OrderStatusModel(String catkey, String itemdisc, String itemid, String itemimg, String itemname, String itemprice, String itemqnt, String orderid, String orderstats, String ordertime, String userid) {
        this.catkey = catkey;
        this.itemdisc = itemdisc;
        this.itemid = itemid;
        this.itemimg = itemimg;
        this.itemname = itemname;
        this.itemprice = itemprice;
        this.itemqnt = itemqnt;
        this.orderid = orderid;
        this.orderstats = orderstats;
        this.ordertime = ordertime;
        this.userid = userid;
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

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getOrderstats() {
        return orderstats;
    }

    public void setOrderstats(String orderstats) {
        this.orderstats = orderstats;
    }

    public String getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(String ordertime) {
        this.ordertime = ordertime;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
