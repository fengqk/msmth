package com.perfect.msmth.activity;

import com.perfect.msmth.R;
import com.perfect.msmth.activity.MainActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.View;
import android.view.View.OnClickListener;
import android.content.Intent;

public class LoginActivity extends Activity implements OnClickListener {
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        
        // set view widgets and events
        initView();
    }
    
    @Override
    public void onClick(View view) {
        switch(view.getId()){
        case R.id.button_login_enter:{
            Intent intent = new Intent();
            intent.setClass(view.getContext(), MainActivity.class);
            startActivity(intent);
        }break;
        case R.id.button_login_guest:{
            Intent intent = new Intent();
            intent.setClass(view.getContext(), MainActivity.class);
            startActivity(intent);
        }break;
        }
    }
    
    private void initView() {
        findViewById(R.id.button_login_enter).setOnClickListener(this);
        findViewById(R.id.button_login_guest).setOnClickListener(this);
    }
}
