package com.example.lhy.bean;

/**
 * Created by 刘焕宇 on 16/7/14.
 * QQ：310719413
 * Email：freshmboy@126.com
 */
public class RResponse {


    /**
     * code : 1
     * msg : 修改成功
     * data : [{"img":"http://dtres.img2.detuyun.cn/admin/appcamera/55e3e9244156f.jpg","type":"app","url":"72412","title":"花千骨"},{"img":"http://dtres.img2.detuyun.cn/admin/appcamera/55e3e9244156f.jpg","type":"h5","url":"http://www.detu.com","title":"抗战胜利"}]
     */

    private int code;
    private String msg;
    /**
     * img : http://dtres.img2.detuyun.cn/admin/appcamera/55e3e9244156f.jpg
     * type : app
     * url : 72412
     * title : 花千骨
     */

    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
