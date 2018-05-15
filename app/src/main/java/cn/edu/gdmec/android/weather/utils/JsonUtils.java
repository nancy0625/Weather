package cn.edu.gdmec.android.weather.utils;

import com.google.gson.Gson;

import cn.edu.gdmec.android.weather.BookBean;
import cn.edu.gdmec.android.weather.WeatherBean;

/**
 * Created by apple on 18/5/15.
 */

public class JsonUtils {
    public static WeatherBean getWeather(String res){
        Gson gson = new Gson();
        WeatherBean weatherBean = gson.fromJson(res,WeatherBean.class);
        return weatherBean;
    }
    public static BookBean getBook(String res){
        Gson gson = new Gson();
        BookBean bookBean = gson.fromJson(res,BookBean.class);
        return bookBean;
    }
}
