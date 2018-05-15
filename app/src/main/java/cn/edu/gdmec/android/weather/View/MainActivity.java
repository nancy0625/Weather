package cn.edu.gdmec.android.weather.View;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.TimerTask;

import cn.edu.gdmec.android.weather.BookBean;
import cn.edu.gdmec.android.weather.R;
import cn.edu.gdmec.android.weather.WeatherBean;
import cn.edu.gdmec.android.weather.presenter.BookPresenter;
import cn.edu.gdmec.android.weather.presenter.WeatherPresenter;

public class MainActivity extends AppCompatActivity implements IWeatherView, View.OnClickListener, IBookView {


    private Button btn_search;
    private Button btn_beijing;
    private Button btn_book;
    private TextView tv_View,tv_book;
    private TextView tv_yesterday;
    private TextView tv_forecast;
    private ProgressDialog progressDialog;
    private WeatherPresenter presenter;
    private BookPresenter bookPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        btn_search = (Button) findViewById(R.id.btn_search);
        btn_beijing = (Button) findViewById(R.id.btn_beijing);
        btn_book = (Button) findViewById(R.id.btn_book);
        tv_View = (TextView) findViewById(R.id.tv_View);
        tv_book = (TextView)findViewById(R.id.tv_book);
        tv_yesterday = (TextView) findViewById(R.id.tv_yesterday);
        tv_forecast = (TextView) findViewById(R.id.tv_forecast);

        btn_search.setOnClickListener(this);
        btn_beijing.setOnClickListener(this);
        btn_book.setOnClickListener(this);
        presenter = new WeatherPresenter(this);
        bookPresenter = new BookPresenter(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_search:

                presenter.loadWeather("广州");
                break;
            case R.id.btn_beijing:

                presenter.loadWeather("北京");
                break;
            case R.id.btn_book:
                bookPresenter.loadBook();

                break;
        }
    }

    @Override
    public void showProgress() {
        if (progressDialog != null  && progressDialog.isShowing()){
            progressDialog.dismiss();
        }
        progressDialog = ProgressDialog.show(MainActivity.this,"","正在获取");
    }

    @Override
    public void hideProgress() {

        if (progressDialog != null && progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }

    @Override
    public void showBookData(final BookBean bookBean) {
        runOnUiThread(new TimerTask() {
            @Override
            public void run() {
                if (bookBean.getStart()==1){
                    Toast.makeText(MainActivity.this,"失败",Toast.LENGTH_SHORT).show();
                }else {
                    tv_book.setText("作者："+bookBean.getBooks().get(0).getOrigin_title()+"简介："+bookBean.getBooks().get(0).getSubtitle());
                }
            }
        });
    }

    @Override
    public void showWeatherData(final WeatherBean weatherBean) {

        runOnUiThread(new TimerTask() {
            @Override
            public void run() {
                if (weatherBean.getStatus() == 304){
                    Toast.makeText(MainActivity.this,weatherBean.getMessage(),Toast.LENGTH_SHORT).show();
                }else {
                    tv_View.setText("城市："+weatherBean.getCity()+"日期："+weatherBean.getDate()+"温度："+weatherBean.getData().getWendu());
                    tv_yesterday.setText("昨日天气:"+weatherBean.getData().getYesterday().getLow()+" "+weatherBean.getData().getYesterday().getHigh());
                }
            }
        });
    }

    @Override
    public void showLoadFailMsg(final Exception e) {

        runOnUiThread(new TimerTask() {
            @Override
            public void run() {
                tv_View.setText("加载数据失败"+ e.toString());
            }
        });
    }
}
