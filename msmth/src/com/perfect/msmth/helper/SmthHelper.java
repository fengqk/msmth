package com.perfect.msmth.helper;

import com.perfect.msmth.helper.SmthSpider;
import com.perfect.msmth.helper.XmlHelper;
import com.perfect.msmth.mvc.data.PostData;

import java.util.List;
import java.util.ArrayList;

public class SmthHelper {
    
    public static final String SMTH_URL = "http://www.newsmth.net/mainpage.html";
    public static final String SMTH_HOT_URL = "http://www.newsmth.net/rssi.php?h=1";

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

    public List<PostData> getHotPost() {
        String content = mSpider.getUrlContent(SMTH_HOT_URL);
        if(content == null) {
            return new ArrayList<PostData>();
        }
        
        return XmlHelper.parseHotPost(content);
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

