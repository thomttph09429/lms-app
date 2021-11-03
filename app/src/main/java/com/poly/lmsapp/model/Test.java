package com.poly.lmsapp.model;

public class Test {
    private String ip;

    public Test(String text) {
        this.ip = text;
    }

    public String getText() {
        return ip;
    }

    public void setText(String text) {
        this.ip = text;
    }
}
