package com.perfect.msmth.mvc.data;

import com.perfect.msmth.mvc.Data;

public class PostData extends Data {

    private String mId;
    private String mTitle;
    private String mBoard;
    private String mContent;
    private String mAuthor;
    private String mDate;
    private String mLink;
    
    public PostData copy() {
        PostData copy = new PostData();
        
        copy.mId = mId;
        copy.mTitle = mTitle;
        copy.mBoard = mBoard;
        copy.mContent = mContent;
        copy.mAuthor = mAuthor;
        copy.mDate = mDate;
        copy.mLink = mLink;
        
        return copy;
    }
    
    public String getId() {
        return mId;
    }
    
    public void setId(String id) {
        mId = id;
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
}
