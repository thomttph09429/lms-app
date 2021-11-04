package com.poly.lmsapp.model;

public class MonHocModel {
    private String maMon;
    private String titLe;

    public MonHocModel() {
    }

    public MonHocModel(String maMon, String titLe) {
        this.maMon = maMon;
        this.titLe = titLe;
    }

    public String getMaMon() {
        return maMon;
    }

    public void setMaMon(String maMon) {
        this.maMon = maMon;
    }

    public String getTitLe() {
        return titLe;
    }

    public void setTitLe(String titLe) {
        this.titLe = titLe;
    }

}
