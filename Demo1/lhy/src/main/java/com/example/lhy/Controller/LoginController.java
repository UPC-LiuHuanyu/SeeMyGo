package com.example.lhy.Controller;

import android.app.Activity;
import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.example.lhy.application.MainApplication;
import com.example.lhy.bean.RLoginResult;
import com.example.lhy.bean.RResult;
import com.example.lhy.bean.UserInfo;
import com.example.lhy.cons.IDiyMessage;
import com.example.lhy.cons.NetWorkCons;
import com.example.lhy.db.UserDao;
import com.example.lhy.util.HttpUtil;

import java.util.HashMap;

/**
 * @author Serena
 * @time 2016/7/17  13:55
 * @desc 登陆页面的数据请求
 */
public class LoginController extends BaseController{


    private UserDao mUserDao;

    public LoginController(Context mContext) {
        super(mContext);
        mUserDao = new UserDao(mContext);
    }

    private UserInfo queryUserInfo() {
        return mUserDao.queryLastSaveUser();
    }

    @Override
    protected void handlerMessage(int action, Object... values) {
        switch (action){
            case IDiyMessage.LOGIN_ACTION:
                RResult rResult = login((String)values[0],(String)values[1]);
                mListener.onModelChanged(IDiyMessage.LOGIN_ACTION_RESULT,rResult);
                break;
            case IDiyMessage.SAVE_USER_ACTION:
                boolean saveUserInfo = saveUserInfo((String)values[0], (String)values[1], (String)values[2]);
                mListener.onModelChanged(IDiyMessage.SAVE_USER_ACTION_RESULT, saveUserInfo);
                break;
            case IDiyMessage.QUERY_USER_ACTION:
                mListener.onModelChanged(IDiyMessage.QUERY_USER_ACTION_RESULT, queryUserInfo());
                break;
        }
    }

    private RResult login(String username, String pwd) {
        HashMap<String, String> params=new HashMap<>();
        params.put("username", username);
        params.put("pwd", pwd);
        String jsonStr = HttpUtil.loadDataFromNetByPost(NetWorkCons.LOGIN_URL,params);
        return JSON.parseObject(jsonStr, RResult.class);
    }

    //登录成功后的保存用户账号和密码
    public boolean saveUserInfo(String jsonStr,String name,String pwd){
        mUserDao.deleteAll();
        boolean saveUser = mUserDao.saveUser(name, pwd);
        RLoginResult userInfo = JSON.parseObject(jsonStr, RLoginResult.class);
        Activity activity=(Activity) mContext;
        ((MainApplication)activity.getApplication()).mUserInfo = userInfo;
        return saveUser;
    }
}
