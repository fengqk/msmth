package com.perfect.msmth.helper;

import com.perfect.msmth.helper.SmthSpider;
import com.perfect.msmth.mvc.data.PostData;

import java.util.Collections;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.List;
import java.util.ArrayList;

public class SmthHelper {
    
    public static final String SMTH_URL = "http://www.newsmth.net/mainpage.html";
    public static final String SMTH_HOT_REGEXP = "<table [^<>]+class=\"HotTable\"[^<>]+>(.*?)</table>";
    public static final String SMTH_HOT_BOARD_REGEXP = "<a href=\"bbsdoc.php\\?board=\\w+\">([^<>]+)</a>";
    public static final String SMTH_HOT_TITLE_REGEXP = "<a href=\"bbstcon.php\\?board=\\w+&gid=\\d+\">([^<>]+)</a>";
    public static final String SMTH_HOT_AUTHOR_REGEXP = "<a href=\"bbsqry.php\\?userid=(\\w+)\">";
    
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

    public List<PostData> getHotPostList() {
        String allContent = mSpider.getUrlContent(SMTH_URL);
        if(allContent == null) {
            return new ArrayList<PostData>();
        }
        
        List<PostData> postList = new ArrayList<PostData>();
        Matcher hotMatcher = Pattern.compile(SMTH_HOT_REGEXP, Pattern.DOTALL).matcher(allContent);
        if(hotMatcher.find()) {
            String hotContent = hotMatcher.group(1);
           
            Matcher boardMatcher = Pattern.compile(SMTH_HOT_BOARD_REGEXP).matcher(hotContent);
            Matcher titleMatcher = Pattern.compile(SMTH_HOT_TITLE_REGEXP).matcher(hotContent);
            Matcher authorMatcher = Pattern.compile(SMTH_HOT_AUTHOR_REGEXP).matcher(hotContent);

            while(boardMatcher.find() && titleMatcher.find() && authorMatcher.find()) {
                PostData post = new PostData();
     
                post.setBoard(boardMatcher.group(1));
                post.setTitle(titleMatcher.group(1));
                post.setAuthor(authorMatcher.group(1));
                
                postList.add(post);
            }
        }
        
        return postList;
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

