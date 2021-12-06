package com.poly.lmsapp.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class User implements Parcelable {

    @SerializedName("_id")
    private String _id;
    @SerializedName("name")
    private String name;
    @SerializedName("userName")
    private String userName;
    @SerializedName("password")
    private String password;
    @SerializedName("email")
    private String email;
    @SerializedName("avatar")
    private String avatar;
    @SerializedName("birth")
    private String birth;
    @SerializedName("phoneNumber")
    private String phoneNumber;
//    @SerializedName("chuyenNganh")
//    private String chuyenNganh;
    @SerializedName("oldPassword")
    private String oldPassword;
    @SerializedName("newPassword")
    private String newPassword;
//    @SerializedName("kiHoc")
//    private String kiHoc;
    @SerializedName("permission")
    private List<Permission> permission;
    private String fcmToken;
    private int idGroup;

    public User(String oldPassword, String newPassword,int idGroup) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.idGroup = idGroup;
    }

//    public String getChuyenNganh() {
//        return chuyenNganh;
//    }

//    public void setChuyenNganh(String chuyenNganh) {
//        this.chuyenNganh = chuyenNganh;
//    }

//    public String getKiHoc() {
//        return kiHoc;
//    }

//    public void setKiHoc(String kiHoc) {
//        this.kiHoc = kiHoc;
//    }


    protected User(Parcel in) {
        _id = in.readString();
        name = in.readString();
        userName = in.readString();
        password = in.readString();
        email = in.readString();
        avatar = in.readString();
        birth = in.readString();
        phoneNumber = in.readString();
        oldPassword = in.readString();
        newPassword = in.readString();
        token = in.readString();
        id = in.readInt();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    private String token;
    private int id;

    public User() {
    }

    public User(String userName, String password,String fcmToken) {
        this.userName = userName;
        this.password = password;
        this.fcmToken = fcmToken;
    }

    public User(String _id, String name, String userName, String email, List<Permission> permission, String token, int id) {
        this._id = _id;
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.permission = permission;
        this.token = token;
        this.id = id;
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

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Permission> getPermission() {
        return permission;
    }

    public void setPermission(List<Permission> permission) {
        this.permission = permission;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(_id);
        parcel.writeString(name);
        parcel.writeString(userName);
        parcel.writeString(password);
        parcel.writeString(email);
        parcel.writeString(avatar);
        parcel.writeString(birth);
        parcel.writeString(phoneNumber);
        parcel.writeString(oldPassword);
        parcel.writeString(newPassword);
        parcel.writeString(token);
        parcel.writeInt(id);
    }

    public String getFcmToken() {
        return fcmToken;
    }

    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }
}
