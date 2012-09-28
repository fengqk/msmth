package com.perfect.msmth.adapter;

import com.perfect.msmth.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class BoardAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> mBoardList;
    
    private class BoardViewHolder {
        public TextView mBoard;
    }
    
    public BoardAdapter(Context context) {
        mContext = context;
        mBoardList = new ArrayList<String>();
        mBoardList.add("Joke");
        mBoardList.add("TVShow");
        mBoardList.add("Picture");
        mBoardList.add("WorldSoccer");
        mBoardList.add("FamilyLife");
        mBoardList.add("Age");
    }
    
    @Override
    public int getCount() {
        return (mBoardList == null) ? 0 : mBoardList.size();
    }

    @Override
    public Object getItem(int position) {
        position = (position >= mBoardList.size()) ? position - 1 : position;
        return mBoardList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RelativeLayout layout = null;
        BoardViewHolder holder = null;
        
        if(convertView != null){
            layout = (RelativeLayout)convertView;
            holder = (BoardViewHolder)layout.getTag();
        } else {
            layout = (RelativeLayout)View.inflate(mContext, R.layout.board, null);
            holder = new BoardViewHolder();
            holder.mBoard = (TextView)layout.findViewById(R.id.text_board_name);
            layout.setTag(holder);
        }
        
        holder.mBoard.setText(mBoardList.get(position));

        return layout;
    }

}
