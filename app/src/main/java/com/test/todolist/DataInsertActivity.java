package com.test.todolist;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DataInsertActivity extends Activity implements View.OnClickListener{

    DBDataManager db;

    EditText main, sub;
    Button save, cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datainsert_activity);

        db = new DBDataManager(this);

        main = (EditText)findViewById(R.id.main_ed);
        sub = (EditText)findViewById(R.id.sub_ed);

        save = (Button)findViewById(R.id.save_bt);
        cancel = (Button)findViewById(R.id.close_bt);

        save.setOnClickListener(this);
        cancel.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.save_bt :
                if(main.getText().toString().equals(null)){
                    Toast.makeText(this, "할일을 입력해주세요.", Toast.LENGTH_SHORT).show();
                }else {
                    db.Insert(main.getText().toString(), sub.getText().toString());
                    Toast.makeText(this, main.getText().toString()+"을(를) 저장했습니다.", Toast.LENGTH_SHORT).show();
                    break;
                }
            case R.id.close_bt :
                finish();
                break;
        }
    }
}
