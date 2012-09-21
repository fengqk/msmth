package com.perfect.msmth;

import com.perfect.msmth.mvc.model.HotPostModel;
import com.perfect.msmth.mvc.model.NewPostModel;

import android.app.Application;


public class SmthApp extends Application {
    private HotPostModel mHotPostModel = new HotPostModel();
    private NewPostModel mNewPostModel = new NewPostModel();
    
    public HotPostModel getHotPostModel() {
        return mHotPostModel;
    }
    
    public NewPostModel getNewPostModel() {
        return mNewPostModel;
    }
}
