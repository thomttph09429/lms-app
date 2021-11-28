package com.poly.lmsapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Subject implements Parcelable {
    private String name;
    private int idDepartment;
    private String description;
    private String createdAt;
//    private int createdBy;
    private int id;

    protected Subject(Parcel in) {
        name = in.readString();
        idDepartment = in.readInt();
        description = in.readString();
        createdAt = in.readString();
        id = in.readInt();
    }

    public static final Creator<Subject> CREATOR = new Creator<Subject>() {
        @Override
        public Subject createFromParcel(Parcel in) {
            return new Subject(in);
        }

        @Override
        public Subject[] newArray(int size) {
            return new Subject[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(int idDepartment) {
        this.idDepartment = idDepartment;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

//    public int getCreatedBy() {
//        return createdBy;
//    }

//    public void setCreatedBy(int createdBy) {
//        this.createdBy = createdBy;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(idDepartment);
        parcel.writeString(description);
        parcel.writeString(createdAt);
        parcel.writeInt(id);
    }
}
