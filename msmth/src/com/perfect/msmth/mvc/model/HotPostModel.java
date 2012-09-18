package com.perfect.msmth.mvc.model;

import com.perfect.msmth.mvc.Model;
import com.perfect.msmth.mvc.data.PostData;
import com.perfect.msmth.helper.SmthHelper;

import java.util.List;

import android.os.AsyncTask;

public class HotPostModel extends Model {

    private List<PostData> mPostList;
    
    public void update() {
        new UpdateTask(this).execute();
    }
    
    public List<PostData> getPostList() {
        return mPostList;
    }
    
    public void setPostList(List<PostData> postList) {
        mPostList = postList;
    }
    
    class UpdateTask extends AsyncTask<Void, Void, List<PostData>> { 
    
        private HotPostModel mModel;
        
        public UpdateTask(HotPostModel model) {
            mModel = model;
        }
        
        @Override  
        protected void onPreExecute() {  
            super.onPreExecute();  
        }
        
        @Override
        protected List<PostData> doInBackground(Void... params) {
            return SmthHelper.getInstance().getHotPostList();
        }

        @Override
        protected void onPostExecute(List<PostData> result) {
            mModel.setPostList(result);
            mModel.notifyListenersDataUpdated();
        }
    }
}
