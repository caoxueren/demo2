package com.example.mycontentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mycontentprovider.mydata.MyDatabaseHelper;


/**
 * 新建一个MyProvider类去继承ContentProvider，并重写其中的6个抽象方法
 */

public class MyProvider  extends ContentProvider {


    /**
     * 我们再借助UriMatcher这个类就可以轻松实现匹配内容URI的功能
     * 提供了一个addURI()方法，接收3个参数(authority/path/自定义代码)
     * 当调用match()时，就可以将一个URI对象传入，返回值你是某个能够匹配这个Uri对象所对应的自定义代码
     */
    public static final int BOOK_DIR = 0;
    public static final int BOOK_ITEM = 1;
    public static final int CATEGORY_DIR = 2;
    public static final int CATEGORY_ITEM = 3;
    public static final String ACTHORITY = "com.example.mycontentprovider";
    private static UriMatcher sUriMatcher;
    private MyDatabaseHelper mMyDatabaseHelper;

    static {
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI(ACTHORITY,"book",BOOK_DIR);
        sUriMatcher.addURI(ACTHORITY,"book/#",BOOK_ITEM);
        sUriMatcher.addURI(ACTHORITY,"category",CATEGORY_DIR);
        sUriMatcher.addURI(ACTHORITY,"catetory/#",CATEGORY_ITEM);
    }


    // 初始化内容提供器的时候调用，通常在这里完成对数据库的创建和升级，返回true表示成功，false表示失败
    @Override
    public boolean onCreate() {
        //创建了一个MyDatabaseHelper实例，返回true表示内容提供器初始化成功
        mMyDatabaseHelper = new MyDatabaseHelper(getContext(),"BookStore.db",null,2);
        return true;
    }

    //通过获取SQLiteDatabase实例，根据uri参数判断用户想要访问哪张表，调用其query方法进行查询，并将cursor对象返回
    //当访问单条数据时，调用了uri.getPathSegments()方法，它会将内容URI权限之后部分以/进行切割
    //并把分割后的结果放入到一个字符串列表里，这个列表的第0个位置存放的是路径，第1个位置存放的是id
    //得到id之后，再根据selection,selectionArgs进行约束，就实现了查找单条数据的功能

    //uri确定表，projection确定列，selection确定where，selectionArgs确定where参数，sortOrder确定排序方式

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        //查询数据
        SQLiteDatabase db = mMyDatabaseHelper.getReadableDatabase();
        Cursor cursor = null;
        switch (sUriMatcher.match(uri)) {
            case BOOK_DIR:
                cursor = db.query("Book",projection,selection,  selectionArgs,null,null,sortOrder);
                break;
            case BOOK_ITEM:
                String bookId = uri.getPathSegments().get(1);
                cursor = db.query("Book",projection,"id=?",new String[]{bookId},null,null,sortOrder);
                break;
            case CATEGORY_DIR:
                cursor = db.query("Category",projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case CATEGORY_ITEM:
                String categoryId = uri.getPathSegments().get(1);
                cursor = db.query("Category",projection,"id=?",new String[]{categoryId},null,null,sortOrder);
                break;
            default:
                break;
        }
        return cursor;
    }


    // 向内容提供器添加数据
    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        //添加数据
        SQLiteDatabase db = mMyDatabaseHelper.getWritableDatabase();
        Uri uriReturn = null;

        switch (sUriMatcher.match(uri)) {
            case BOOK_DIR:
            case BOOK_ITEM:
                long newBookId = db.insert("Book",null,contentValues);
                uriReturn = Uri.parse("content://" + ACTHORITY + "/book/" + newBookId);
                break;
            case CATEGORY_DIR:
            case CATEGORY_ITEM:
                long newCategoryId = db.insert("Category",null,contentValues);
                uriReturn = Uri.parse("content://" + ACTHORITY + "/category/" + newCategoryId);
                break;
            default:
                break;
        }

        return uriReturn;
    }

    // 从内容提供器中删除数据
    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = mMyDatabaseHelper.getWritableDatabase();
        int deleteRows = 0;
        switch (sUriMatcher.match(uri)) {
            case BOOK_DIR:
                deleteRows= db.delete("Book",selection,selectionArgs);
                break;
            case BOOK_ITEM:
                String bookId = uri.getPathSegments().get(1);
                deleteRows = db.delete("Book","id=?",new String[]{bookId});
                break;
            case CATEGORY_DIR:
                deleteRows = db.delete("Category",selection,selectionArgs);
                break;
            case CATEGORY_ITEM:
                String categoryId = uri.getPathSegments().get(1);
                deleteRows = db.delete("Category","id=?",new String[]{categoryId});
                break;
            default:
                break;

        }
        return deleteRows;
    }


    //更新内容提供器中已有的数据
    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String selection, @Nullable String[] selectionArgs) {
        //更新数据
        SQLiteDatabase db = mMyDatabaseHelper.getWritableDatabase();
        int updatedRows = 0;

        switch (sUriMatcher.match(uri)) {
            case BOOK_DIR:
                updatedRows = db.update("Book",contentValues,selection,selectionArgs);
                break;
            case BOOK_ITEM:
                String bookId = uri.getPathSegments().get(1);
                updatedRows = db.update("Book",contentValues,"id=?",new String[]{bookId});
                break;
            case CATEGORY_DIR:
                updatedRows = db.update("Category",contentValues,selection,selectionArgs);
                break;
            case CATEGORY_ITEM:
                String categoryId = uri.getPathSegments().get(1);
                updatedRows = db.update("Category",contentValues,"id=?",new String[]{categoryId});
                break;
            default:
                break;
        }

        return updatedRows;
    }

    // 根据传入的内容URI返回相应的MIME类型
    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (sUriMatcher.match(uri)) {
            case BOOK_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.mycontentprovider.book";
            case BOOK_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.mycontentprovider.book";
            case CATEGORY_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.mycontentprovider.category";
            case CATEGORY_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.mycontentprovider.category";
        }
        return null;
    }
}
