package okhttp;

import com.example.tom.one.callback.OkhttpCallBack;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by tom on 2016/9/19.
 */
public class OkUntils {


    public static String getdata(String path) {

        try {

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(path)
                    .build();
            Response response = client.newCall(request).execute();
            String data = response.body().string();
            return data;
//            client.newCall(request).enqueue(new Callback() {
//                @Override
//                public void onFailure(Call call, IOException e) {
//
//                }
//
//                @Override
//                public void onResponse(Call call, Response response) throws IOException {
//
//                                String data = response.body().string();
//                }
//            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
