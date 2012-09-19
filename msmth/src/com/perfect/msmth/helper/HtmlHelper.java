package com.perfect.msmth.helper;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import android.util.Log;

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
}
