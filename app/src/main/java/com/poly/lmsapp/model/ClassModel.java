package com.poly.lmsapp.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class ClassModel implements Parcelable {
    private String _id;
    private String name;
    private String description;
    private int idSubject;
    private String createAt;
    @SerializedName("giangVien")
    private User teacher;
    private User createBy;
    private int id;
    private int __v;

    public ClassModel() {
    }

    protected ClassModel(Parcel in) {
        _id = in.readString();
        name = in.readString();
        description = in.readString();
        idSubject = in.readInt();
        createAt = in.readString();
        createBy = in.readParcelable(User.class.getClassLoader());
        id = in.readInt();
        __v = in.readInt();
    }

    public static final Creator<ClassModel> CREATOR = new Creator<ClassModel>() {
        @Override
        public ClassModel createFromParcel(Parcel in) {
            return new ClassModel(in);
        }

        @Override
        public ClassModel[] newArray(int size) {
            return new ClassModel[size];
        }
    };

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

    public int getIdSubject() {
        return idSubject;
    }

    public void setIdSubject(int idSubject) {
        this.idSubject = idSubject;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public User getCreateBy() {
        return createBy;
    }

    public void setCreateBy(User createBy) {
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

    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(_id);
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeInt(idSubject);
        parcel.writeString(createAt);
        parcel.writeParcelable(createBy, i);
        parcel.writeInt(id);
        parcel.writeInt(__v);
    }
}
