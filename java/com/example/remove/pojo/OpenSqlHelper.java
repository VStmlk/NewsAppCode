package com.example.remove.pojo;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class OpenSqlHelper extends SQLiteOpenHelper {

    // name  -> 数据库名字
    public OpenSqlHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //     建表
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table user(id integer primary key autoincrement,username varchar(20),password varchar(20),age integer) ");
    }

    //    更新表
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
