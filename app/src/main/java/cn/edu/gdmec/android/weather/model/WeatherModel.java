package cn.edu.gdmec.android.weather.model;

import cn.edu.gdmec.android.weather.BookBean;
import cn.edu.gdmec.android.weather.WeatherBean;
import cn.edu.gdmec.android.weather.utils.OkHttpUtils;

/**
 * Created by apple on 18/5/15.
 */

public class WeatherModel implements IWeatherModel {
    @Override
    public void loadWeather(String url, final ILoadListener loadListener) {
        OkHttpUtils.ResultCallBack resultCallBack = new OkHttpUtils.ResultCallBack() {
            @Override
            public void getWeather(WeatherBean weatherBean) {
                loadListener.onSuccess(weatherBean);
            }

            @Override
            public void getBook(BookBean bookBean) {

            }

            @Override
            public void onFailure(Exception e) {

                loadListener.onFailure(e);
            }
        };
        OkHttpUtils.getResultCallback(url,resultCallBack);
    }
}
