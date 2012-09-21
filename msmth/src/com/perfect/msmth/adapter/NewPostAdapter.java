package com.perfect.msmth.adapter;

import com.perfect.msmth.R;
import com.perfect.msmth.fragment.HomeFragment;
import com.perfect.msmth.mvc.data.PostData;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Date;

public class NewPostAdapter extends BaseAdapter {

    private class NewPostViewHolder {
        public TextView mTitle;
        public TextView mContent;
        public TextView mDate;
        public TextView mAuthor;
        public TextView mLink;
    }
    
    private HomeFragment mFragment;
    private LayoutInflater mInflater;
    private List<PostData> mPostList;
    
    public NewPostAdapter(HomeFragment fragment, LayoutInflater inflater) {
        mFragment = fragment;
        mInflater = inflater;
    }
    
    public void setData(List<PostData> postList) {
        mPostList = postList;
    }
    
    @Override
    public int getCount() {
        return (mPostList == null) ? 0 : mPostList.size();
    }

    @Override
    public Object getItem(int position) {
        position = (position >= mPostList.size()) ? position - 1 : position;
        return mPostList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RelativeLayout layout = null;
        NewPostViewHolder holder = null;
        
        if(convertView != null){
            layout = (RelativeLayout)convertView;
            holder = (NewPostViewHolder)layout.getTag();
        } else {
            layout = (RelativeLayout)mInflater.inflate(R.layout.post, null);
            holder = new NewPostViewHolder();
            
            holder.mTitle = (TextView)layout.findViewById(R.id.text_post_title);
            holder.mContent = (TextView)layout.findViewById(R.id.text_post_content);
            holder.mDate = (TextView)layout.findViewById(R.id.text_post_date);
            holder.mAuthor = (TextView)layout.findViewById(R.id.text_post_author);
            holder.mLink = (TextView)layout.findViewById(R.id.text_post_link);
            
            layout.setTag(holder);
        }
        
        PostData post = mPostList.get(position);
        
        if(post.getTitle() != null) {
            holder.mTitle.setText(post.getBoard() + post.getTitle());
        } else {
            holder.mTitle.setText("unknown");
        }
        
        if(post.getContent() != null) {
            holder.mContent.setText(Html.fromHtml(post.getContent()));
        } else {
            holder.mContent.setText("unknown");
        }
        
        if(post.getDate() != null) {
            Date d  = new Date(Integer.parseInt(post.getDate()));
            holder.mDate.setText(d.toString());
        } else {
            holder.mDate.setText("unknown");
        }
        
        if(post.getAuthor() != null) {
            holder.mAuthor.setText(String.format(mFragment.getString(R.string.label_post_author), post.getAuthor()));
        } else {
            holder.mAuthor.setText("unknown");
        }
        
        if(post.getLink() != null) {
            holder.mLink.setText(post.getLink());
        } else {
            holder.mLink.setText("unknown");
        }
        holder.mLink.setVisibility(View.GONE);
        
        return layout;
    }
    
}
