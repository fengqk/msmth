package com.perfect.msmth;

import com.perfect.msmth.mvc.model.HotPostModel;
import com.perfect.msmth.mvc.model.NewPostModel;
import com.perfect.msmth.mvc.model.ImgPostModel;
import com.perfect.msmth.mvc.model.RepPostModel;

import android.app.Application;

public class SmthApp extends Application {
    
    private HotPostModel mHotPostModel = new HotPostModel();
    private NewPostModel mNewPostModel = new NewPostModel();
    private ImgPostModel mImgPostModel = new ImgPostModel();
    private RepPostModel mRepPostModel = new RepPostModel();
    
    public HotPostModel getHotPostModel() {
        return mHotPostModel;
    }
    
    public NewPostModel getNewPostModel() {
        return mNewPostModel;
    }
    
    public ImgPostModel getImgPostModel() {
        return mImgPostModel;
    }
    
    public RepPostModel getRepPostModel() {
        return mRepPostModel;
    }
}
