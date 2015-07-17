package com.example.administrator.myfive;


import com.activeandroid.ActiveAndroid;
import com.activeandroid.app.Application;

/**
 * Created by Administrator on 2015/7/17.
 * 扩展 Application
 * ORM ActivityAndroid 的初始化
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);

    }
}
