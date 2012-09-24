package com.perfect.msmth.helper;

import com.perfect.msmth.mvc.data.PostData;
import com.perfect.msmth.helper.SmthSpider;
import com.perfect.msmth.helper.StrHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class HtmlHelper {
    
    public static String parse(String content, String regExp, int grpIdx) {
        if(content == null) {
            return null;
        }

        Matcher m = Pattern.compile(regExp).matcher(content);
        if(!m.find()) {
            return null;
        }
        
        if(grpIdx > m.groupCount()) {
            return null;
        }

        return m.group(grpIdx);
    }
    
    public static PostData parsePost(String content) {
        if(content == null) {
            return new PostData();
        }
        
        PostData post = new PostData();
        
        Matcher m = null;
        m = Pattern.compile(StrHelper.REG_SMTH_POST_TITLE).matcher(content);
        if(m.find()) { 
            post.setTitle(m.group(1));
        }
        
        m = Pattern.compile(StrHelper.REG_SMTH_POST_BOARD).matcher(content);
        if(m.find()) { 
            post.setBoard(m.group(1));
        }
        
        m = Pattern.compile(StrHelper.REG_SMTH_POST_AUTHOR).matcher(content);
        if(m.find()) { 
            post.setAuthor(m.group(1));
        }
        
        m = Pattern.compile(StrHelper.REG_SMTH_POST_DATE).matcher(content);
        if(m.find()) { 
            post.setDate(m.group(1));
        }
        
        m = Pattern.compile(StrHelper.REG_SMTH_POST_CONTENT).matcher(content);
        if(m.find()) { 
            Object[] objects = StrHelper.filterPostContent(m.group(1));
            post.setContent((String)objects[0]);
        }
        
        return post;
    }
    
    public static List<PostData> parsePostList(String content) {
        if(content == null) {
            return new ArrayList<PostData>();
        }
        
        final List<PostData> postList = new ArrayList<PostData>();
        
        Matcher m = Pattern.compile(StrHelper.REG_SMTH_POST_LIST).matcher(content);
        while(m.find()) {            
            String url = String.format(StrHelper.URL_SMTH_POST, m.group(1), m.group(2));
            String detail = SmthSpider.getInstance().getUrlContent(url);
            if(detail != null) {
                postList.add(parsePost(detail));
            }
        }
        
        return postList;
    }
}
