package com.perfect.msmth.helper;

import com.perfect.msmth.helper.StrHelper;
import com.perfect.msmth.helper.SmthSpider;
import com.perfect.msmth.helper.HtmlHelper;

import com.perfect.msmth.mvc.data.PostData;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Xml;

import org.xmlpull.v1.XmlSerializer;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.StringWriter;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    
    public List<PostData> getNewPostList(String board) {
        String url = String.format(StrHelper.URL_SMTH_BOARD, board);
        
        String content = mSpider.getUrlContent(url, "UTF-8");
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
        
        String content = mSpider.getUrlContent(url, "UTF-8");
        if(content == null) {
            return new ArrayList<PostData>();
        }
        
        return HtmlHelper.parsePostList(content);
    }
    
    public List<PostData> getRepPostList(String url) {
        return getRepPostList(url, 0);
    }
    
    public List<PostData> getRepPostList(String url, int page) {
        String content = mSpider.getUrlContent(String.format("%s?p=%d", url, page), "UTF-8");
        if(content == null) {
            return new ArrayList<PostData>();
        }
        
        return HtmlHelper.parsePostListDirectly(content);
    }
    
    public Drawable getImage(String url) { 
        Drawable drawable = null;  
        
        byte[] bytes = mSpider.getUrlImage(url);
        if(bytes != null) {
            drawable = Drawable.createFromStream(new ByteArrayInputStream(bytes), "src");
        }
        
        return drawable;
    }
    
    public boolean getAllBoardList(Context context) {
        String content = mSpider.getUrlContent(StrHelper.URL_SMTH_BOARD_LIST, "GBK");
        if(content == null) {
            return false;
        }
        
        try {
            XmlSerializer serializer = Xml.newSerializer();
            StringWriter writer = new StringWriter();
            serializer.setOutput(writer);
            
            serializer.startDocument("UTF-8",true);
            serializer.startTag("", "root");
            
            Matcher m = Pattern.compile(StrHelper.REG_SMTH_BOARD_LIST).matcher(content);
            while(m.find()) {
                serializer.startTag("", "board");
                serializer.attribute("", "name", m.group(1));
                serializer.text(m.group(2));
                serializer.endTag("", "board");
            }
            
            serializer.endTag("", "root");
            serializer.endDocument();
            
            FileOutputStream fout = context.openFileOutput("boardlist.xml", Context.MODE_PRIVATE);
            byte[] bytes = writer.toString().getBytes();
            fout.write(bytes);
            fout.close();
            
            return true;
            
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
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

