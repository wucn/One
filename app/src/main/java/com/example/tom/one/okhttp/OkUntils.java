package com.example.tom.one.okhttp;


import android.os.Handler;

import com.example.tom.one.callback.OkhttpCallBack;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by tom on 2016/9/19.
 */
public class OkUntils {

    OkhttpCallBack okhttpCallBack;
    static OkUntils okUntils = null;
    Handler handler = new Handler();

    private OkUntils() {
    }

    public static OkUntils getInstance() {
        if (okUntils == null) {
            okUntils = new OkUntils();
        }
        return okUntils;
    }

    public void setOkhttpCallBack(OkhttpCallBack okhttpCallBack) {
        this.okhttpCallBack = okhttpCallBack;
    }


    public String getdata(String path) {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(path)
                    .build();
//            Response response = client.newCall(request).execute();
//            String data = response.body().string();
//            return data;
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, final Response response) throws IOException {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            String data = null;
                            try {
                                data = response.body().string();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            if (okhttpCallBack != null) {
                                okhttpCallBack.setOnCallBack(data);
                            }
                        }
                    });
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
