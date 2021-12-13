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
    @SerializedName("chuyenNganh")
    private Department chuyenNganh;
    @SerializedName("oldPassword")
    private String oldPassword;
    @SerializedName("newPassword")
    private String newPassword;
    @SerializedName("kiHoc")
    private Semester kiHoc;
    @SerializedName("permission")
    private List<Permission> permission;
    private String fcmToken;
    private String data;
    private String gender;
    private String address;
    private int idGroup;
    private int chuyenNganhId;
    private int kiHocId;

    public User(String oldPassword, String newPassword,int idGroup,int address) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.idGroup = idGroup;
    }
   public User(String userName, String password,int idGroup,String address,String email) {
        this.userName = userName;
        this.password = password;
    }

    public Department getChuyenNganh() {
        return chuyenNganh;
    }

    public void setChuyenNganh(Department chuyenNganh) {
        this.chuyenNganh = chuyenNganh;
    }

    public Semester getKiHoc() {
        return kiHoc;
    }

    public void setKiHoc(Semester kiHoc) {
        this.kiHoc = kiHoc;
    }

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

    public User(String name, String email, String avatar,String address, String birth, String phoneNumber, String data, int id,int idGroup,String gender,int chuyenNganhId,int kiHocId) {
        this.name = name;
        this.email = email;
        this.avatar = avatar;
        this.birth = birth;
        this.phoneNumber = phoneNumber;
        this.data = data;
        this.id = id;
        this.idGroup = idGroup;
        this.gender = gender;
        this.chuyenNganhId = chuyenNganhId;
        this.kiHocId = kiHocId;
        this.address = address;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(int idGroup) {
        this.idGroup = idGroup;
    }

    public String getGender() {
        return gender;
    }

    public int getChuyenNganhId() {
        return chuyenNganhId;
    }

    public void setChuyenNganhId(int chuyenNganhId) {
        this.chuyenNganhId = chuyenNganhId;
    }

    public int getKiHocId() {
        return kiHocId;
    }

    public void setKiHocId(int kiHocId) {
        this.kiHocId = kiHocId;
    }

    public void setGender(String gender) {
        this.gender =

                gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
