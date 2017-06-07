package com.clark.ganktwo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.clark.ganktwo.R;
import com.clark.ganktwo.bean.VideoInfo;
import com.clark.ganktwo.settings.GankSettings;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * tab 休息视频
 * 使用listView
 *
 * Created by clark on 2016/12/5.
 */
public class VideoFragment extends BaseFragment {

    /**
     * url地址
     */
    public String url = GankSettings.BASE_URL + GankSettings.CATEGORY_VIDEO + GankSettings.COUNT_URL + "10" + GankSettings.PAGE_URL + "1";

    public VideoInfo mVideoInfo;

    /**
     * 联网获得数据
     */
    @Override
    public void initData() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                mVideoInfo = new Gson().fromJson(string,VideoInfo.class);
                mListView.post(new Runnable() {
                    @Override
                    public void run() {
                        initListView();
                    }
                });

            }
        });
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // 显示listView
        mListView.setVisibility(View.VISIBLE);
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * 设置listView
     */
    private void initListView() {
        VideoAdapter mVideoAdapter = new VideoAdapter();
        mListView.setAdapter(mVideoAdapter);
    }


    /**
     * adapter
     */
    private class VideoAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            if (mVideoInfo != null){
                return mVideoInfo.count;
            }
            return 0;
        }

        @Override
        public Object getItem(int i) {
            if (mVideoInfo != null && mVideoInfo.count > 0){
                return mVideoInfo.results.get(i);
            }
            return null;
        }

        @Override
        public long getItemId(int i) {

            return 0;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {
            View view = null;
            ViewHolder viewHolder = new ViewHolder();
            if (view == null){
                view = View.inflate(context, R.layout.item_video, null);
                viewHolder.title = (TextView) view.findViewById(R.id.title_video);
                viewHolder.author = (TextView) view.findViewById(R.id.author_video);
                viewHolder.time = (TextView) view.findViewById(R.id.time_video);
                view.setTag(viewHolder);
            }else {
                viewHolder = (ViewHolder) convertView.getTag();
                view = convertView;
            }

            viewHolder.title.setText(((VideoInfo.ResultsBean)getItem(i)).desc);
            viewHolder.author.setText(((VideoInfo.ResultsBean)getItem(i)).who);
            // TODO 时间刷新时间
            //viewHolder.title.setText(((VideoInfo.ResultsBean)getItem(i)).desc);

            return view;
        }
    }

    static class ViewHolder{
        TextView title;
        TextView author;
        TextView time;
    }
}
