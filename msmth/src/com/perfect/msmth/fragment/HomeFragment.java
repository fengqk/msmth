package com.perfect.msmth.fragment;

import com.perfect.msmth.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.app.Activity;
import android.util.AttributeSet;

public class HomeFragment extends Fragment implements OnClickListener{
    private CharSequence mLabel;
    
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
        View view = inflater.inflate(R.layout.home, container, false);
        
        view.findViewById(R.id.image_topbar_post).setOnClickListener(this);
        view.findViewById(R.id.image_topbar_refresh).setOnClickListener(this);
        
        return view;
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
}
