package com.poly.lmsapp.model;


import android.os.Parcel;
import android.os.Parcelable;

public class Repository implements Parcelable {

        private String _id;
        private String title;
        private String image;
        private int id;

    protected Repository(Parcel in) {
        _id = in.readString();
        title = in.readString();
        image = in.readString();
        id = in.readInt();
    }

    public static final Creator<Repository> CREATOR = new Creator<Repository>() {
        @Override
        public Repository createFromParcel(Parcel in) {
            return new Repository(in);
        }

        @Override
        public Repository[] newArray(int size) {
            return new Repository[size];
        }
    };

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(_id);
        parcel.writeString(title);
        parcel.writeString(image);
        parcel.writeInt(id);
    }
}
