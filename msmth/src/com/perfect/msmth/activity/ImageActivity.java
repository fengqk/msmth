package com.perfect.msmth.activity;

import com.perfect.msmth.R;
import com.perfect.msmth.SmthApp;
import com.perfect.msmth.mvc.model.ImgPostModel;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class ImageActivity extends Activity implements OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image);

        // set view widgets and events
        initView();
    }
    
    @Override
    public void onClick(View view) {
        finish();
    }
    
    private void initView() {
        ImageView imageView = (ImageView)findViewById(R.id.image_attachment);
        imageView.setOnClickListener(this);
        
        ImgPostModel imgModel = ((SmthApp)getApplication()).getImgPostModel();
        imgModel.update(getIntent().getStringExtra("IMAGE_SRC_URL"), imageView);
    }
}
