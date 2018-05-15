package cn.edu.gdmec.android.weather.utils;

import android.service.carrier.CarrierMessagingService;
import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import cn.edu.gdmec.android.weather.BookBean;
import cn.edu.gdmec.android.weather.UrlBean;
import cn.edu.gdmec.android.weather.WeatherBean;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by apple on 18/5/15.
 */

public class OkHttpUtils {
    String res = null;
    private static OkHttpUtils okHttpUtils;
    private synchronized static OkHttpUtils getInstance(){

        if (okHttpUtils == null){
            okHttpUtils = new OkHttpUtils();

        }
        return okHttpUtils;
    }
    public static void getResultCallback(String url, final ResultCallBack resultCallBack){
        getInstance().sendRequest(url,resultCallBack);


    }
    public void sendRequest(final String url, final ResultCallBack resultCallBack){
        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(2, TimeUnit.SECONDS).build();
        final Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (resultCallBack != null){
                    resultCallBack.onFailure(e);
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                res = response.body().string();
                Log.i("res",res);
                if (url.equals(UrlBean.getBookPATH())){
                    BookBean bookBean = JsonUtils.getBook(res);
                    if (resultCallBack != null){
                        resultCallBack.getBook(bookBean);
                        //resultCallBack.getData(bookBean);
                    }
                }else {
                    WeatherBean weatherBean = JsonUtils.getWeather(res);
                    if (resultCallBack != null){
                        resultCallBack.getWeather(weatherBean);
                    }

                }


            }
        });
    }


    public interface ResultCallBack{

        void getData();
        void getWeather(WeatherBean weatherBean);
        void getBook(BookBean bookBean);
        void onFailure(Exception e);

    }


}
