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
import android.widget.TabHost;
import android.widget.TabHost.TabContentFactory;
import android.widget.TabHost.OnTabChangeListener;

public class MainActivity extends FragmentActivity implements TabContentFactory {
    
    private TabHost mTabHost;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        initView();
    }

    @Override
    public View createTabContent(String tag) {
        return TabHelper.createTabContent(this, tag);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_home, menu);
        return true;
    }
    
    private void initView() {
        createTabHost();
    }
    
    private void createTabHost(){
        mTabHost = (TabHost)findViewById(android.R.id.tabhost);
        mTabHost.setup();
        
        TabHelper.createTab(this, mTabHost);
        mTabHost.setOnTabChangedListener( new OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId){
                switchTabHost(tabId);
            }
        });
        
        switchTabHost(getString(R.string.label_navbar_search));
    }
    
    private void switchTabHost(String tabId){
        FragmentManager fm = getSupportFragmentManager();  
        Fragment f = fm.findFragmentByTag(tabId);
        if(f != null){
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(android.R.id.tabcontent, f);
            ft.setTransition(FragmentTransaction.TRANSIT_NONE);
            ft.addToBackStack(null);
            ft.commit();
        }
        
        mTabHost.setCurrentTabByTag(tabId);
    }
}
