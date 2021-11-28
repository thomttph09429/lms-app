package com.poly.lmsapp.model;

import java.util.List;

public class DocumentType {
    public List<Object> listQuestion;
    public String _id;
    public String name;
    public String description;
    public String startTime;
    public String type;
    public String endTime;
    public int idGroupType;
    public String createdAt;
    public int createdBy;
    public int id;
    public int __v;
    public String updatedAt;
    public int updatedBy;

    public List<Object> getListQuestion() {
        return listQuestion;
    }

    public String get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public int getIdGroupType() {
        return idGroupType;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public int getId() {
        return id;
    }

    public int get__v() {
        return __v;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public int getUpdatedBy() {
        return updatedBy;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
