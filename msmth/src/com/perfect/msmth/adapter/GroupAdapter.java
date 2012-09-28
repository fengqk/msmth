package com.perfect.msmth.adapter;

import com.perfect.msmth.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;
import java.util.ArrayList;

public class GroupAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> mGroupList;
    
    private class GroupViewHolder {
        public TextView mGroup;
    }
    
    public GroupAdapter(Context context) {
        mContext = context;
        mGroupList = new ArrayList<String>();
        
        String[] groups = context.getResources().getStringArray(R.array.label_hot_group);
        for(String group : groups) {
            mGroupList.add(group);
        }
    }
    
    @Override
    public int getCount() {
        return (mGroupList == null) ? 0 : mGroupList.size();
    }

    @Override
    public Object getItem(int position) {
        position = (position >= mGroupList.size()) ? position - 1 : position;
        return mGroupList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RelativeLayout layout = null;
        GroupViewHolder holder = null;
        
        if(convertView != null){
            layout = (RelativeLayout)convertView;
            holder = (GroupViewHolder)layout.getTag();
        } else {
            layout = (RelativeLayout)View.inflate(mContext, R.layout.group, null);
            holder = new GroupViewHolder();
            holder.mGroup = (TextView)layout.findViewById(R.id.text_group_name);
            layout.setTag(holder);
        }
        
        holder.mGroup.setText(mGroupList.get(position));

        return layout;
    }
}
