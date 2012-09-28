package com.perfect.msmth.fragment;

import com.perfect.msmth.R;
import com.perfect.msmth.SmthApp;
import com.perfect.msmth.activity.TopicActivity;
import com.perfect.msmth.adapter.BoardAdapter;
import com.perfect.msmth.adapter.NewPostAdapter;
import com.perfect.msmth.mvc.Model;
import com.perfect.msmth.mvc.Model.OnModelDataListener;
import com.perfect.msmth.mvc.model.NewPostModel;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.SlidingDrawer;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.app.Activity;
import android.content.Intent;
import android.util.AttributeSet;

public class HomeFragment extends Fragment implements OnModelDataListener{
    
    private LayoutInflater mInflater;
    private ViewGroup mContainer;
    
    private CharSequence mLabel;
    
    private NewPostModel mPostListModel;
    private ListView mPostListView;
    private NewPostAdapter mPostListAdapter;
    
    private String mCurrBoard;
    private SlidingDrawer mBoardSlidingView; 
    private ListView mBoardListView;
    private BoardAdapter mBoardListAdapter;
    
    private HomeFragment() {}
    
    public static HomeFragment newInstance(CharSequence label) {
        HomeFragment f = new HomeFragment();

        Bundle args = new Bundle();
        args.putCharSequence("label", label);
        f.setArguments(args);

        return f;
    }
    
    @Override
    public void onInflate(Activity activity, AttributeSet attrs, Bundle savedInstanceState) {
        super.onInflate(activity, attrs, savedInstanceState);

    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if(args != null){
            mLabel = args.getCharSequence("label");
        }
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mInflater = inflater;
        mContainer = container;
        
        return initView();
    }
    
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        
        mPostListModel.update(mCurrBoard);
    }
    
    @Override
    public void onDestroy() {
        mPostListModel.unregisterListener(this);
        
        super.onDestroy();
    }
    
    @Override
    public void onModelDataUpdated(Model model) {
        mPostListAdapter.setData(mPostListModel.getPostList());
        mPostListAdapter.notifyDataSetChanged();
        mPostListView.setSelectionAfterHeaderView();
        // mPostListView.requestFocus();
    }
    
    private View initView() {
        View view = mInflater.inflate(R.layout.home, mContainer, false);
        
        view.findViewById(R.id.image_topbar_post).setOnClickListener( new OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });
        
        view.findViewById(R.id.image_topbar_refresh).setOnClickListener( new OnClickListener() {
            @Override
            public void onClick(View view) {
                mPostListModel.update(mCurrBoard);
            }
        });
        
        mPostListModel = ((SmthApp)getActivity().getApplication()).getNewPostModel();
        mPostListModel.registerListener(this);
        
        mPostListAdapter = new NewPostAdapter(getActivity());
        mPostListAdapter.setData(mPostListModel.getPostList());
        
        mPostListView = (ListView)view.findViewById(R.id.list_new_post);
        mPostListView.setAdapter(mPostListAdapter);
        mPostListView.setOnItemLongClickListener( new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(view.getContext(), TopicActivity.class);
                String url = ((TextView)view.findViewById(R.id.text_post_link)).getText().toString();
                intent.putExtra("POST_URL", url);
                startActivity(intent);
                return true;
            }
        });
        mPostListView.setOnItemClickListener( new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RelativeLayout contentView = (RelativeLayout)view.findViewById(R.id.layout_post_content);
                contentView.setVisibility(contentView.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
                
            }
        });
        
        mBoardSlidingView = (SlidingDrawer)view.findViewById(R.id.sliding_fav_board);
        mBoardListView = (ListView)view.findViewById(R.id.list_fav_board);
        
        mBoardListAdapter = new BoardAdapter(getActivity());
        mBoardListView.setAdapter(mBoardListAdapter);
        mBoardListView.setOnItemClickListener( new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String board = ((TextView)view.findViewById(R.id.text_board_name)).getText().toString();
                if(mCurrBoard != board) {
                    mPostListModel.update(board);
                    mCurrBoard = board;
                    mBoardSlidingView.close();
                }
            }
        });
        
        mCurrBoard = "Joke";
        
        return view;
    }
}
