package com.example.myphonestorage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class FileActivity extends AppCompatActivity {


    private EditText et_1;
    private TextView tv_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);

        et_1=(EditText)findViewById(R.id.et_1);
        tv_1=(TextView)findViewById(R.id.tv_1);
        init();//先进行读取

    }


    //写内部文件
    public void bt2_OnClick(View v) {
        //从内存里写入文件

        //1.得到内部的存储目录
        try {


            File file=getFilesDir();

            String path=file.getAbsolutePath();

            Toast.makeText(FileActivity.this, "path"+path, Toast.LENGTH_SHORT).show();

            //2.用输出流写入文件

            FileOutputStream fos = openFileOutput("test.txt",MODE_APPEND);

            //3.写入文件内容
            PrintStream ps=new PrintStream(fos);

            String str=et_1.getText().toString();

            ps.println(str);
            //ps.println("自动换行");
            ps.close();
            fos.close();
            Toast.makeText(FileActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            Toast.makeText(FileActivity.this, "保存失败", Toast.LENGTH_SHORT).show();
        }


    }

    //读
    public void bt3_OnClick(View v)
    {
        try {
            //输入流
            FileInputStream fis = openFileInput("test.txt");

            //1.定义byte[]
            byte[]b=new byte[1024];
            int i=0;//读到的数据长度

            String str1="";

            //2.循环读
            while((i=fis.read(b))>0)
            {
                String str=new String(b,0,i);

                str1+=str;
            }

            fis.close();

            tv_1.setText(str1);

        }
        catch (Exception e)
        {

        }
    }


    public void init(){

        try {
            //输入流
            FileInputStream fis = openFileInput("test.txt");

            //1.定义byte[]
            byte[]b=new byte[1024];
            int i=0;//读到的数据长度

            String str1="";

            //2.循环读
            while((i=fis.read(b))>0)
            {
                String str=new String(b,0,i);

                str1+=str;
            }

            fis.close();

            tv_1.setText(str1);

        }
        catch (Exception e)
        {

        }

    }
}