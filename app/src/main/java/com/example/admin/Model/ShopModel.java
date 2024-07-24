package com.example.admin.Model;

public class ShopModel{
    String P_desc,P_name,P_price,P_url;

    public ShopModel() {

    }

    public ShopModel(String p_desc, String p_name, String p_price, String p_url) {
        P_desc = p_desc;
        P_name = p_name;
        P_price = p_price;
        P_url = p_url;
    }

    public String getP_desc() {
        return P_desc;
    }

    public void setP_desc(String p_desc) {
        P_desc = p_desc;
    }

    public String getP_name() {
        return P_name;
    }

    public void setP_name(String p_name) {
        P_name = p_name;
    }

    public String getP_price() {
        return P_price;
    }

    public void setP_price(String p_price) {
        P_price = p_price;
    }

    public String getP_url() {
        return P_url;
    }

    public void setP_url(String p_url) {
        P_url = p_url;
    }
}
