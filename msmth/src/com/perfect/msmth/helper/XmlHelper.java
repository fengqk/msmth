package com.perfect.msmth.helper;

import java.util.List;
import java.util.ArrayList;
import java.io.InputStream;
import org.xml.sax.Attributes;

import android.util.Xml;
import android.sax.RootElement;
import android.sax.Element;
import android.sax.StartElementListener;
import android.sax.EndTextElementListener;
import android.content.Context;

public class XmlHelper {

    public static List<String> parseBoardList (Context context) {
        
        final List<String> boardList = new ArrayList<String>();
        
        RootElement root = new RootElement("root");
        Element item = root.getChild("board");
        
        item.setStartElementListener(new StartElementListener() {
            @Override
            public void start(Attributes attributes) {
                boardList.add(attributes.getValue("name"));
            }
        });
        
        item.setEndTextElementListener(new EndTextElementListener() {
            @Override
            public void end(String body) {
                boardList.add(body);
            }
        });
       
        try {
            InputStream inputStream = context.getResources().getAssets().open("boardlist.xml");
            Xml.parse(inputStream, Xml.Encoding.UTF_8, root.getContentHandler());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
        return boardList;
    }
}