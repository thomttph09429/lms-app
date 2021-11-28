package com.poly.lmsapp.model;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private ArrayList<Answer> listCauTraLoiObject;
    private String _id;
    private String content;
    private int idQuiz;
    private String createdAt;
    private int createdBy;
    private int idDapAp;
    private int id;
    private int __v;

    public ArrayList<Answer> getListCauTraLoi() {
        return listCauTraLoiObject;
    }

    public void setListCauTraLoi(ArrayList<Answer> listCauTraLoiObject) {
        this.listCauTraLoiObject = listCauTraLoiObject;
    }

    public ArrayList<Answer> getListCauTraLoiObject() {
        return listCauTraLoiObject;
    }

    public void setListCauTraLoiObject(ArrayList<Answer> listCauTraLoiObject) {
        this.listCauTraLoiObject = listCauTraLoiObject;
    }

    public int getIdDapAp() {
        return idDapAp;
    }

    public void setIdDapAp(int idDapAp) {
        this.idDapAp = idDapAp;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getIdQuiz() {
        return idQuiz;
    }

    public void setIdQuiz(int idQuiz) {
        this.idQuiz = idQuiz;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }
}
