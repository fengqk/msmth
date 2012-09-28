package com.perfect.msmth.helper;

import com.perfect.msmth.mvc.data.PostData;
import com.perfect.msmth.mvc.data.PostData.Attachment;
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
            
            ArrayList<String[]> imgList = (ArrayList<String[]>)objects[1];
            for(int i = 0; i < imgList.size(); ++i) {
                Attachment att = post.newAttachment();
                att.setName("image_" + i);
                att.setSrcUrl(imgList.get(i)[0]);
                att.setLocUrl(imgList.get(i)[1]);                
            }
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
            String url = String.format(StrHelper.URL_SMTH_SINGLE_POST, m.group(1), m.group(2));
            String detail = SmthSpider.getInstance().getUrlContent(url, "UTF-8");
            if(detail != null) {
                PostData post = parsePost(detail);
                post.setLink(String.format(StrHelper.URL_SMTH_POST, m.group(1), m.group(2)));
                
                postList.add(post);
            }
        }
        
        return postList;
    }
    
    public static List<PostData> parsePostListDirectly(String content) {
        if(content == null) {
            return new ArrayList<PostData>();
        }
        
        final List<PostData> postList = new ArrayList<PostData>();

        List<String> titleList = new ArrayList<String>();
        Matcher m = null;
        m = Pattern.compile(StrHelper.REG_SMTH_POST_TITLE).matcher(content);
        if(m.find()) { 
            titleList.add(m.group(1));
        }
        
        List<String> authorList = new ArrayList<String>();
        m = Pattern.compile(StrHelper.REG_SMTH_POST_AUTHOR).matcher(content);
        while(m.find()) { 
            authorList.add(m.group(1));
        }
        
        List<String> dateList = new ArrayList<String>();
        m = Pattern.compile(StrHelper.REG_SMTH_POST_DATE).matcher(content);
        while(m.find()) { 
            dateList.add(m.group(1));
        }
        
        List<Object[]> contentList = new ArrayList<Object[]>();
        m = Pattern.compile(StrHelper.REG_SMTH_POST_CONTENT).matcher(content);
        while(m.find()) { 
            contentList.add(StrHelper.filterPostContent(m.group(1)));
        }
        
        for(int i = 0; i < authorList.size(); ++i) {
            PostData post = new PostData();
            
            if(i < titleList.size()) {
                post.setTitle(titleList.get(i));
            }
            
            post.setAuthor(authorList.get(i));
            
            if(i < dateList.size()) {
                post.setDate(dateList.get(i));
            }
            
            if(i < contentList.size()) {
                post.setContent((String)contentList.get(i)[0]);
                ArrayList<String[]> imgList = (ArrayList<String[]>)contentList.get(i)[1];
                for(int j = 0; j < imgList.size(); ++j) {
                    Attachment att = post.newAttachment();
                    att.setName("image_" + j);
                    att.setSrcUrl(imgList.get(j)[0]);
                    att.setLocUrl(imgList.get(j)[1]);                
                }
            }
            
            postList.add(post);
        }
        
        return postList;
    }
}
