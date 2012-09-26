package com.perfect.msmth.helper;

import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class StrHelper {

    public static final String URL_SMTH = "http://m.newsmth.net";
    public static final String URL_SMTH_POST = "http://m.newsmth.net/article/%s/%s";
    public static final String URL_SMTH_BOARD = "http://m.newsmth.net/board/%s";
    public static final String URL_SMTH_HOT_TOTAL = "http://m.newsmth.net/hot";
    public static final String URL_SMTH_HOT_GROUP = "http://m.newsmth.net/hot/%d";
    
    public static final String REG_SMTH_POST_LIST = "<a href=\"/article/(\\w+)/(\\d+)\">";
    
    public static final String REG_SMTH_POST_TITLE = "<li class=\"f\">[^:]+:([^<>]+)</li>";
    public static final String REG_SMTH_POST_BOARD = "<div class=\"menu sp\">[^-]+-([^<>]+)\\(([^<>]+)\\)</div>";
    public static final String REG_SMTH_POST_AUTHOR = "<a href=\"/user/query/([^<>]+)\">";
    public static final String REG_SMTH_POST_DATE = "<a class=\"plant\">\\d+-([^<>]+)</a>";
    public static final String REG_SMTH_POST_CONTENT = "<div class=\"sp\">(.*?)</div>";
    public static final String REG_SMTH_POST_IMAGE = "<a target=\"_blank\" href=\"([^<>]+)\"><img border=\"[^<>]+\" title=\"[^<>]+\" src=\"([^<>]+)\" class=\"[^<>]+\" />";
    
    public static Object[] filterPostContent(String content) {
        if (content == null) {
                return new Object[] { "", null };
        }
        
        content = content.replace("<br />", "<br/>");
        
        String[] lines = content.split("<br/>");
        StringBuilder sb = new StringBuilder();
        int linebreak = 0;
        int linequote = 0;
        int seperator = 0;
        boolean isMainbodyEnd = false;
        sb.append("<br />");
        
        ArrayList<String[]> imgList = new ArrayList<String[]>();
        for(String line : lines) {       
            Matcher m = Pattern.compile(REG_SMTH_POST_IMAGE).matcher(line);
            if(m.find()) {
                imgList.add(new String[]{m.group(1),m.group(2)});
                continue;
            }
            
            /*
            if(isMainbodyEnd) {
                m = Pattern.compile("<a href=\"([^<>]+)\">([^<>]+)</a>").matcher(line);
                if (m.find()) {
                    imgList.add(line);
                }
                continue;
            }
            */
            
            if(line.equals("--")) {
                if (seperator > 1) {
                    break;
                }

                seperator++;
            } else {
                if(seperator > 0) {
                    if (line.length() > 0) {
                        line = "<font color=#33CC66>" + line + "</font>";
                    } else {
                        continue;
                    }
                }
            }

            if(line.startsWith(":")) {
                linequote++;
                if (linequote > 5) {
                    continue;
                } else {
                    line = "<font color=#006699>" + line + "</font>";
                }
            } else {
                linequote = 0;
            }
            
            /*
            if(seperator > 0 && !line.contains("ÐÞ¸Ä") && line.contains("FROM ")) {
                isMainbodyEnd = true;
            }
            */

            if(line.equals("")) {
                linebreak++;
                if (linebreak > 1) {
                    continue;
                }
            } else {
                linebreak = 0;
            }
            
            sb.append(line).append("<br />");
        }

        String result = sb.toString().trim();
        return new Object[] {result, imgList};
    }
}
