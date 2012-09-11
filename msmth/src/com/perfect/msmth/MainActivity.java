package com.perfect.msmth;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.widget.TabHost;

import com.perfect.msmth.helper.TabFactory;

public class MainActivity extends FragmentActivity {

    private TabHost mTabHost;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // init tabs
        initTabHost();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_home, menu);
        return true;
    }
    
    public void initTabHost(){
        mTabHost = (TabHost)this.findViewById(android.R.id.tabhost);
        mTabHost.setup();
        
        // home tab
        TabHost.TabSpec tSpecHome = mTabHost.newTabSpec("");
        tSpecHome.setIndicator("Android",getResources().getDrawable(R.drawable.navbar_home));        
        tSpecHome.setContent(new TabFactory(getBaseContext()));
        mTabHost.addTab(tSpecHome);
        
        // hot tab
        TabHost.TabSpec tSpecHot = mTabHost.newTabSpec("");
        tSpecHot.setIndicator("Android",getResources().getDrawable(R.drawable.navbar_hot));        
        tSpecHot.setContent(new TabFactory(getBaseContext()));
        mTabHost.addTab(tSpecHot);
        
        // msg tab
        TabHost.TabSpec tSpecMsg = mTabHost.newTabSpec("");
        tSpecMsg.setIndicator("Android",getResources().getDrawable(R.drawable.navbar_msg));        
        tSpecMsg.setContent(new TabFactory(getBaseContext()));
        mTabHost.addTab(tSpecMsg);
        
        // search tab
        TabHost.TabSpec tSpecSearch = mTabHost.newTabSpec("");
        tSpecSearch.setIndicator("Android",getResources().getDrawable(R.drawable.navbar_search));        
        tSpecSearch.setContent(new TabFactory(getBaseContext()));
        mTabHost.addTab(tSpecSearch);
        
        // more tab
        TabHost.TabSpec tSpecMore = mTabHost.newTabSpec("");
        tSpecMore.setIndicator("Android",getResources().getDrawable(R.drawable.navbar_more));        
        tSpecMore.setContent(new TabFactory(getBaseContext()));
        mTabHost.addTab(tSpecMore);
    }
}
