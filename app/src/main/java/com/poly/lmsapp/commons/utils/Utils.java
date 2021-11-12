package com.poly.lmsapp.commons.utils;

import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;
import com.google.gson.Gson;
import com.poly.lmsapp.model.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Utils {

    public  static void hideKeyBoard(Activity activity){
        activity.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
    }

    public static Object jsonDecode(Object json,Class instance){
        return new Gson().fromJson( new Gson().toJson(json), instance);
    }

}
