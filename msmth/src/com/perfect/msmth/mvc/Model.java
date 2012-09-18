package com.perfect.msmth.mvc;

import java.util.List;
import java.util.ArrayList;

public abstract class Model {
 
    public interface OnModelDataListener{
        public abstract void onModelDataUpdated(Model model); 
    }
    
    private final List<OnModelDataListener> mListeners = new ArrayList<OnModelDataListener>();
    
    public void registerListener(OnModelDataListener listener){
        synchronized(mListeners){
            mListeners.add(listener);
        }
    }
    
    public void unregisterListener(OnModelDataListener listener){
        synchronized(mListeners){
            mListeners.remove(listener);
        }
    }
    
    public void notifyListenersDataUpdated(){
        synchronized(mListeners){
            for(OnModelDataListener listener : mListeners){
                listener.onModelDataUpdated(this);
            }
        }
    }
}
