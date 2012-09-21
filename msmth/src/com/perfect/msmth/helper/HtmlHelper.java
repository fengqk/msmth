package com.perfect.msmth.helper;

import com.perfect.msmth.mvc.data.PostData;
import com.perfect.msmth.helper.SmthHelper;

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
    
    public static final String SMTH_BOARD_REGEXP = "<h1 class=\"(.*?)\">(.*?)</h1>"; 
    public static final String SMTH_POST_REGEXP = "c.o(.*?);"; 
    
    public static List<PostData> parseNewPostList(String content) {
        if(content == null) {
            return new ArrayList<PostData>();
        }
        
        final PostData post = new PostData();
        final List<PostData> postList = new ArrayList<PostData>();
        
        String board = "";
        Matcher m = Pattern.compile(SMTH_BOARD_REGEXP).matcher(content);
        if(m.find()) {
            board = m.group(2);
        }
        
        m = Pattern.compile(SMTH_POST_REGEXP).matcher(content);
        while(m.find()) {
            String list[] = m.group(1).split(",");
            
            post.setId(list[0]);
            post.setAuthor(list[2]);
            post.setDate(list[4]);
            post.setTitle(list[5]);
            post.setBoard(board);
            //String.format(SmthHelper.SMTH_POST_URL, 
            
            postList.add(post.copy());
        }
        
        return postList;
    }
}
