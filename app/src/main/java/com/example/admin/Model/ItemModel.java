package com.example.admin.Model;

import android.widget.TextView;

public class ItemModel {
    public ItemModel() {
    }

    String Tittle;

    public ItemModel(String tittle) {
        Tittle = tittle;
    }

    public String getTittle() {
        return Tittle;
    }

    public void setTittle(String tittle) {
        Tittle = tittle;
    }
}
