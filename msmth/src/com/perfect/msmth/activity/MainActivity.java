package com.perfect.msmth.activity;

import com.perfect.msmth.R;
import com.perfect.msmth.helper.TabHelper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Menu;
import android.view.Window;
import android.widget.TabHost;
import android.widget.TabHost.TabContentFactory;
import android.widget.TabHost.OnTabChangeListener;

public class MainActivity extends FragmentActivity implements OnTabChangeListener, TabContentFactory {
    
    // tab tags
    public static final String TAB_TAG_HOME = "home";
    public static final String TAB_TAG_HOT = "hot";
    public static final String TAB_TAG_MSG = "msg";
    public static final String TAB_TAG_SEARCH = "search";
    public static final String TAB_TAG_MORE = "more";
    
    private TabHost mTabHost;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        
        // create tabs
        this.createTabHost();
    }

    @Override
    public View createTabContent(String tag) {
        // create tab view
        return TabHelper.createTabContent(this, tag);
    }
    
    @Override
    public void onTabChanged(String tabId){
        //switch tabs
        switchTabHost(tabId);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_home, menu);
        return true;
    }
    
    private void createTabHost(){
        mTabHost = (TabHost)findViewById(android.R.id.tabhost);
        mTabHost.setup();
        
        TabHelper.createTab(this, mTabHost);
        mTabHost.setOnTabChangedListener(this);
        
        switchTabHost(getString(R.string.label_navbar_more));
    }
    
    private void switchTabHost(String tabId){
        FragmentManager fm = getSupportFragmentManager();  
        Fragment f = fm.findFragmentByTag(tabId);
        if(f != null){
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(android.R.id.tabcontent, f);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.addToBackStack(null);
            ft.commit();
        }
        
        mTabHost.setCurrentTabByTag(tabId);
    }
}
