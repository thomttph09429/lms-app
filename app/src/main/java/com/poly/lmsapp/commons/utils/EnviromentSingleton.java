package com.poly.lmsapp.commons.utils;

public class EnviromentSingleton {
    private static EnviromentSingleton enviromentSingleton;
    private int idDocumentType;
    private int idClass;
    private int idSubject;

    public static EnviromentSingleton getEnviromentSingleton() {
        if(enviromentSingleton == null) enviromentSingleton = new EnviromentSingleton();
        return enviromentSingleton;
    }

    public static void setEnviromentSingleton(EnviromentSingleton enviromentSingleton) {
        EnviromentSingleton.enviromentSingleton = enviromentSingleton;
    }

    public int getIdDocumentType() {
        return idDocumentType;
    }

    public void setIdDocumentType(int idDocumentType) {
        this.idDocumentType = idDocumentType;
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
}
