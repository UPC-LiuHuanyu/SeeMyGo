package com.example.lhy.Controller;

import android.content.Context;

import com.example.lhy.cons.IDiyMessage;
import com.example.lhy.db.UserDao;

/**
 * @author Serena
 * @time 2016/7/18  16:56
 * @desc ${TODD}
 */
public class MineController extends BaseController {

    private UserDao mUserDao;

    public MineController(Context mContext) {
        super(mContext);
        mUserDao = new UserDao(mContext);
    }

    @Override
    protected void handlerMessage(int action, Object... values) {
        switch (action){
            case IDiyMessage.DELETE_USER_ACTION:
                mUserDao.deleteAll();
                mListener.onModelChanged(IDiyMessage.DELETE_USER_ACTION_RESULT,0);
                break;
        }
    }
}
