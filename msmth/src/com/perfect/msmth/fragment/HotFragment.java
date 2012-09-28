package com.perfect.msmth.fragment;

import com.perfect.msmth.R;
import com.perfect.msmth.SmthApp;
import com.perfect.msmth.mvc.Model;
import com.perfect.msmth.mvc.Model.OnModelDataListener;
import com.perfect.msmth.mvc.model.HotPostModel;
import com.perfect.msmth.activity.TopicActivity;
import com.perfect.msmth.adapter.HotPostAdapter;
import com.perfect.msmth.adapter.GroupAdapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.LayoutInflater;
import android.app.Activity;
import android.content.Intent;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.SlidingDrawer;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class HotFragment extends Fragment implements OnModelDataListener {
    
    private LayoutInflater mInflater;
    private ViewGroup mContainer;
    
    private CharSequence mLabel;
   
    private HotPostModel mPostListModel;
    private ListView mPostListView;
    private HotPostAdapter mPostListAdapter;
    
    private int mCurrGroup = 0;
    private SlidingDrawer mGroupSlidingView; 
    private ListView mGroupListView;
    private GroupAdapter mGroupListAdapter;
    
    private HotFragment() {}
    
    public static HotFragment newInstance(CharSequence label) {
        HotFragment f = new HotFragment();

        Bundle args = new Bundle();
        args.putCharSequence("label", label);
        f.setArguments(args);

        return f;
    }
    
    @Override
    public void onInflate(Activity activity, AttributeSet attrs, Bundle savedInstanceState) {
        super.onInflate(activity, attrs, savedInstanceState);
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if(args != null){
            mLabel = args.getCharSequence("label");
        }
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mInflater = inflater;
        mContainer = container;
        
        return initView();
    }
   
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        
        mPostListModel.update(mCurrGroup);
    }
    
    @Override
    public void onDestroy() {
        mPostListModel.unregisterListener(this);
        
        super.onDestroy();
    }
    
    @Override
    public void onModelDataUpdated(Model model) {
        mPostListAdapter.setData(mPostListModel.getPostList());
        mPostListAdapter.notifyDataSetChanged();
        mPostListView.setSelectionAfterHeaderView();
    }
    
    private View initView() {
        View view = mInflater.inflate(R.layout.hot, mContainer, false);
        
        view.findViewById(R.id.image_topbar_back).setOnClickListener( new OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });
        
        view.findViewById(R.id.image_topbar_refresh).setOnClickListener( new OnClickListener() {
            @Override
            public void onClick(View view) {
                mPostListModel.update(mCurrGroup);
            }
        });
        
        mPostListModel = ((SmthApp)(getActivity()).getApplication()).getHotPostModel();
        mPostListModel.registerListener(this);
        
        mPostListAdapter = new HotPostAdapter(getActivity());
        mPostListAdapter.setData(mPostListModel.getPostList());
        
        mPostListView = (ListView)view.findViewById(R.id.list_hot_post);
        mPostListView.setAdapter(mPostListAdapter);
        mPostListView.setOnItemLongClickListener( new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(view.getContext(), TopicActivity.class);
                String url = ((TextView)view.findViewById(R.id.text_post_link)).getText().toString();
                intent.putExtra("POST_URL", url);
                startActivity(intent);
                return true;
            }
        });
        mPostListView.setOnItemClickListener( new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RelativeLayout contentView = (RelativeLayout)view.findViewById(R.id.layout_post_content);
                contentView.setVisibility(contentView.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
            }
        });
        
        mGroupSlidingView = (SlidingDrawer)view.findViewById(R.id.sliding_hot_group);
        mGroupListView = (ListView)view.findViewById(R.id.list_hot_group);
        
        mGroupListAdapter = new GroupAdapter(getActivity());
        mGroupListView.setAdapter(mGroupListAdapter);
        mGroupListView.setOnItemClickListener( new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(mCurrGroup != position) {
                    mPostListModel.update(position);
                    mCurrGroup = position;
                    mGroupSlidingView.close();
                }
            }
        });
        
        return view;
    }
}
