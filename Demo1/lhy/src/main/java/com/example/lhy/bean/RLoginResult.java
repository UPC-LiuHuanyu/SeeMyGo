package com.example.lhy.bean;

/**
 * @author Serena
 * @time 2016/7/17  14:34
 * @desc ${TODD}
 */
public class RLoginResult {
    /**
     * id : 用户id
     * waitPayCount : 待付款数
     * userLevel : 用户等级（1注册会员2铜牌会员3银牌会员4金牌会员5钻石会员）
     * userIcon : 头像路径
     * waitReceiveCount : 待收货数
     * userName : 用户名
     */
    private String id;
    private String waitPayCount;
    private String userLevel;
    private String userIcon;
    private String waitReceiveCount;
    private String userName;

    public void setId(String id) {
        this.id = id;
    }

    public void setWaitPayCount(String waitPayCount) {
        this.waitPayCount = waitPayCount;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public void setWaitReceiveCount(String waitReceiveCount) {
        this.waitReceiveCount = waitReceiveCount;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getId() {
        return id;
    }

    public String getWaitPayCount() {
        return waitPayCount;
    }

    public String getUserLevel() {
        return userLevel;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public String getWaitReceiveCount() {
        return waitReceiveCount;
    }

    public String getUserName() {
        return userName;
    }

   /* {
        "nickname": "韩迎春啊",
            "domainname": "",
            "headphoto": "http://dtavatar.img2.detuyun.cn/55b0c310c9407.jpg",
            "personalinfo": "123",
            "sex": 0,
            "address": "",
            "mobile": "15157819536",
            "ismobilevalid": true,
            "is_follow": false,
            "is_weixin_user": false,
            "is_qq_user": false,
            "is_weibo_user": false,
            "usercode": "a6a234df79764218f5564eaa21867409"
    }*//*

    private String nickname;
    private String domainname;
    private String headphoto;
    private String personalinfo;
    private int sex;
    private String address;
    private String mobile;
    private boolean ismobilevalid;
    private boolean is_follow;
    private boolean is_weixin_user;
    private boolean is_qq_user;
    private boolean is_weibo_user;
    private String usercode;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getDomainname() {
        return domainname;
    }

    public void setDomainname(String domainname) {
        this.domainname = domainname;
    }

    public String getHeadphoto() {
        return headphoto;
    }

    public void setHeadphoto(String headphoto) {
        this.headphoto = headphoto;
    }

    public String getPersonalinfo() {
        return personalinfo;
    }

    public void setPersonalinfo(String personalinfo) {
        this.personalinfo = personalinfo;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public boolean ismobilevalid() {
        return ismobilevalid;
    }

    public void setIsmobilevalid(boolean ismobilevalid) {
        this.ismobilevalid = ismobilevalid;
    }

    public boolean is_follow() {
        return is_follow;
    }

    public void setIs_follow(boolean is_follow) {
        this.is_follow = is_follow;
    }

    public boolean is_weixin_user() {
        return is_weixin_user;
    }

    public void setIs_weixin_user(boolean is_weixin_user) {
        this.is_weixin_user = is_weixin_user;
    }

    public boolean is_qq_user() {
        return is_qq_user;
    }

    public void setIs_qq_user(boolean is_qq_user) {
        this.is_qq_user = is_qq_user;
    }

    public boolean is_weibo_user() {
        return is_weibo_user;
    }

    public void setIs_weibo_user(boolean is_weibo_user) {
        this.is_weibo_user = is_weibo_user;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }*/


}
