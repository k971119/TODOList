package com.test.todolist;

import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CustomDlg extends Dialog implements View.OnClickListener{

    TextView mTextView, clearView, sTextView;
    Button close, delete;

    DBDataManager db;
    Context context;

    String mText;
    String sText;
    String clear;

    CustomDlg(Context context, String mText, String sText, String clear){
        super(context);
        this.context = context;
        this.mText = mText;
        this.sText = sText;
        this.clear = clear;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_dlg);

        db = new DBDataManager(context);

        mTextView = (TextView)findViewById(R.id.mText);
        sTextView = (TextView)findViewById(R.id.sText);
        clearView = (TextView)findViewById(R.id.clearText);

        close = (Button)findViewById(R.id.close_bt);
        delete = (Button)findViewById(R.id.delete_bt);

        mTextView.setText(mText);
        sTextView.setText(sText);
        clearView.setText(clear);

        close.setOnClickListener(this);
        delete.setOnClickListener(this);


    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.delete_bt :   // DBHelper Delete호출
                db.Delete(mText);
                break;
            case R.id.close_bt :    // 닫기버튼이 눌리면 다이얼로그 종료
                cancel();
                break;
        }
    }

}
