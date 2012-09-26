package com.perfect.msmth.helper;

import com.perfect.msmth.helper.StrHelper;
import com.perfect.msmth.helper.SmthSpider;
import com.perfect.msmth.helper.HtmlHelper;

import com.perfect.msmth.mvc.data.PostData;

import android.graphics.drawable.Drawable;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.ArrayList;

public class SmthHelper {
    
    private SmthSpider mSpider;    
    private String mUsername;
    private String mPassword;

    private static class Holder {
        private static SmthHelper instance = new SmthHelper();
    }

    public static SmthHelper getInstance() {
        return Holder.instance;
    }

    private SmthHelper() {
        mSpider = SmthSpider.getInstance();
    }

    public List<PostData> getNewPostList() {
        return getNewPostList("FamilyLife");
    }
    
    public List<PostData> getNewPostList(String board) {
        String url = String.format(StrHelper.URL_SMTH_BOARD, board);
        
        String content = mSpider.getUrlContent(url);
        if(content == null) {
            return new ArrayList<PostData>();
        }
        
        return HtmlHelper.parsePostList(content);
    }
    
    public List<PostData> getHotPostList(int pos) {
        String url = StrHelper.URL_SMTH_HOT_TOTAL;
        if(pos > 0) {
            url = String.format(StrHelper.URL_SMTH_HOT_GROUP, pos);
        }
        
        String content = mSpider.getUrlContent(url);
        if(content == null) {
            return new ArrayList<PostData>();
        }
        
        return HtmlHelper.parsePostList(content);
    }
    
    public Drawable getImage(String url) { 
        Drawable drawable = null;  
        
        byte[] bytes = mSpider.getUrlImage(url);
        if(bytes != null) {
            drawable = Drawable.createFromStream(new ByteArrayInputStream(bytes), "src");
        }
        
        return drawable;
    }
    
    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }
}

