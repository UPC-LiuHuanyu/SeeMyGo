package com.example.lhy.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.lhy.bean.UserInfo;
import com.example.lhy.cons.DBConst;
import com.example.lhy.util.MD5Util;

/**
 * @author Serena
 * @time 2016/7/18  0:46
 * @desc ${TODD}
 */
public class UserDao {

    private DbOpenHelper mHelper;

    public UserDao(Context c) {
        mHelper = new DbOpenHelper(c);
    }

    // 业务
    // 1.只保存最后的登陆成功后用户
    // 2.进入登陆界面的时候 只查询第一条数据
    // 3. 退出登陆 删除所有用户数据

    // 代码
    // 1.删除所有数据库数据
    public void deleteAll() {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.delete(DBConst.TABLE_NAME, null, null);
    }

    // 2.添加一条数据
    public boolean saveUser(String name, String pwd) {
        try {
            pwd = MD5Util.getMD5(pwd);// 加密
            SQLiteDatabase db = mHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(DBConst._USERNAME, name);
            values.put(DBConst._PASSWORD, pwd);
            long insertedId = db.insert(DBConst.TABLE_NAME, null, values);
            return insertedId != -1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 3.查询第一条数据
    public UserInfo queryLastSaveUser() {
        try {
            SQLiteDatabase db = mHelper.getReadableDatabase();
            Cursor cursor = db.query(DBConst.TABLE_NAME, new String[] {
                            DBConst._USERNAME, DBConst._PASSWORD }, null, null, null,
                    null, null);
            // cursor 我只想查询第一条数据
            if (cursor.moveToFirst()) {
                // 获取两个字段 构建一个对象并返回
                String username = cursor.getString(0);
                String password = cursor.getString(1);
                return new UserInfo(username, password);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}

