package com.perfect.msmth.adapter;

import com.perfect.msmth.R;
import com.perfect.msmth.SmthApp;
import com.perfect.msmth.activity.ImageActivity;
import com.perfect.msmth.fragment.HotFragment;
import com.perfect.msmth.mvc.data.PostData;
import com.perfect.msmth.mvc.data.PostData.Attachment;
import com.perfect.msmth.mvc.model.ImgPostModel;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;
import android.content.Intent;
import android.text.Html;

public class HotPostAdapter extends BaseAdapter {

    private class HotPostViewHolder {
        public TextView mTitle;
        public TextView mContent;
        public TextView mDate;
        public TextView mAuthor;
        public TextView mLink;
        public LinearLayout mImageList;
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
            layout = (RelativeLayout)mInflater.inflate(R.layout.post, null);
            holder = new HotPostViewHolder();
            
            holder.mTitle = (TextView)layout.findViewById(R.id.text_post_title);
            holder.mContent = (TextView)layout.findViewById(R.id.text_post_content);
            holder.mDate = (TextView)layout.findViewById(R.id.text_post_date);
            holder.mAuthor = (TextView)layout.findViewById(R.id.text_post_author);
            holder.mLink = (TextView)layout.findViewById(R.id.text_post_link);
            holder.mImageList = (LinearLayout)layout.findViewById(R.id.layout_post_image);
            
            layout.setTag(holder);
        }
        
        PostData post = mPostList.get(position);
        
        if(post.getBoard() != null && post.getTitle() != null) {
            holder.mTitle.setText(String.format(mFragment.getString(R.string.label_post_rank), position + 1, post.getBoard(), post.getTitle()));
        } else {
            holder.mTitle.setText("null");
        }
        
        if(post.getContent() != null) {
            holder.mContent.setText(Html.fromHtml(post.getContent()));
        } else {
            holder.mContent.setText("null");
        }
        
        if(post.getDate() != null) {
            holder.mDate.setText(post.getDate());
        } else {
            holder.mDate.setText("null");
        }
        
        if(post.getAuthor() != null) {
            holder.mAuthor.setText(String.format(mFragment.getString(R.string.label_post_author), post.getAuthor()));
        } else {
            holder.mAuthor.setText("null");
        }
        
        if(post.getLink() != null) {
            holder.mLink.setText(post.getLink());
        } else {
            holder.mLink.setText("null");
        }
        holder.mLink.setVisibility(View.GONE);
        
        ImgPostModel imgModel = ((SmthApp)mFragment.getActivity().getApplication()).getImgPostModel();
        ArrayList<Attachment> attachList = post.getAttachment();
        if(attachList != null) {
            holder.mImageList.removeAllViews();
            for(int i =0; i < attachList.size(); ++i) {
                ImageView imageView = new ImageView(mFragment.getActivity());
                imageView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                imageView.setTag(attachList.get(i));
                
                imageView.setOnClickListener(new OnClickListener() {
                    
                    @Override
                    public void onClick(View view) {
                            Intent intent = new Intent();
                            intent.setClass(view.getContext(), ImageActivity.class);
                            Attachment attachment = (Attachment)view.getTag();
                            intent.putExtra("IMAGE_SRC_URL", attachment.getSrcUrl());
                            intent.putExtra("IMAGE_NAME", attachment.getName());
                            mFragment.startActivity(intent);
                    }
                });
                
                holder.mImageList.addView(imageView);
                imgModel.update(attachList.get(i).getLocUrl(), imageView);
            }
        }

        return layout;
    }
}
