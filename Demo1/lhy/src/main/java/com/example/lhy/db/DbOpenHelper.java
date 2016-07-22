package com.example.lhy.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.lhy.cons.DBConst;


/**
 * @author Serena
 * @time 2016/7/18  0:45
 * @desc ${TODD}
 */
public class DbOpenHelper extends SQLiteOpenHelper {

    public DbOpenHelper(Context context) {
        super(context, DBConst.DB_NAME, null, DBConst.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+DBConst.TABLE_NAME+
                "("+DBConst._ID+" integer primary key autoincrement,"
                +DBConst._USERNAME+" text,"
                +DBConst._PASSWORD+" text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }



}
