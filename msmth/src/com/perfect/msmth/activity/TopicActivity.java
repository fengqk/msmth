package com.perfect.msmth.activity;

import com.perfect.msmth.R;
import com.perfect.msmth.SmthApp;
import com.perfect.msmth.adapter.HotPostAdapter;
import com.perfect.msmth.adapter.RepPostAdapter;
import com.perfect.msmth.mvc.Model;
import com.perfect.msmth.mvc.Model.OnModelDataListener;
import com.perfect.msmth.mvc.model.RepPostModel;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class TopicActivity extends Activity implements OnModelDataListener{
    
    private RepPostModel mPostListModel;
    private ListView mPostListView;
    private RepPostAdapter mPostListAdapter;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.topic);
        
        initView();
    }
    
    @Override
    public void onModelDataUpdated(Model model) {
        mPostListAdapter.setData(mPostListModel.getPostList());
        mPostListAdapter.notifyDataSetChanged();
    }
    
    private void initView() {      
        mPostListModel = ((SmthApp)getApplication()).getRepPostModel();
        mPostListModel.registerListener(this);
        
        mPostListAdapter = new RepPostAdapter(this);
        mPostListAdapter.setData(mPostListModel.getPostList());
        
        mPostListView = (ListView)findViewById(R.id.list_rep_post);
        mPostListView.setAdapter(mPostListAdapter);
        
        mPostListModel.update(getIntent().getStringExtra("POST_URL"));
    }
}
