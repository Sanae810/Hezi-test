package com.example.test3;

import android.app.Application;

import cn.bmob.v3.Bmob;

public class BmobApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Bmob.initialize(this, "99d1b745767013cfe839f685c28bdf11");
    }
}