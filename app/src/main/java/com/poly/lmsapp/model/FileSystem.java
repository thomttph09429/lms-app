package com.poly.lmsapp.model;

import java.util.List;

public class FileSystem {
   private String _id;
   private String name;
   private String description;
   private int idClass;
   private int idSubject;
   private List<Object> listClass;
   private String linkFile;
   private String createAt;
   private int createBy;
   private int id;
   private int __v;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdClass() {
        return idClass;
    }

    public void setIdClass(int idClass) {
        this.idClass = idClass;
    }

    public int getIdSubject() {
        return idSubject;
    }

    public void setIdSubject(int idSubject) {
        this.idSubject = idSubject;
    }

    public List<Object> getListClass() {
        return listClass;
    }

    public void setListClass(List<Object> listClass) {
        this.listClass = listClass;
    }

    public String getLinkFile() {
        return linkFile;
    }

    public void setLinkFile(String linkFile) {
        this.linkFile = linkFile;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public int getCreateBy() {
        return createBy;
    }

    public void setCreateBy(int createBy) {
        this.createBy = createBy;
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
