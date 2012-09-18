package com.perfect.msmth;

import com.perfect.msmth.mvc.model.HotPostModel;

import android.app.Application;


public class SmthApp extends Application {
    private HotPostModel mHotPostModel = new HotPostModel();
    
    public HotPostModel getHotPostModel() {
        return mHotPostModel;
    }
}
