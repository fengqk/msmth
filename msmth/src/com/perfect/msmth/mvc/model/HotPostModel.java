package com.perfect.msmth.mvc.model;

import com.perfect.msmth.mvc.Model;
import com.perfect.msmth.mvc.data.PostData;
import com.perfect.msmth.helper.SmthHelper;

import java.util.List;

import android.os.AsyncTask;

public class HotPostModel extends Model {

    private List<PostData> mPostList;
    
    public void update(int pos) {
        new UpdateTask(this).execute(pos);
    }
    
    public List<PostData> getPostList() {
        return mPostList;
    }
    
    public void setPostList(List<PostData> postList) {
        mPostList = postList;
    }
    
    private class UpdateTask extends AsyncTask<Integer, Void, List<PostData>> { 
    
        private HotPostModel mModel;
        
        public UpdateTask(HotPostModel model) {
            mModel = model;
        }
        
        @Override  
        protected void onPreExecute() {  
            super.onPreExecute();  
        }
        
        @Override
        protected List<PostData> doInBackground(Integer... params) {
            return SmthHelper.getInstance().getHotPostList(params[0]);
        }

        @Override
        protected void onPostExecute(List<PostData> result) {
            mModel.setPostList(result);
            mModel.notifyListenersDataUpdated();
        }
    }
}
