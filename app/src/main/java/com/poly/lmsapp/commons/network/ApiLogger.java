package com.poly.lmsapp.commons.network;

import android.util.Log;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import okhttp3.logging.HttpLoggingInterceptor;

public class ApiLogger implements HttpLoggingInterceptor.Logger {

    @Override
    public void log(String message) {
        String logName = "Logger";
        if (message.startsWith("{") || message.startsWith("[")) {
            try {
                String prettyPrintJson = new GsonBuilder().setPrettyPrinting()
                        .create().toJson(new JsonParser().parse(message));
                Log.e(logName, prettyPrintJson);
            } catch (JsonSyntaxException e) {
                Log.d(logName, message);
            }
        } else {
            Log.d(logName, message);
            return;
        }
    }
}
