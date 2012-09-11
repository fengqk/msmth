package com.perfect.msmth.helper;

import android.widget.TabHost.TabContentFactory;
import android.content.Context;
import android.view.View;

public class TabFactory implements TabContentFactory {
    
    private Context mContext;
    
    public TabFactory(Context context){
        mContext = context;
    }
    
    @Override
    public View createTabContent(String tag){
        View v = new View(mContext);
        return v;
    }
}
