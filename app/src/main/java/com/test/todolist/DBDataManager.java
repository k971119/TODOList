package com.test.todolist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DBDataManager {

    SQLiteDatabase db;
    DBHelper helper;

    DBDataManager(Context c) {
        helper = new DBHelper(c);
    }

    public void Insert(String title, String sub){
        db = helper.getWritableDatabase();
        db.execSQL("INSERT INTO todo VALUE(NULL,'"+title+"', '"+sub+"', '"+"0');");
        db.close();
    }

    public void Update(String title, boolean clear){
        int check;
        if(clear){
            check = 1;
        }else{
            check = 0;
        }
        db = helper.getWritableDatabase();
        db.execSQL("UPDATE todo SET clear ="+check+"WHERE mText = '"+title+"';");
        db.close();
    }

    public void Delete(String title){
        db = helper.getWritableDatabase();
        db.execSQL("DELETE FROM todo WHERE mText = '"+title+"';");
        db.close();
    }

}
