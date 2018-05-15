package cn.edu.gdmec.android.weather.model;

import cn.edu.gdmec.android.weather.BookBean;
import cn.edu.gdmec.android.weather.WeatherBean;
import cn.edu.gdmec.android.weather.utils.OkHttpUtils;

/**
 * Created by apple on 18/5/15.
 */

public class BookModel implements IBookModel {
    @Override
    public void loadBook(String url, final ILoadListener loadListener) {
        OkHttpUtils.ResultCallBack resultCallBack = new OkHttpUtils.ResultCallBack() {
            @Override
            public void getWeather(WeatherBean weatherBean) {

            }

            @Override
            public void getBook(BookBean bookBean) {
                loadListener.onSuccessBook(bookBean);
            }

            @Override
            public void onFailure(Exception e) {

                loadListener.onFailure(e);
            }
        };
        OkHttpUtils.getResultCallback(url,resultCallBack);
    }
}
