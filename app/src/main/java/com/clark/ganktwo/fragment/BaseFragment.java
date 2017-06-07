package com.clark.ganktwo.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.clark.ganktwo.R;
import com.clark.ganktwo.utils.Connectivities;

/**
 *
 * 本着练习的目的,使用了listView和recycleView
 *
 * Created by clark on 2016/12/5.
 */
public abstract class BaseFragment extends Fragment {

    /**
     * context 主activity
     */
    public Context context;
    /**
     * 提示加载
     */
    public ProgressBar mProgressBar;
    /**
     * 提示无网络
     */
    public TextView mNoNetwork;
    /**
     * recyclerView
     */
    public RecyclerView mRecyclerView;
    /**
     * listView
     */
    public ListView mListView;

    /**
     * 根View RelativeLayout
     */
    public RelativeLayout mRelativeLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        context = getActivity();
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return initView(inflater);
    }

    private View initView(LayoutInflater inflater){

        mRelativeLayout = (RelativeLayout) inflater.inflate(R.layout.fragment_base,null);
        mProgressBar = (ProgressBar) mRelativeLayout.findViewById(R.id.progressBar);
        mNoNetwork = (TextView) mRelativeLayout.findViewById(R.id.no_network);
        mRecyclerView = (RecyclerView) mRelativeLayout.findViewById(R.id.recycleView);
        mListView = (ListView) mRelativeLayout.findViewById(R.id.listView);
        // 网络不可用显示无法联网
        if (!Connectivities.isConnected(context)){
            mNoNetwork.setVisibility(View.VISIBLE);
        }
        return mRelativeLayout;
    }

    @Override
    public void onStart() {
        if (Connectivities.isConnected(context)){
            initData();
        }
        super.onStart();
    }

    public abstract void initData();
}
