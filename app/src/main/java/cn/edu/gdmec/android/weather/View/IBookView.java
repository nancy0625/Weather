package cn.edu.gdmec.android.weather.View;

import cn.edu.gdmec.android.weather.BookBean;
import cn.edu.gdmec.android.weather.WeatherBean;

/**
 * Created by apple on 18/5/15.
 */

public interface IBookView {
    void showProgress();
    void hideProgress();
    void showBookData(BookBean bookBean);
    void showLoadFailMsg(Exception e);
}
