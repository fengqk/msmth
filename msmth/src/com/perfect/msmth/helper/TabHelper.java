package com.perfect.msmth.helper;

import com.perfect.msmth.R;
import com.perfect.msmth.activity.MainActivity;
import com.perfect.msmth.fragment.HomeFragment;
import com.perfect.msmth.fragment.HotFragment;
import com.perfect.msmth.fragment.MsgFragment;
import com.perfect.msmth.fragment.SearchFragment;
import com.perfect.msmth.fragment.MoreFragment;

import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Gravity;
import android.content.res.Resources;

public class TabHelper {
    
    public static void createTab(MainActivity context, TabHost tabHost){
        String tags[] = context.getResources().getStringArray(R.array.label_navbar_tags);
        
        TabSpec tSpecHome = tabHost.newTabSpec(tags[0]);
        View viewHome = TabHelper.createTabLayout(context, tags[0], R.drawable.navbar_home);
        tSpecHome.setIndicator(viewHome);        
        tSpecHome.setContent(context);
        tabHost.addTab(tSpecHome);
        
        TabSpec tSpecHot = tabHost.newTabSpec(tags[1]);
        View viewHot = TabHelper.createTabLayout(context, tags[1], R.drawable.navbar_hot);
        tSpecHot.setIndicator(viewHot);        
        tSpecHot.setContent(context);
        tabHost.addTab(tSpecHot);

        TabSpec tSpecMsg = tabHost.newTabSpec(tags[2]);
        View viewMsg = TabHelper.createTabLayout(context, tags[2], R.drawable.navbar_msg);
        tSpecMsg.setIndicator(viewMsg);        
        tSpecMsg.setContent(context);
        tabHost.addTab(tSpecMsg);
 
        TabSpec tSpecSearch = tabHost.newTabSpec(tags[3]);
        View viewSearch = TabHelper.createTabLayout(context, tags[3], R.drawable.navbar_search);
        tSpecSearch.setIndicator(viewSearch);        
        tSpecSearch.setContent(context);
        tabHost.addTab(tSpecSearch);
  
        TabSpec tSpecMore = tabHost.newTabSpec(tags[4]);
        View viewMore = TabHelper.createTabLayout(context, tags[4], R.drawable.navbar_more);
        tSpecMore.setIndicator(viewMore);        
        tSpecMore.setContent(context);
        tabHost.addTab(tSpecMore);
    }
    
    public static View createTabContent(MainActivity context, String tag){
        FragmentManager fm = context.getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
 
        if(tag.equalsIgnoreCase(context.getString(R.string.label_navbar_home))){
            HomeFragment f = (HomeFragment)fm.findFragmentByTag(tag);
            if(f == null){
                f = HomeFragment.newInstance(tag);
                ft.add(android.R.id.tabcontent, f, tag);
                ft.commit();
            }
        }else if(tag.equalsIgnoreCase(context.getString(R.string.label_navbar_hot))){
            HotFragment f = (HotFragment)fm.findFragmentByTag(tag);
            if(f == null){
                f = HotFragment.newInstance(tag);
                ft.add(android.R.id.tabcontent, f, tag);
                ft.commit();
            }
        }else if(tag.equalsIgnoreCase(context.getString(R.string.label_navbar_msg))){
            MsgFragment f = (MsgFragment)fm.findFragmentByTag(tag);
            if(f == null){
                f = MsgFragment.newInstance(tag);
                ft.add(android.R.id.tabcontent, f, tag);
                ft.commit();
            }
        }else if(tag.equalsIgnoreCase(context.getString(R.string.label_navbar_search))){
            SearchFragment f = (SearchFragment)fm.findFragmentByTag(tag);
            if(f == null){
                f = SearchFragment.newInstance(tag);
                ft.add(android.R.id.tabcontent, f, tag);
                ft.commit();
            }
        }else if(tag.equalsIgnoreCase(context.getString(R.string.label_navbar_more))){
            MoreFragment f = (MoreFragment)fm.findFragmentByTag(tag);
            if(f == null){
                f = MoreFragment.newInstance(tag);
                ft.add(android.R.id.tabcontent, f, tag);
                ft.commit();
            }
        }else{
            return null;
        }
        
        fm.executePendingTransactions();
        return new View(context);
    }
    
    public static View createTabLayout(MainActivity context, String text, int drawableId){
        Resources res = context.getResources();
        
        LinearLayout layout = new LinearLayout(context);  
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setBackgroundDrawable(res.getDrawable(R.drawable.navbar_bg));
        
        ImageView iv = new ImageView(context);
        iv.setImageDrawable(res.getDrawable(drawableId)); 
        layout.addView(iv, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));  
     
        TextView tv = new TextView(context);  
        tv.setGravity(Gravity.CENTER);  
        tv.setSingleLine(true);  
        tv.setText(text);
        tv.setTextSize(10);
        tv.setTextColor(res.getColorStateList(R.color.navbar_text));
        layout.addView(tv,new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));  
        
        return layout;
    }
}
