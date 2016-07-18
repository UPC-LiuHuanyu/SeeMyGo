package com.example.lhy.bean;

import java.util.List;

/**
 * Created by 刘焕宇 on 16/7/18.
 * QQ：310719413
 * Email：freshmboy@126.com
 */
public class RItemBeanNew {


    /**
     * thumburl : http://media.qicdn.detu.com/@/admin/channel/578c33270dd35.jpg
     * original_s : http://media.qicdn.detu.com/@/11952648-8057-79C8-112C-3359F38671974/2016-07-15/57885d91b7d20-512x256.m3u8
     * picmark : []
     * imagedes : 由REnextop举办的第二届FIGHT NIGHT拳击赛已经正式开战！这里，没有地域、阶级、年龄等限制，有的只是对于拳击的狂热和偏执！在这狭小的空间内，投降和认输从来都不曾存在，只有击倒对方才是唯一的生存法则！
     * picmode : 6
     * id : 132891
     * author : {"is_follow":false,"headphoto":"http://dtavatar.img.detuyun.cn/8c70908a831b4a72be3c21b0752d863a.jpg","sex":"0","domainname":"detu_965JPW","address":"","nickname":"FANC","personalinfo":""}
     * syncstatus : 2
     * height : 2048
     * name : 最纯粹的地下拳击
     * coordinates : {"longitude":121.480237,"latitude":31.236305}
     * comment_count : 0
     * app_config : http://www.detu.com/ajax/pano/xml/132891
     * width : 4096
     * is_like : false
     * prealign : 0
     * imagesize : 801248000
     * viewcount : 38
     * default_quality : 3
     * iscanview : 1
     * cutsize :
     * original_offline : http://media.qicdn.detu.com/@/11952648-8057-79C8-112C-3359F38671974/2016-07-15/57885d91b7d20-2048x1024.mp4
     * original_m : http://media.qicdn.detu.com/@/11952648-8057-79C8-112C-3359F38671974/2016-07-15/57885d91b7d20-1024x512.m3u8
     * original_l : http://media.qicdn.detu.com/@/11952648-8057-79C8-112C-3359F38671974/2016-07-15/57885d91b7d20-2048x1024.m3u8
     * address : 上海市-上海市
     * stars : 3
     * like_count : 0
     * max_quality : 3
     * original : http://media.qicdn.detu.com/@/11952648-8057-79C8-112C-3359F38671974/2016-07-15/57885d91b7d20-2048x1024.m3u8
     * uploadtime : 1468554641
     * devicename :
     * comments : []
     */

    public String thumburl;
    public String original_s;
    public String imagedes;
    public String picmode;
    public String id;
    /**
     * is_follow : false
     * headphoto : http://dtavatar.img.detuyun.cn/8c70908a831b4a72be3c21b0752d863a.jpg
     * sex : 0
     * domainname : detu_965JPW
     * address :
     * nickname : FANC
     * personalinfo :
     */

    public AuthorBean author;
    public int syncstatus;
    public String height;
    public String name;
    /**
     * longitude : 121.480237
     * latitude : 31.236305
     */

    public CoordinatesBean coordinates;
    public int comment_count;
    public String app_config;
    public String width;
    public boolean is_like;
    public String prealign;
    public String imagesize;
    public String viewcount;
    public String default_quality;
    public String iscanview;
    public String cutsize;
    public String original_offline;
    public String original_m;
    public String original_l;
    public String address;
    public String stars;
    public int like_count;
    public String max_quality;
    public String original;
    public int uploadtime;
    public String devicename;
    public List<?> picmark;
    public List<?> comments;

    public static class AuthorBean {
        public boolean is_follow;
        public String headphoto;
        public String sex;
        public String domainname;
        public String address;
        public String nickname;
        public String personalinfo;
    }

    public static class CoordinatesBean {
        public double longitude;
        public double latitude;
    }
}
