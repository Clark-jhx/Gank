package com.clark.ganktwo.bean;

import java.util.List;

/**
 * 代表服务器返回的 休息视频
 * 形式如下
 * Created by clark on 2016/12/6.
 */

//    {
//
//        "count": 1,
//        "error": false,
//        "results": [
//            {
//                "desc": "街头极限健身Greg Plitt 格雷格普利特励志演讲, Greg Plitt是一传奇人物 ",
//                "ganhuo_id": "56cc6d1d421aa95caa707541",
//                "publishedAt": "2015-10-22T02:06:07.744000",
//                "readability": "",
//                "type": "休息视频",
//                "url": "http://v.youku.com/v_show/id_XMTM2NDM2MTcxNg",
//                "who": "andyiac"
//            }
//        ]
//
//    }

public class VideoInfo {

    public int count;
    public boolean error;
    public List<ResultsBean> results;

    public static class ResultsBean {

        public String desc;
        public String ganhuo_id;
        public String publishedAt;
        public String readability;
        public String type;
        public String url;
        public String who;
    }
}
