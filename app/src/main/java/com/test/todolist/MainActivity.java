package com.test.todolist;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity{

    ListView mList;
    Button add_bt;
    Toolbar toolbar;

    DBHelper helper;
    SQLiteDatabase db;
    String sql;
    Cursor cursor;

    String dbName = "TODO.db";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar)findViewById(R.id.toolbar);  // Toolbar
        mList = (ListView)findViewById(R.id.Todo_List); // 리스트를 보여줄 리스트뷰
        add_bt = (Button)findViewById(R.id.add_bt);     // 리스트 항목을 추가할 add버튼

        add_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DataInsertActivity.class));
            }
        });

        setSupportActionBar(toolbar);

        helper = new DBHelper(this);

        SelectDB();

    }

    public void SelectDB(){
        db = helper.getReadableDatabase();
        sql = "SELECT * FROM todo";

        cursor = db.rawQuery(sql, null);
        if(cursor.getCount() > 0){
            startManagingCursor(cursor);
            CustomAdapter adapter = new CustomAdapter(this, cursor);
            mList.setAdapter(adapter);
        }
    }
}
