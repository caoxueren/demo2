package com.example.mysqlitestorage;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mysqlitestorage.service.DatabaseHelper;

public class IndexActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_insert;
    private Button btn_query;
    private Button btn_update;
    private Button btn_delete;

    private StringBuilder result;

    //帮助建立sqlite数据库
    SQLiteOpenHelper mysqlhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);


        btn_insert = (Button) findViewById(R.id.btn_insert);
        btn_query = (Button) findViewById(R.id.btn_query);
        btn_update = (Button) findViewById(R.id.btn_update);
        btn_delete = (Button) findViewById(R.id.btn_delete);

        btn_query.setOnClickListener(this);
        btn_insert.setOnClickListener(this);
        btn_update.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {


        mysqlhelper = new DatabaseHelper(IndexActivity.this, "MyDatabase.db", null, 1);
        SQLiteDatabase db;
        result=new StringBuilder();

        switch (view.getId()) {
            case R.id.btn_insert:

                db = mysqlhelper.getWritableDatabase();
                db.execSQL("insert into user_tb(username,password) values(?,?)", new Object[]{"jack","jack"});
                Toast.makeText(IndexActivity.this,"添加成功",Toast.LENGTH_LONG).show();
                Log.d("dddddddddddd","添加成功");
                db.close();

                break;
            case R.id.btn_query:
                db = mysqlhelper.getWritableDatabase();

                Cursor cursor = db.rawQuery("select * from user_tb", null);
                while (cursor.moveToNext()){
                    result.append("\n" + "用户id：" + cursor.getInt(0) +
                            "，用户名：" + cursor.getString(1) +
                            ",密码是：" + cursor.getString(2));
                }
                Log.d("dddddddddddd","查询结果为"+result);
                cursor.close();
                db.close();
                Toast.makeText(IndexActivity.this,"查询成功",Toast.LENGTH_LONG).show();
                break;

            case R.id.btn_update:

                db = mysqlhelper.getWritableDatabase();
                db.execSQL("update  user_tb  set password=? where username=? ", new Object[]{"Tom","jack"});

                Toast.makeText(IndexActivity.this,"修改成功",Toast.LENGTH_LONG).show();
                Log.d("dddddddddddd","修改成功"+result);
                db.close();

                break;
            case R.id.btn_delete:
                db = mysqlhelper.getWritableDatabase();
                db.execSQL("delete from user_tb  where username=?", new Object[]{"jack"});
                Toast.makeText(IndexActivity.this,"删除成功",Toast.LENGTH_LONG).show();
                Log.d("dddddddddddd","删除成功"+result);
                db.close();
                break;
        }
    }
}