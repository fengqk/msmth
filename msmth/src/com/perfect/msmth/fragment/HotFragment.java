package com.perfect.msmth.fragment;

import com.perfect.msmth.R;
import com.perfect.msmth.SmthApp;
import com.perfect.msmth.mvc.Model;
import com.perfect.msmth.mvc.Model.OnModelDataListener;
import com.perfect.msmth.mvc.model.HotPostModel;
import com.perfect.msmth.adapter.HotPostAdapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.LayoutInflater;
import android.app.Activity;
import android.util.AttributeSet;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

public class HotFragment extends Fragment implements OnClickListener, OnItemSelectedListener, OnModelDataListener {
    
    private LayoutInflater mInflater;
    private ViewGroup mContainer;
    
    private CharSequence mLabel;
    private HotPostModel mPostListModel;
    private ListView mPostListView;
    private HotPostAdapter mPostListAdapter;
    private int mSelectedPos = 0;
    
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
        
        mPostListModel.update(mSelectedPos);
    }
    
    @Override
    public void onDestroy() {
        mPostListModel.unregisterListener(this);
        
        super.onDestroy();
    }
    
    @Override
    public void onClick(View view) {
        switch(view.getId()){
        case R.id.image_topbar_back:{
            
        }break;
        case R.id.image_topbar_refresh:{
            mPostListModel.update(mSelectedPos);
        }break; 
        }
    }
    
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        if(mSelectedPos != pos) {
            mPostListModel.update(pos);
            mSelectedPos = pos;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
    
    @Override
    public void onModelDataUpdated(Model model) {
        mPostListAdapter.setData(mPostListModel.getPostList());
        mPostListAdapter.notifyDataSetChanged();
    }
    
    private View initView() {
        View view = mInflater.inflate(R.layout.hot, mContainer, false);
        
        view.findViewById(R.id.image_topbar_back).setOnClickListener(this);
        view.findViewById(R.id.image_topbar_refresh).setOnClickListener(this);
        
        Spinner spinner = (Spinner)view.findViewById(R.id.spinner_topbar_hot_group);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.label_hot_group, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        
        mPostListModel = ((SmthApp)getActivity().getApplication()).getHotPostModel();
        mPostListModel.registerListener(this);
        
        mPostListAdapter = new HotPostAdapter(this, mInflater);
        mPostListAdapter.setData(mPostListModel.getPostList());
        
        mPostListView = (ListView)view.findViewById(R.id.list_hot_post);
        mPostListView.setAdapter(mPostListAdapter);
        
        return view;
    }
    
}
