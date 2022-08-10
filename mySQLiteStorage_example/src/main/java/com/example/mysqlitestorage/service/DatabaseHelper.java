package com.example.mysqlitestorage.service;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.mysqlitestorage.IndexActivity;

/*
 * 什么是SQLiteDatabase？
 * 一个SQLiteDatabase的实例代表了一个SQLite的数据库，通过SQLiteDatabase实例的一些方法，我们可以执行SQL语句，
 * 对数据库进行增、删、查、改的操作。需要注意的是，数据库对于一个应用来说是私有的，并且在一个应用当中，数据库的名字也是惟一的。
 */

/*
 * 什么是SQLiteOpenHelper ？
 * 这个类主要生成一个数据库，并对数据库的版本进行管理。
 * 当在程序当中调用这个类的方法getWritableDatabase()或者getReadableDatabase()方法的时候，如果当时没有数据，那么Android系统就会自动生成一个数据库。
 * SQLiteOpenHelper 是一个抽象类，我们通常需要继承它，并且实现里边的3个函数，
 *     onCreate（SQLiteDatabase）：在数据库第一次生成的时候会调用这个方法，一般我们在这个方法里边生成数据库表。
 *     onUpgrade（SQLiteDatabase, int, int）：当数据库需要升级的时候，Android系统会主动的调用这个方法。一般我们在这个方法里边删除数据表，并建立新的数据表，当然是否还需要做其他的操作，完全取决于应用的需求。
 *     onOpen（SQLiteDatabase）：这是当打开数据库时的回调函数，一般也不会用到。
 */
public class DatabaseHelper extends SQLiteOpenHelper {


    private static final int VERSION=1;
    private static final String DBNAME="mydatabase.db";   //  创建数据库名叫 mydatabase
    private Context mContext;

    /*构造方法创建数据库
    参数:1.上下文对象，2.数据库名称 3.默认null 4.版本号*/
    public DatabaseHelper(Context context){
        super(context,DBNAME,null,VERSION);
        mContext = context;
    }

    public DatabaseHelper(IndexActivity context, String s, Object o, int i) {
        super(context,DBNAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //创建用户表  user_tb
        sqLiteDatabase.execSQL("create table user_tb ("
                        +"id integer primary key autoincrement,"
                        +"username text,"
                        +"password text)");
        Toast.makeText(mContext, "创建User表成功", Toast.LENGTH_SHORT).show();

    }

    //数据库版本更新
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("drop table if exists user_tb");

    }
}
