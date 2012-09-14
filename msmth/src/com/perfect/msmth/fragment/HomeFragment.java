package com.perfect.msmth.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.app.Activity;
import android.util.AttributeSet;

public class HomeFragment extends Fragment {
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
    public void onInflate(Activity activity, AttributeSet attrs, Bundle savedInstanceState){
        super.onInflate(activity, attrs, savedInstanceState);

    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if(args != null){
            mLabel = args.getCharSequence("label");
        }
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mainview = new View(getActivity());
        return mainview;
    }
}
