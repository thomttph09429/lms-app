package com.poly.lmsapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class User {

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
    @SerializedName("chuyenNganh")
    private String chuyenNganh;
    @SerializedName("oldPassword")
    private String oldPassword;
    @SerializedName("newPassword")
    private String newPassword;
    @SerializedName("kiHoc")
    private String kiHoc;
    @SerializedName("permission")
    private List<Permission> permission;

    public User(String oldPassword, String newPassword, String token) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.token = token;
    }

    public String getChuyenNganh() {
        return chuyenNganh;
    }

    public void setChuyenNganh(String chuyenNganh) {
        this.chuyenNganh = chuyenNganh;
    }

    public String getKiHoc() {
        return kiHoc;
    }

    public void setKiHoc(String kiHoc) {
        this.kiHoc = kiHoc;
    }



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

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
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
}
