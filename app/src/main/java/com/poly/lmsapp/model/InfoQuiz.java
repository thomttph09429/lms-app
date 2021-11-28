package com.poly.lmsapp.model;

public class InfoQuiz {
    private String _id;
    private int idDocumentType;
    private int idUser;
    private String startTime;
    private String endTime;
    private int id;
    private int point;
    private int __v;

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public InfoQuiz(int idDocumentType, int point) {
        this.idDocumentType = idDocumentType;
        this.point = point;
    }

    public InfoQuiz(int idDocumentType) {
        this.idDocumentType = idDocumentType;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public int getIdDocumentType() {
        return idDocumentType;
    }

    public void setIdDocumentType(int idDocumentType) {
        this.idDocumentType = idDocumentType;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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
