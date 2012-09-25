package com.perfect.msmth.mvc.model;

import com.perfect.msmth.helper.SmthHelper;
import com.perfect.msmth.mvc.Model;

import java.lang.ref.SoftReference;   
import java.util.HashMap;  
  
import android.graphics.drawable.Drawable;  
import android.os.AsyncTask; 
import android.widget.ImageView; 

public class ImgPostModel extends Model {
    
    private static HashMap<String, SoftReference<Drawable>> sImageCache = null;  

    public void update(final String imageUrl, final ImageView imageView) {
        
        if(sImageCache == null){  
            sImageCache = new HashMap<String, SoftReference<Drawable>>();  
        }  
        
        if(sImageCache.containsKey(imageUrl)){  
            SoftReference<Drawable> soft = sImageCache.get(imageUrl);  
            Drawable draw = soft.get();  
            imageView.setImageDrawable(draw);  
            return;
        }
        
        new UpdateTask(this).execute(imageUrl, imageView);
    }
    
    private class UpdateTask extends AsyncTask<Object, Void, Object[]> { 
        
        private ImgPostModel mModel;
        
        public UpdateTask(ImgPostModel model) {
            mModel = model;
        }
        
        @Override  
        protected void onPreExecute() {  
            super.onPreExecute();  
        }
        
        @Override
        protected Object[] doInBackground(Object... params) {
            return new Object[]{SmthHelper.getInstance().getImage((String)params[0]), params[0], params[1]};
        }

        @Override
        protected void onPostExecute(Object[] result) {
            if(result[0] != null){
                ((ImageView)result[2]).setImageDrawable((Drawable)result[0]);
                mModel.sImageCache.put((String)result[1], new SoftReference<Drawable>((Drawable)result[0]));  
            }
        }
    }
}
