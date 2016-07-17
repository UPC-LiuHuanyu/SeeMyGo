package com.example.lhy.bean;

import java.util.List;

/**
 * Created by 刘焕宇 on 16/7/15.
 * QQ：310719413
 * Email：freshmboy@126.com
 */
public class RItembean {

    /**
     * id : 76252
     * name : 22 (1)
     * syncstatus : 2
     * like_count : 0
     * is_like : false
     * comment_count : 1
     * thumburl : http://7xjcqf.com2.z0.glb.qiniucdn.com/pano979261467338525822036248/thumb/500_500/panofile.jpg
     * picmark : []
     * comments : [{"id":"5268","relationid":"76252","content":"阿斯顿撒旦","reply":"0","createtime":"2016-07-14 09:56:46","type":"0","parentid":"0","score":"0","isdelete":"0","author":{"nickname":"巭孬嫑勥烎","headphoto":"http://q.qlogo.cn/qqapp/100510144/876151949CB9EFBCC875A94B6ACAD09A/100","domainname":"qqTNTQSI","personalinfo":"asdsa"}}]
     * cutsize :
     * picmode : 3
     * imagedes :
     * uploadtime : 1467338498
     * imagesize : 18887000
     * app_config : http://www.test.detu.com/ajax/pano/xml/76252
     * width : 7260
     * height : 3630
     * devicename :
     * viewcount : 9
     * coordinates : {"longitude":0,"latitude":0}
     * address :
     * stars : 3
     * prealign : 0
     * iscanview : 1
     * html5_path : http://7xjcqf.com2.z0.glb.qiniucdn.com/pano979261467338525822036248/oper/panofile_html
     * html5_3dpreview : http://7xjcqf.com2.z0.glb.qiniucdn.com/pano979261467338525822036248/oper/panofile_preview_detunew.jpg
     * author : {"nickname":"哈哈","domainname":"detu_NL7D1G","headphoto":"http://dtavatar.img2.detuyun.cn/7633a04f0509d94ff92fbf88d36968b9.jpg","personalinfo":"哈哈哈哈","sex":"0","address":"","is_follow":false}
     */

    public String id;
    public String name;
    public int syncstatus;
    public int like_count;
    public boolean is_like;
    public int comment_count;
    public String thumburl;
    public String cutsize;
    public String picmode;
    public String imagedes;
    public int uploadtime;
    public String imagesize;
    public String app_config;
    public String width;
    public String height;
    public String devicename;
    public String viewcount;
    /**
     * longitude : 0
     * latitude : 0
     */

    public CoordinatesBean coordinates;
    public String address;
    public String stars;
    public String prealign;
    public String iscanview;
    public String html5_path;
    public String html5_3dpreview;
    /**
     * nickname : 哈哈
     * domainname : detu_NL7D1G
     * headphoto : http://dtavatar.img2.detuyun.cn/7633a04f0509d94ff92fbf88d36968b9.jpg
     * personalinfo : 哈哈哈哈
     * sex : 0
     * address :
     * is_follow : false
     */

    public AuthorBean author;
    public List<?> picmark;
    /**
     * id : 5268
     * relationid : 76252
     * content : 阿斯顿撒旦
     * reply : 0
     * createtime : 2016-07-14 09:56:46
     * type : 0
     * parentid : 0
     * score : 0
     * isdelete : 0
     * author : {"nickname":"巭孬嫑勥烎","headphoto":"http://q.qlogo.cn/qqapp/100510144/876151949CB9EFBCC875A94B6ACAD09A/100","domainname":"qqTNTQSI","personalinfo":"asdsa"}
     */

    public List<CommentsBean> comments;
    /**
     * original : http://media.qicdn.detu.com/@/89536426-577D-B1A1-279B-51F49596505/2016-07-08/577f5209b70af-2048x1024.m3u8
     * original_m : http://media.qicdn.detu.com/@/89536426-577D-B1A1-279B-51F49596505/2016-07-08/577f5209b70af-1024x512.m3u8
     * original_l : http://media.qicdn.detu.com/@/89536426-577D-B1A1-279B-51F49596505/2016-07-08/577f5209b70af-2048x1024.m3u8
     * original_s : http://media.qicdn.detu.com/@/89536426-577D-B1A1-279B-51F49596505/2016-07-08/577f5209b70af-512x256.m3u8
     * original_offline : http://media.qicdn.detu.com/@/89536426-577D-B1A1-279B-51F49596505/2016-07-08/577f5209b70af-2048x1024.mp4
     * max_quality : 3
     * default_quality : 3
     */

    private String original;
    private String original_m;
    private String original_l;
    private String original_s;
    private String original_offline;
    private String max_quality;
    private String default_quality;

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getOriginal_m() {
        return original_m;
    }

    public void setOriginal_m(String original_m) {
        this.original_m = original_m;
    }

    public String getOriginal_l() {
        return original_l;
    }

    public void setOriginal_l(String original_l) {
        this.original_l = original_l;
    }

    public String getOriginal_s() {
        return original_s;
    }

    public void setOriginal_s(String original_s) {
        this.original_s = original_s;
    }

    public String getOriginal_offline() {
        return original_offline;
    }

    public void setOriginal_offline(String original_offline) {
        this.original_offline = original_offline;
    }

    public String getMax_quality() {
        return max_quality;
    }

    public void setMax_quality(String max_quality) {
        this.max_quality = max_quality;
    }

    public String getDefault_quality() {
        return default_quality;
    }

    public void setDefault_quality(String default_quality) {
        this.default_quality = default_quality;
    }

    public static class CoordinatesBean {
        public int longitude;
        public int latitude;
    }

    public static class AuthorBean {
        public String nickname;
        public String domainname;
        public String headphoto;
        public String personalinfo;
        public String sex;
        public String address;
        public boolean is_follow;
    }

    public static class CommentsBean {
        public String id;
        public String relationid;
        public String content;
        public String reply;
        public String createtime;
        public String type;
        public String parentid;
        public String score;
        public String isdelete;
        /**
         * nickname : 巭孬嫑勥烎
         * headphoto : http://q.qlogo.cn/qqapp/100510144/876151949CB9EFBCC875A94B6ACAD09A/100
         * domainname : qqTNTQSI
         * personalinfo : asdsa
         */

        public AuthorBean author;

        public static class AuthorBean {
            public String nickname;
            public String headphoto;
            public String domainname;
            public String personalinfo;
        }
    }
}
