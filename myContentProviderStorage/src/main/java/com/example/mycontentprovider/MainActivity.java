package com.example.mycontentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.mycontentprovider.mydata.MyDatabaseHelper;

public class MainActivity extends AppCompatActivity {


    private MyDatabaseHelper mMyDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMyDatabaseHelper = new MyDatabaseHelper(this,"BookStore.db",null,2);

    }


    public void createDatabase(View view) {
        mMyDatabaseHelper.getWritableDatabase();
    }

    public void addData(View view) {

        SQLiteDatabase db = mMyDatabaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        //开始组装第一条数据
        values.put("name","The Da Vinci Code");
        values.put("author","Dan Brown");
        values.put("pages",454);
        values.put("price",16.96);
        //插入第一条数据
        db.insert("Book",null,values);//返回新添记录的行号，与主键id无关
        values.clear();

        //插入第二条数据
        values.put("name","The Lost Symbol");
        values.put("author","Dan Brown");
        values.put("pages",510);
        values.put("price",19.95);
        //插入第一条数据
        /* db.insert("person", "name" , null);
        第一个参数为表名，
        第二个参数为，缺省的字段的名字,如果没有值 就在数据库中存储为null,
        db.insert("person", "name" , null);   执行这表语句以后，该字段就会为null*/

        db.insert("Book",null,values);//返回新添记录的行号，与主键id无关
        values.clear();

        Log.d("mmmmmmmmmmmmm","添加数据成功");

        //db.execSQL("insert into Book (name,author,pages,price) values(?,?,?,?)",new String[] {"The Da Vinci Code","Dan Brown","454","44.23"});

    }

    public void updateData(View view) {

        SQLiteDatabase db = mMyDatabaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("price",29.89);

        db.update("Book",values,"name = ?",new String[]{"The Da Vinci Code"});
        values.clear();

        //db.execSQL("update Book set price = ? where name = ?",new String[] {"29.9","The Da Vinci Code"});
    }

    public void deleteData(View view) {

        SQLiteDatabase db = mMyDatabaseHelper.getWritableDatabase();

        db.delete("Book","id > ?",new String[]{"2"});

        //db.execSQL("delete from Book where id > ?",new String[] {"2"});

    }

    public void queryData(View view) {

        SQLiteDatabase db = mMyDatabaseHelper.getWritableDatabase();

        //查询Book表中的所有数据
        Cursor cursor = db.query("Book",null,null,null,null,null,null);

        //Cursor cursor = db.rawQuery("select * from Book",null);

        if (cursor.moveToFirst()) {
            do {
                //遍历Cuosor对象，取出数据并打印
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
                @SuppressLint("Range") String author = cursor.getString(cursor.getColumnIndex("author"));
                @SuppressLint("Range") int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                @SuppressLint("Range") double price = cursor.getDouble(cursor.getColumnIndex("price"));

                Log.d("ddddddddddd","book name is " + name);
                Log.d("MainActivity","book author is " + author);
                Log.d("MainActivity","book pages is " + pages);
                Log.d("MainActivity","book price is " + price);
            }while (cursor.moveToNext());
        }
        cursor.close();

    }
}