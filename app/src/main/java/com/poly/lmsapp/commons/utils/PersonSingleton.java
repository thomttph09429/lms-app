package com.poly.lmsapp.commons.utils;

import com.poly.lmsapp.model.User;

public class PersonSingleton {
    private static PersonSingleton personSingleton;
    private User user;
   public static PersonSingleton getInstance() {
       if(personSingleton == null)
           personSingleton = new PersonSingleton();
        return personSingleton;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
