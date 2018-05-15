package cn.edu.gdmec.android.weather.model;

import cn.edu.gdmec.android.weather.BookBean;
import cn.edu.gdmec.android.weather.WeatherBean;

/**
 * Created by apple on 18/5/15.
 */

public interface ILoadListener {
    void onSuccess(WeatherBean bean);
    void onSuccessBook(BookBean bookBean);
    void onFailure(Exception e);}
