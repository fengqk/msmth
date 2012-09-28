package com.perfect.msmth.fragment;

import com.perfect.msmth.R;
import com.perfect.msmth.activity.MainActivity;
import com.perfect.msmth.helper.XmlHelper;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.app.Activity;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.AutoCompleteTextView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class SearchFragment extends Fragment {
    
    private LayoutInflater mInflater;
    private ViewGroup mContainer;
    
    private CharSequence mLabel;
    
    private SearchFragment() {}
    
    public static SearchFragment newInstance(CharSequence label) {
        SearchFragment f = new SearchFragment();

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
    
    private View initView() {
        View view = mInflater.inflate(R.layout.search, mContainer, false);
        
        List<String> boardList = XmlHelper.parseBoardList(getActivity());
        String[] boards = boardList.toArray(new String[boardList.size()]); 
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.search_hint, boards);
        AutoCompleteTextView auto = (AutoCompleteTextView)view.findViewById(R.id.edit_search_group);
        auto.setAdapter(adapter);
        
        view.findViewById(R.id.edit_search_group).setOnClickListener( new OnClickListener() {
            @Override
            public void onClick(View view) {
                String board = ((TextView)view).getText().toString();
                
                HomeFragment homeFragment = (HomeFragment)getFragmentManager().findFragmentByTag(view.getContext().getString(R.string.label_navbar_home));
                
            }
        });
        
        return view;
    }
 
}
