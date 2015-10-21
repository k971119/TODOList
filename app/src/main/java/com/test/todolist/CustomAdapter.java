package com.test.todolist;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class CustomAdapter extends CursorAdapter{

    private String title;
    private String sub;
    private boolean clear;

    DBDataManager db;

    public CustomAdapter(Context context, Cursor cursor){
        super(context, cursor);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) { // ListView에 띄워줄 ROW를 관리
        View v = ((LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.list_item, parent, false);
        return v;
    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {

        final TextView mText = (TextView)view.findViewById(R.id.mText);
        final TextView sText = (TextView)view.findViewById(R.id.sText);
        final Button clearCK = (Button)view.findViewById(R.id.clear_bt);

        title = cursor.getString(cursor.getColumnIndex("mText"));
        sub = cursor.getString(cursor.getColumnIndex("sText"));

        mText.setText(title);
        sText.setText(sub);

        // 1 = true(완료) 0 = flase(미완)
        clear = cursor.getInt(cursor.getColumnIndex("clear"))==1? true:false;
        if(clear){
            clearCK.setText("완료");
        }else{
            clearCK.setText("미완");
        }

        clearCK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = new DBDataManager(context);
                if(clear){
                    Toast.makeText(context, "이미 완료된 할일입니다.", Toast.LENGTH_SHORT).show();
                }else{  //상세보기 커스텀 다이알로그 - listview_dlg.xml // DB UPDATE
                    db.Update(title, true);

                }
                notifyDataSetChanged();
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDlg dlg = new CustomDlg(context, title, sub, clearCK.getText().toString());
            }
        });

    }
}
