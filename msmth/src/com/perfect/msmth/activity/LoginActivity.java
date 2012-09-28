package com.perfect.msmth.activity;

import com.perfect.msmth.R;
import com.perfect.msmth.activity.MainActivity;
import com.perfect.msmth.helper.SmthHelper;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.content.Intent;

public class LoginActivity extends Activity{
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        
        initView();
    }
    
    private void initView() {
        findViewById(R.id.button_login_enter).setOnClickListener( new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(view.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        
        findViewById(R.id.button_login_guest).setOnClickListener( new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(view.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
