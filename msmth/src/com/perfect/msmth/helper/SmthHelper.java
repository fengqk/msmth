package com.perfect.msmth.helper;

import com.perfect.msmth.helper.SmthSpider;
import com.perfect.msmth.helper.HtmlHelper;
import com.perfect.msmth.helper.XmlHelper;

import com.perfect.msmth.mvc.data.PostData;

import java.util.List;
import java.util.ArrayList;

public class SmthHelper {
    
    public static final String SMTH_URL = "http://www.newsmth.net/mainpage.html";
    public static final String SMTH_HOT_TOTAL_URL = "http://www.newsmth.net/rssi.php?h=1";
    public static final String SMTH_HOT_GROUP_URL = "http://www.newsmth.net/rssi.php?h=2&s=%d";
    
    public static final String SMTH_POST_URL = "http://www.newsmth.net/bbstcon.php?board=%s&gid=%s";
    public static final String SMTH_BOARD_URL = "http://www.newsmth.net/bbsdoc.php?board=%s&ftype=6";
    
    
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
        String boards[] = {"WorldSoccer", "Joke", "TVShow", "RealEstate"};
        
        List<PostData> postList = new ArrayList<PostData>();
        for(int i=0; i<boards.length; ++i){
            postList.addAll(getNewPostList(boards[i]));
        }
        
        return postList;
    }
    
    public List<PostData> getNewPostList(String board) {
        String url = String.format(SMTH_BOARD_URL, board);
        
        String content = mSpider.getUrlContent(url);
        if(content == null) {
            return new ArrayList<PostData>();
        }
        
        return HtmlHelper.parseNewPostList(content);
    }
    
    public List<PostData> getHotPostList(int pos) {
        String url = SMTH_HOT_TOTAL_URL;
        if(pos > 0) {
            url = String.format(SMTH_HOT_GROUP_URL, pos);
        }
        
        String content = mSpider.getUrlContent(url);
        if(content == null) {
            return new ArrayList<PostData>();
        }
        
        return XmlHelper.parseHotPostList(content);
    }
    
    public void getSubject(String url) {
        
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

