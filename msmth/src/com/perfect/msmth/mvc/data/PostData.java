package com.perfect.msmth.mvc.data;

import java.util.ArrayList;

import com.perfect.msmth.mvc.Data;

public class PostData extends Data {

    private String mID;
    private String mTitle;
    private String mBoard;
    private String mContent;
    private String mAuthor;
    private String mDate;
    private String mLink;
    private ArrayList<Attachment> mAttachList;
    
    public class Attachment {
        
        private String mName;
        private String mLocUrl;
        private String mSrcUrl;
        
        public String getName() {
            return mName;
        }
        
        public void setName(String name) {
            mName = name;
        }
        
        public String getLocUrl() {
            return mLocUrl;
        }
        
        public void setLocUrl(String url) {
            mLocUrl = url;
        }
        
        public String getSrcUrl() {
            return mSrcUrl;
        }
        
        public void setSrcUrl(String url) {
            mSrcUrl = url;
        }
    }
    
    public PostData copy() {
        PostData copy = new PostData();
        
        copy.mID = mID;
        copy.mTitle = mTitle;
        copy.mBoard = mBoard;
        copy.mContent = mContent;
        copy.mAuthor = mAuthor;
        copy.mDate = mDate;
        copy.mLink = mLink;
        copy.mAttachList = mAttachList;
        
        return copy;
    }
 
 
    public String getID() {
        return mID;
    }
    
    public void setID(String id) {
        mID = id;
    }
    
    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getBoard() {
        return mBoard;
    }

    public void setBoard(String board) {
        mBoard = board;
    }
    
    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }
    
    public String getAuthor() {
        return mAuthor;
    }

    public void setAuthor(String author) {
        mAuthor = author;
    }
    
    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }
    
    public String getLink() {
        return mLink;
    }

    public void setLink(String link) {
        mLink = link;
    }
    
    public Attachment newAttachment() {
        if(mAttachList == null) {
            mAttachList = new ArrayList<Attachment>();
        }
        
        Attachment att = new Attachment();
        mAttachList.add(att);
        
        return att;
    }
    
    public ArrayList<Attachment> getAttachment() {
        return mAttachList;
    }
}
