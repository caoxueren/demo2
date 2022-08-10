package com.example.myphonestorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         imageView=(ImageView) findViewById(R.id.image_1);
    }

    public void writer(View view) throws IOException {
       /* InputStream inputStream=null;*/
        /*FileOutputStream outputStream=null;*/
        //读取资产目录下的图片
        AssetManager assets=this.getAssets();

        Log.d("TAG", String.valueOf(assets));

        InputStream inputStream=assets.open("img.png");



        Log.d("TAG",this.getFilesDir().toString());
        FileOutputStream outputStream=openFileOutput("img.png", Context.MODE_PRIVATE);
        byte[] data=new byte[1024];
        int len;
        while((len=inputStream.read(data))!=-1){
            outputStream.write(data,0,len);
        }
        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();


        outputStream.close();
        inputStream.close();
    }

    public void read(View view){

        Bitmap bitmap=null;
        try {
             bitmap= BitmapFactory.decodeStream(openFileInput("img.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        imageView.setImageBitmap(bitmap);

    }

    /*跳转到FileActivity*/
    public void bntn(View view){
        Intent intent=new Intent(MainActivity.this,FileActivity.class);
        startActivity(intent);
    }
}