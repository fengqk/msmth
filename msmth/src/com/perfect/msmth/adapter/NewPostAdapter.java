package com.perfect.msmth.adapter;

import com.perfect.msmth.R;
import com.perfect.msmth.SmthApp;
import com.perfect.msmth.activity.ImageActivity;
import com.perfect.msmth.mvc.data.PostData;
import com.perfect.msmth.mvc.data.PostData.Attachment;
import com.perfect.msmth.mvc.model.ImgPostModel;

import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.view.animation.AccelerateInterpolator;

import java.util.ArrayList;
import java.util.List;

public class NewPostAdapter extends BaseAdapter {

    private class NewPostViewHolder {
        public TextView mTitle;
        public TextView mContent;
        public TextView mDate;
        public TextView mAuthor;
        public TextView mLink;
        public LinearLayout mImageList;
    }
    
    private Context mContext;
    private List<PostData> mPostList;
    
    public NewPostAdapter(Context context) {
        mContext = context;
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
            layout = (RelativeLayout)View.inflate(mContext, R.layout.post, null);
            holder = new NewPostViewHolder();
            
            holder.mTitle = (TextView)layout.findViewById(R.id.text_post_title);
            holder.mContent = (TextView)layout.findViewById(R.id.text_post_content);
            holder.mDate = (TextView)layout.findViewById(R.id.text_post_date);
            holder.mAuthor = (TextView)layout.findViewById(R.id.text_post_author);
            holder.mLink = (TextView)layout.findViewById(R.id.text_post_link);
            holder.mImageList = (LinearLayout)layout.findViewById(R.id.layout_post_image);
            
            layout.findViewById(R.id.layout_post_content).setVisibility(View.GONE);
            holder.mLink.setVisibility(View.GONE);
            
            LayoutTransition transition = new LayoutTransition();
            PropertyValuesHolder pvhAlpha = PropertyValuesHolder.ofFloat("alpha", 0.0f, 1.0f);
            ObjectAnimator appearingAnimator =  (ObjectAnimator) ObjectAnimator.ofPropertyValuesHolder((Object)null, pvhAlpha);
            appearingAnimator.setInterpolator(new AccelerateInterpolator());
            transition.setAnimator(LayoutTransition.APPEARING, appearingAnimator);
            transition.setStagger(LayoutTransition.APPEARING, 2000);
            ((RelativeLayout)layout.findViewById(R.id.layout_post_body)).setLayoutTransition(transition);
            
            layout.setTag(holder);
        }
        
        PostData post = mPostList.get(position);
        
        if(post.getTitle() != null) {
            holder.mTitle.setText(post.getTitle());
        } else {
            holder.mTitle.setText("");
        }
        
        if(post.getContent() != null) {
            holder.mContent.setText(Html.fromHtml(post.getContent()));
        } else {
            holder.mContent.setText("");
        }
        
        if(post.getDate() != null) {
            holder.mDate.setText(post.getDate());
        } else {
            holder.mDate.setText("");
        }
        
        if(post.getAuthor() != null) {
            holder.mAuthor.setText(String.format(mContext.getString(R.string.label_post_author), post.getAuthor()));
        } else {
            holder.mAuthor.setText("");
        }
        
        if(post.getLink() != null) {
            holder.mLink.setText(post.getLink());
        } else {
            holder.mLink.setText("");
        }
        
        holder.mImageList.removeAllViews();
        ArrayList<Attachment> attachList = post.getAttachment();
        if(attachList != null) {
            for(int i =0; i < attachList.size(); ++i) {
                ImageView imageView = new ImageView(mContext);
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
                        mContext.startActivity(intent);
                    }
                });
                
                holder.mImageList.addView(imageView);
                
                ImgPostModel imgModel = ((SmthApp)((Activity)mContext).getApplication()).getImgPostModel();
                imgModel.update(attachList.get(i).getLocUrl(), imageView);
            }
        }
        
        return layout;
    }
}
