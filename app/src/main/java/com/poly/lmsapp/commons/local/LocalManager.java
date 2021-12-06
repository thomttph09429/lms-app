package com.poly.lmsapp.commons.local;

import android.content.Context;
import android.content.SharedPreferences;

public class LocalManager {
    private static LocalManager localManager ;
    private static SharedPreferences sharedPreferences ;
    public static LocalManager getInstance(Context activity){
        if(localManager == null){
            sharedPreferences = activity.getSharedPreferences("local_storage", Context.MODE_PRIVATE);
            localManager = new LocalManager();
        }
        return localManager;
    }

    public void putString(String k, String v){
        sharedPreferences.edit().putString(k, v).apply();
    }

    public void putBool(String k, boolean v){
        sharedPreferences.edit().putBoolean(k, v).apply();
    }

    public void putInt(String k, int v){
        sharedPreferences.edit().putInt(k, v).apply();
    }

    public String getString(String k){
       return sharedPreferences.getString(k, "");
    }

    public boolean getBool(String k){
       return sharedPreferences.getBoolean(k, false);
    }

    public int getInt(String k){
       return sharedPreferences.getInt(k, -1);
    }

    public void clear(){
        sharedPreferences.edit().clear().apply();
    }

    public boolean contain(String k){
      return  sharedPreferences.contains(k);
    }
}
