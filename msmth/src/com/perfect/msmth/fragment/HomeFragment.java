package com.perfect.msmth.fragment;

import com.perfect.msmth.R;
import com.perfect.msmth.SmthApp;
import com.perfect.msmth.adapter.NewPostAdapter;
import com.perfect.msmth.mvc.Model;
import com.perfect.msmth.mvc.Model.OnModelDataListener;
import com.perfect.msmth.mvc.model.NewPostModel;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.app.Activity;
import android.util.AttributeSet;

public class HomeFragment extends Fragment implements OnClickListener, OnModelDataListener{
    
    private LayoutInflater mInflater;
    private ViewGroup mContainer;
    
    private CharSequence mLabel;
    private NewPostModel mPostListModel;
    private ListView mPostListView;
    private NewPostAdapter mPostListAdapter;
    
    private HomeFragment() {}
    
    public static HomeFragment newInstance(CharSequence label) {
        HomeFragment f = new HomeFragment();

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
        
        mPostListModel.update();
    }
    
    @Override
    public void onDestroy() {
        mPostListModel.unregisterListener(this);
        
        super.onDestroy();
    }
    
    @Override
    public void onClick(View view) {
        switch(view.getId()){
        case R.id.image_topbar_post:{
            
        }break;
        case R.id.image_topbar_refresh:{
            
        }break; 
        }
    }
    
    @Override
    public void onModelDataUpdated(Model model) {
        mPostListAdapter.setData(mPostListModel.getPostList());
        mPostListAdapter.notifyDataSetChanged();
    }
    
    private View initView() {
        View view = mInflater.inflate(R.layout.home, mContainer, false);
        
        view.findViewById(R.id.image_topbar_post).setOnClickListener(this);
        view.findViewById(R.id.image_topbar_refresh).setOnClickListener(this);
              
        mPostListModel = ((SmthApp)getActivity().getApplication()).getNewPostModel();
        mPostListModel.registerListener(this);
        
        mPostListAdapter = new NewPostAdapter(this, mInflater);
        mPostListAdapter.setData(mPostListModel.getPostList());
        
        mPostListView = (ListView)view.findViewById(R.id.list_new_post);
        mPostListView.setAdapter(mPostListAdapter);
        
        return view;
    }
}
