package com.poly.lmsapp.commons.network;

import com.poly.lmsapp.R;
import com.poly.lmsapp.commons.resource.StringResource;
import okhttp3.OkHttpClient;

public class Client {
    public static Service service = BaseClient.createService(Service.class, StringResource.baseUrl);
//    public static Service service = BaseClient.createService(Service.class,"http://ip.jsontest.com/"); // url test api
}
