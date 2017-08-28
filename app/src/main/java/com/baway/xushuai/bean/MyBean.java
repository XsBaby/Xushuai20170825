package com.baway.xushuai.bean;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * date:2017/8/28
 * author:徐帅(acer)
 * funcation: 实体类  自定义属性名用@SerializedName
 */
public class MyBean {

    /**
     * data : [{"Comment[]":[{"content":"This is a Content...-3","date":"2017-02-01 19:20:50.0","id":3,"momentId":15,"toId":0,"userId":82002},{"content":"thank you","date":"2017-03-25 20:28:03.0","id":176,"momentId":15,"toId":166,"userId":38710}],"Moment":{"content":"APIJSON is a JSON Transmission Structure Protocol\u2026","date":"2017-02-08 16:06:11.0","id":15,"pictureList":["http://static.oschina.net/uploads/user/1218/2437072_100.jpg?t=1461076033000","http://common.cnblogs.com/images/icon_weibo_24.png"],"praiseUserIdList":[82055,82002,82001],"userId":70793},"User":{"head":"http://static.oschina.net/uploads/user/585/1170143_50.jpg?t=1390226446000","id":70793,"name":"Strong"}},{"Comment[]":[{"content":"This is a Content...-13","date":"2017-02-01 19:20:50.0","id":13,"momentId":58,"toId":0,"userId":82005},{"content":"This is a Content...-77","date":"2017-02-01 19:20:50.0","id":77,"momentId":58,"toId":13,"userId":93793}],"Moment":{"content":"This is a Content...-435","date":"2017-02-01 19:14:31.0","id":58,"pictureList":["http://static.oschina.net/uploads/img/201604/22172507_aMmH.jpg"],"praiseUserIdList":[38710,82003,82005,93793,82006,82044,82001],"userId":90814},"User":{"head":"http://static.oschina.net/uploads/user/51/102723_50.jpg?t=1449212504000","id":90814,"name":"007"}},{"Comment[]":[{"content":"This is a Content...-44","date":"2017-02-01 19:20:50.0","id":44,"momentId":170,"toId":0,"userId":82003},{"content":"This is a Content...-54","date":"2017-02-01 19:20:50.0","id":54,"momentId":170,"toId":0,"userId":82004}],"Moment":{"content":"This is a Content...-73","date":"2017-02-01 19:14:31.0","id":170,"pictureList":["http://static.oschina.net/uploads/img/201604/22172508_eGDi.jpg","http://static.oschina.net/uploads/img/201604/22172508_eGDi.jpg","http://static.oschina.net/uploads/img/201604/22172508_mpwj.jpg"],"praiseUserIdList":[82044,82002,82001],"userId":70793},"User":{"head":"http://static.oschina.net/uploads/user/585/1170143_50.jpg?t=1390226446000","id":70793,"name":"Strong"}},{"Comment[]":[{"content":"This is a Content...-45","date":"2017-02-01 19:20:50.0","id":45,"momentId":301,"toId":0,"userId":93793},{"content":"This is a Content...-51","date":"2017-02-01 19:20:50.0","id":51,"momentId":301,"toId":45,"userId":82003}],"Moment":{"content":"This is a Content...-301","date":"2017-02-01 19:14:31.0","id":301,"pictureList":["http://static.oschina.net/uploads/img/201604/22172508_eGDi.jpg"],"praiseUserIdList":[38710,93793,82003,82005,82040,82055,82002,82001],"userId":93793},"User":{"head":"http://static.oschina.net/uploads/user/48/96331_50.jpg","id":93793,"name":"Mike"}},{"Comment[]":[{"content":"This is a Content...-68","date":"2017-02-01 19:20:50.0","id":68,"momentId":371,"toId":0,"userId":82005},{"content":"This is a Content...-110","date":"2017-02-01 19:23:24.0","id":110,"momentId":371,"toId":0,"userId":93793}],"Moment":{"content":"This is a Content...-371","date":"2017-02-01 19:14:31.0","id":371,"pictureList":["http://static.oschina.net/uploads/img/201604/22172507_rrZ5.jpg","http://static.oschina.net/uploads/img/201604/22172507_rrZ5.jpg","https://camo.githubusercontent.com/c98b1c86af136745cc4626c6ece830f76de9ee83/687474703a2f2f696d61676573323031352e636e626c6f67732e636f6d2f626c6f672f3636303036372f3230313630342f3636303036372d32303136303431343232343930383036362d313837323233393236352e6a7067","http://static.oschina.net/uploads/img/201604/22172507_aMmH.jpg","http://static.oschina.net/uploads/img/201604/22172508_eGDi.jpg"],"praiseUserIdList":[90814,93793,82003,82005,82006,82040,82002,82001],"userId":82002},"User":{"head":"http://static.oschina.net/uploads/user/1174/2348263_50.png?t=1439773471000","id":82002,"name":"Happy~"}},{"Comment[]":[{"content":"This is a Content...-4","date":"2017-02-01 19:20:50.0","id":4,"momentId":470,"toId":0,"userId":38710},{"content":"This is a Content...-22","date":"2017-02-01 19:20:50.0","id":22,"momentId":470,"toId":221,"userId":82001}],"Moment":{"content":"This is a Content...-470","date":"2017-02-01 19:14:31.0","id":470,"pictureList":["http://static.oschina.net/uploads/img/201604/22172507_Pz9Y.png"],"praiseUserIdList":[],"userId":38710},"User":{"head":"http://static.oschina.net/uploads/user/1218/2437072_100.jpg?t=1461076033000","id":38710,"name":"TommyLemon"}},{"Comment[]":[{"content":"I want one","date":"2017-03-30 16:46:03.0","id":1490863563124,"momentId":704,"toId":0,"userId":82003},{"content":"哈哈","date":"2017-08-13 01:56:23.0","id":1502560583160,"momentId":704,"toId":1490863563124,"userId":82001}],"Moment":{"content":"APIJSON is a JSON Transmission Structure Protocol\u2026","date":"2017-03-12 17:39:44.0","id":704,"pictureList":["http://static.oschina.net/uploads/user/629/1258821_50.jpg?t=1378063141000","http://static.oschina.net/uploads/user/1332/2664107_50.jpg?t=1457405500000"],"praiseUserIdList":[82003,82002,82001],"userId":38710},"User":{"head":"http://static.oschina.net/uploads/user/1218/2437072_100.jpg?t=1461076033000","id":38710,"name":"TommyLemon"}},{"Comment[]":[{"content":"How do you do","date":"2017-04-03 16:56:03.0","id":1491209763162,"momentId":1491200468898,"toId":0,"userId":82001},{"content":"Wow","date":"2017-04-04 21:26:22.0","id":1491312382941,"momentId":1491200468898,"toId":0,"userId":82001}],"Moment":{"content":"APIJSON, let interfaces go to hell!","date":"2017-04-03 14:21:08.0","id":1491200468898,"pictureList":["http://static.oschina.net/uploads/user/998/1997902_50.jpg?t=1407806577000","http://static.oschina.net/uploads/user/1200/2400261_50.png?t=1439638750000"],"praiseUserIdList":[82001,38710],"userId":38710},"User":{"head":"http://static.oschina.net/uploads/user/1218/2437072_100.jpg?t=1461076033000","id":38710,"name":"TommyLemon"}},{"Comment[]":[{"content":"六级","date":"2017-07-25 17:25:49.0","id":1500974749796,"momentId":1493835799335,"toId":0,"userId":82001},{"content":"回来了","date":"2017-07-25 17:25:55.0","id":1500974755007,"momentId":1493835799335,"toId":1500974749796,"userId":82001}],"Moment":{"content":"APIJSON is a JSON Transmission Structure Protocol\u2026","date":"2017-05-04 02:23:19.0","id":1493835799335,"pictureList":["http://static.oschina.net/uploads/user/1218/2437072_100.jpg?t=1461076033000","http://common.cnblogs.com/images/icon_weibo_24.png"],"praiseUserIdList":[82001,82002],"userId":38710},"User":{"head":"http://static.oschina.net/uploads/user/1218/2437072_100.jpg?t=1461076033000","id":38710,"name":"TommyLemon"}}]
     * code : 200
     * msg : success
     */

