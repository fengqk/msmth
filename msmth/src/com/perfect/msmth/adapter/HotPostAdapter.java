package com.perfect.msmth.adapter;

import com.perfect.msmth.R;
import com.perfect.msmth.fragment.HotFragment;
import com.perfect.msmth.mvc.data.PostData;

import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class HotPostAdapter extends BaseAdapter {

    private class HotPostViewHolder {
        public TextView mRank;
        public TextView mHots;
        public TextView mTitle;
        public TextView mBoard;
        public TextView mContent;
        public TextView mDate;
        public TextView mAuthor;
        public TextView mLink;
    }
    
    private HotFragment mFragment;
    private LayoutInflater mInflater;
    private List<PostData> mPostList;
    
    public HotPostAdapter(HotFragment fragment, LayoutInflater inflater) {
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
        HotPostViewHolder holder = null;
        
        if(convertView != null){
            layout = (RelativeLayout)convertView;
            holder = (HotPostViewHolder)layout.getTag();
        } else {
            layout = (RelativeLayout)mInflater.inflate(R.layout.post_outline, null);
            holder = new HotPostViewHolder();
            
            holder.mRank = (TextView)layout.findViewById(R.id.text_post_rank);
            holder.mHots = (TextView)layout.findViewById(R.id.text_post_hots);
            holder.mTitle = (TextView)layout.findViewById(R.id.text_post_title);
            holder.mBoard = (TextView)layout.findViewById(R.id.text_post_board);
            holder.mContent = (TextView)layout.findViewById(R.id.text_post_content);
            holder.mDate = (TextView)layout.findViewById(R.id.text_post_date);
            holder.mAuthor = (TextView)layout.findViewById(R.id.text_post_author);
            holder.mLink = (TextView)layout.findViewById(R.id.text_post_link);
            
            layout.setTag(holder);
        }
        
        PostData post = mPostList.get(position);
        
        holder.mRank.setText("TOP " + Integer.toString(position + 1));
        holder.mHots.setText("");
        holder.mTitle.setText(post.getTitle());
        holder.mBoard.setText("[" + post.getBoard() + "]");
        holder.mContent.setText(post.getContent());
        holder.mDate.setText("9月18日 20:59");
        holder.mAuthor.setText("来自" + post.getAuthor());
        holder.mLink.setText(post.getLink());
        
        return layout;
    }
}
