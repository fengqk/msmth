package com.perfect.msmth.mvc.model;

import com.perfect.msmth.helper.SmthHelper;
import com.perfect.msmth.mvc.Model;
import com.perfect.msmth.mvc.data.PostData;

import android.os.AsyncTask;

import java.util.List;

public class NewPostModel extends Model {

    private List<PostData> mPostList;
    
    public void update(String board) {
        new UpdateTask(this).execute(board);
    }
    
    public List<PostData> getPostList() {
        return mPostList;
    }
    
    public void setPostList(List<PostData> postList) {
        mPostList = postList;
    }
    
    private class UpdateTask extends AsyncTask<String, Void, List<PostData>> { 
        
        private NewPostModel mModel;
        
        public UpdateTask(NewPostModel model) {
            mModel = model;
        }
        
        @Override  
        protected void onPreExecute() {  
            super.onPreExecute();  
        }
        
        @Override
        protected List<PostData> doInBackground(String... params) {
            return SmthHelper.getInstance().getNewPostList(params[0]);
        }

        @Override
        protected void onPostExecute(List<PostData> result) {
            mModel.setPostList(result);
            mModel.notifyListenersDataUpdated();
        }
    }
}
