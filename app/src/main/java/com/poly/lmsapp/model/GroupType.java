package com.poly.lmsapp.model;

import java.util.List;

public class GroupType {
    private  String _id;
    private  String name;
    private  String description;
    private  List<Object> listClass;
    private  int idClass;
    private  String createAt;
//    private  int createBy;
    private  int id;
    private  int __v;

    public GroupType() {
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

    public List<Object> getListClass() {
        return listClass;
    }

    public int getIdClass() {
        return idClass;
    }

    public String getCreateAt() {
        return createAt;
    }

//    public int getCreateBy() {
//        return createBy;
//    }

    public int getId() {
        return id;
    }

    public int get__v() {
        return __v;
    }
}
