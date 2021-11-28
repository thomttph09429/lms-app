package com.poly.lmsapp.commons.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import com.google.gson.Gson;
import com.poly.lmsapp.commons.resource.StringResource;
import com.poly.lmsapp.model.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Base64;


public class Utils {

    public static void hideKeyBoard(Activity activity) {
        activity.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
    }

    public static Object jsonDecode(Object json, Class instance) {
        return new Gson().fromJson(new Gson().toJson(json), instance);
    }

    public static Object jsonDecode(String json, Class instance) {
        return new Gson().fromJson(json, instance);
    }

    public static String jsonEncode(Object json) {
        return new Gson().toJson(json);
    }

    public static void lunchUrl(Activity activity, String url) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        activity.startActivity(i);
    }

    public static void showToast(Activity activity, String message) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }

    public static String concatPath(String path) {
        return StringResource.baseUrl + path;
    }


//    public static String encodeBase64(String path) {
//
////        return new String(Base64.getEncoder().encode(bytes));
//    }

}
