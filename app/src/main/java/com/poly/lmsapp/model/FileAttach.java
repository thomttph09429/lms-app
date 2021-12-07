package com.poly.lmsapp.model;

import com.poly.lmsapp.commons.utils.DateTimeUtils;

public class FileAttach {
    private String _id;
    private String name;
    private String link;
    private int idUser;
    private int idDocument;
    private int idDocumentType;
    private int idSubject;
    private int idClass;
    private String data;
    private String createdAt;
    private String note;
    private int createdBy;
    private int point;
    private int id;
    private int __v;

    public FileAttach(int id) {
        this.id = id;
    }

    public FileAttach(String name, int idDocumentType, int idSubject, int idClass, String data) {
        this.name = name;
        this.idDocumentType = idDocumentType;
        this.idSubject = idSubject;
        this.idClass = idClass;
        this.data = data;
    }



    public int getIdDocumentType() {
        return idDocumentType;
    }

    public void setIdDocumentType(int idDocumentType) {
        this.idDocumentType = idDocumentType;
    }

    public int getIdSubject() {
        return idSubject;
    }

    public void setIdSubject(int idSubject) {
        this.idSubject = idSubject;
    }

    public int getIdClass() {
        return idClass;
    }

    public void setIdClass(int idClass) {
        this.idClass = idClass;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdDocument() {
        return idDocument;
    }

    public void setIdDocument(int idDocument) {
        this.idDocument = idDocument;
    }

    public String getCreatedAt() {
        if(createdAt != null){
            createdAt = DateTimeUtils.toDateFormat(createdAt,DateTimeUtils.SERVER_DATE,DateTimeUtils.TIME_DATE);
        }
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