    public int code;
    public String msg;
    @SerializedName("[]")
    public List<DataBean> data;

    public static MyBean objectFromData(String str) {

        return new Gson().fromJson(str, MyBean.class);
    }

    public static MyBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), MyBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    // FIXME generate failure  field _$Comment300
    public static class DataBean {
        /**
         * Comment[] : [{"content":"This is a Content...-3","date":"2017-02-01 19:20:50.0","id":3,"momentId":15,"toId":0,"userId":82002},{"content":"thank you","date":"2017-03-25 20:28:03.0","id":176,"momentId":15,"toId":166,"userId":38710}]
         * Moment : {"content":"APIJSON is a JSON Transmission Structure Protocol\u2026","date":"2017-02-08 16:06:11.0","id":15,"pictureList":["http://static.oschina.net/uploads/user/1218/2437072_100.jpg?t=1461076033000","http://common.cnblogs.com/images/icon_weibo_24.png"],"praiseUserIdList":[82055,82002,82001],"userId":70793}
         * User : {"head":"http://static.oschina.net/uploads/user/585/1170143_50.jpg?t=1390226446000","id":70793,"name":"Strong"}
         */

        public MomentBean Moment;
        public UserBean User;

        public static class MomentBean {
            /**
             * content : APIJSON is a JSON Transmission Structure Protocol…
             * date : 2017-02-08 16:06:11.0
             * id : 15
             * pictureList : ["http://static.oschina.net/uploads/user/1218/2437072_100.jpg?t=1461076033000","http://common.cnblogs.com/images/icon_weibo_24.png"]
             * praiseUserIdList : [82055,82002,82001]
             * userId : 70793
             */

            public String content;
            public String date;
            public long id;
            public int userId;
            public List<String> pictureList;
            public List<Integer> praiseUserIdList;

            public static MomentBean objectFromData(String str) {

                return new Gson().fromJson(str, MomentBean.class);
            }

            public static MomentBean objectFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);

                    return new Gson().fromJson(jsonObject.getString(str), MomentBean.class);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }
        }

        public static class UserBean {
            /**
             * head : http://static.oschina.net/uploads/user/585/1170143_50.jpg?t=1390226446000
             * id : 70793
             * name : Strong
             */

            public String head;
            public long id;
            public String name;

            public static UserBean objectFromData(String str) {

                return new Gson().fromJson(str, UserBean.class);
            }

            public static UserBean objectFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);

                    return new Gson().fromJson(jsonObject.getString(str), UserBean.class);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }
        }

        public static class Comment {
            /**
             * content : This is a Content...-3
             * date : 2017-02-01 19:20:50.0
             * id : 3
             * momentId : 15
             * toId : 0
             * userId : 82002
             */

            public String content;
            public String date;
            public int id;
            public int momentId;
            public int toId;
            public int userId;

            public static Comment objectFromData(String str) {

                return new Gson().fromJson(str, Comment.class);
            }

            public static Comment objectFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);

                    return new Gson().fromJson(jsonObject.getString(str), Comment.class);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }
        }
    }
}