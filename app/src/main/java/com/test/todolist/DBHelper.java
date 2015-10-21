package com.test.todolist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    String sql;
    String query;
    String tableName = "todo";

    public DBHelper(Context context){
        super(context, "TODO.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        sql = "CREATE TABLE "+tableName+"(_id INTEGER PRIMARY KEY AUTOINCREMENT, mText TEXT, sText TEXT, clear INTEGER);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {  // DB버전 업그레이드시 호출
    }
}
