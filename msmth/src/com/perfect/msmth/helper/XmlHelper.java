package com.perfect.msmth.helper;

import com.perfect.msmth.mvc.data.PostData;
import com.perfect.msmth.helper.HtmlHelper;

import java.util.List;
import java.util.ArrayList;

import android.util.Xml;
import android.sax.RootElement;
import android.sax.Element;
import android.sax.EndElementListener;
import android.sax.EndTextElementListener;

public class XmlHelper {

    public static final String SMTH_HOT_DATE_REGEXP = "([a-zA-Z]{3}\\s\\d{2}\\s\\d{2}:\\d{2})";
    public static final String SMTH_HOT_CONTENT_REGEXP = "<br/><br/>(.*?)<br/>--<br/>";
    
    public static List<PostData> parseHotPost(String content) {
        final PostData post = new PostData();
        final List<PostData> postList = new ArrayList<PostData>();
        
        RootElement root = new RootElement("rss");
        Element channel = root.getChild("channel");
        Element item = channel.getChild("item");
        
        item.setEndElementListener(new EndElementListener() {
            @Override
            public void end() {
                postList.add(post.copy());
            }
        });
        item.getChild("title").setEndTextElementListener(new EndTextElementListener() {
            @Override
            public void end(String body) {
                post.setTitle(body);
            }
        });
        item.getChild("link").setEndTextElementListener(new EndTextElementListener() {
            @Override
            public void end(String body) {
                post.setLink(body);
            }
        });
        item.getChild("author").setEndTextElementListener(new EndTextElementListener() {
            @Override
            public void end(String body) {
                post.setAuthor(body);
            }
        });
        item.getChild("description").setEndTextElementListener(new EndTextElementListener() {
            @Override
            public void end(String body) {
                post.setDate(HtmlHelper.parse(body, SMTH_HOT_DATE_REGEXP, 1));
                post.setContent(HtmlHelper.parse(body, SMTH_HOT_CONTENT_REGEXP, 1));
            }
        });
       
        try {
            Xml.parse(content, root.getContentHandler());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
        return postList;
    }
}
