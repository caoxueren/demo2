package com.example.myprogressbar;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private ProgressBar pg;
    private ProgressBar pg2;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       pg=findViewById(R.id.pb);
        pg2=findViewById(R.id.pb2);
    }

    public void doClick(View view){
          if(pg.getVisibility()==View.GONE){  //判断当前是否为隐藏的
              pg.setVisibility(View.VISIBLE);  //当前为隐藏的，显示出来
          }else{
              pg.setVisibility(View.GONE);//当前为显示的，隐藏掉
          }
    }

    public void doClick2(View view){
       int p=pg2.getProgress();//获取进度条的值
       p+=10;
       pg2.setProgress(p);//设置进度跳的值
    }
}