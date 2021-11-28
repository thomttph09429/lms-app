package com.poly.lmsapp.model;

public class RegisterClass {
    private  int id;
    private  int idClass;
    private  int idUser;

    public RegisterClass() {
    }

    public RegisterClass(int idClass) {
        this.idClass = idClass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdClass() {
        return idClass;
    }

    public void setIdClass(int idClass) {
        this.idClass = idClass;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
