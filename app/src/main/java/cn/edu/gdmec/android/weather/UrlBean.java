package cn.edu.gdmec.android.weather;

/**
 * Created by apple on 18/5/15.
 */

public class UrlBean {
    public static String getWeatherPATH() {
        return WeatherPATH;
    }

    public static String getBookPATH() {
        return bookPATH;
    }

    final static String WeatherPATH = "https://www.sojson.com/open/api/weather/json.shtml?city=";
    final static String bookPATH = "https://api.douban.com/v2/book/search?q=%E9%87%91%E7%93%B6%E6%A2%85&tag=&start=0&count=1";
}
