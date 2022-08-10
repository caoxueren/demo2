package com.example.mysqlitestorage.service;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mysqlitestorage.entity.User;

public class UserService {

    private DatabaseHelper dbHelper;

    public UserService(Context context){

        dbHelper=new DatabaseHelper(context);

    }

    public boolean login(String username,String password){

        SQLiteDatabase sdb=dbHelper.getReadableDatabase();

        String sql="select * from user_tb where username=? and password=?";

        Cursor cursor=sdb.rawQuery(sql, new String[]{username,password});

        if(cursor.moveToFirst()==true){

            cursor.close();

            return true;
        }
        return false;
    }


    /*public User query(){

        SQLiteDatabase sdb=dbHelper.getReadableDatabase();

        String sql="select * from user_tb";

        Cursor cursor=sdb.rawQuery(sql, new String[]{});


        //存在数据才返回true
        if(cursor.moveToFirst()==true){
            int userid= cursor.getInt(0);
            String username=cursor.getString(2);
            String userpassword=cursor.getString(3);

            User.add(new User());
        }
        return false;
    }*/

    public boolean register(User user){

        SQLiteDatabase sdb=dbHelper.getReadableDatabase();

        String sql="insert into user_tb(username,password) values(?,?)";
        Object obj[]={user.getUsername(),user.getPassword()};
        sdb.execSQL(sql, obj);
        return true;
    }


}
