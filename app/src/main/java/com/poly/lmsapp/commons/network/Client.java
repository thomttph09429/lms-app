package com.poly.lmsapp.commons.network;

import com.poly.lmsapp.R;
import com.poly.lmsapp.commons.resource.StringResource;
import okhttp3.OkHttpClient;

public class Client {
    public static Service service;

    public static Service getInstance(){
        if(service == null){
            service = BaseClient.createService(Service.class, StringResource.baseUrl);
        }
        return service;
    }
//    public static Service service = BaseClient.createService(Service.class,"http://ip.jsontest.com/"); // url test api
}
