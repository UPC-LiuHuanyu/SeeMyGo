package com.example.lhy.bean;

/**
 * @author Serena
 * @time 2016/7/16  19:49
 * @desc ${TODD}
 */
public class RResult {
    /**
     * result : {"id":"用户id","waitPayCount":"待付款数","userLevel":"用户等级（1注册会员2铜牌会员3银牌会员4金牌会员5钻石会员）","userIcon":"头像路径","waitReceiveCount":"待收货数","userName":"用户名"}
     * errorMsg :
     * success : true
     */
    private String result;
    private String errorMsg;
    private boolean success;

    public void setResult(String result) {
        this.result = result;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getResult() {
        return result;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public boolean isSuccess() {
        return success;
    }


  /*  "code": 1,
            "msg": "登录成功",
            "data":*/

  /*  private int code;
    private String msg;
    private String data;

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data='" + data + '\'' +
                '}';
    }*/

   /* "success": true,
            "errorMsg": "",
            "result": {*/


}
