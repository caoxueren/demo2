package com.example.myaactivity_left;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1=(Button) findViewById(R.id.btn_one);

        Button button2=(Button) findViewById(R.id.btn_two);



        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*①最常见的：
                    startActivity(new Intent(当前Act.this,要启动的Act.class))*/;
                /*Intent intent =new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent);*/

                /*②通过Intent的ComponentName：
                      ComponentName cn = new ComponentName("当前Act的全限定类名","启动Act的全限定类名") ;*/
                /*ComponentName cn=new ComponentName("com.example.myaactivity_left","com.example.myaactivity_left.MainActivity2");
                Intent intent=new Intent();
                intent.setComponent(cn);
                startActivity(intent);*/

                /*③初始化Intent时指定包名：
                    Intent intent = new Intent("android.intent.action.MAIN");
                    intent.setClassName("当前Act的全限定类名","启动Act的全限定类名");*/
                Intent intent = new Intent("android.intent.action.MAIN");
                intent.setClassName("com.example.myaactivity_left","com.example.myaactivity_left.MainActivity2");
                startActivity(intent);
                Log.d("test","显示式调用");




            }
        });


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent it = new Intent();
                it.setAction( "my_action");
                it.addCategory("my_category");
                startActivity(it);

                Log.d("test","隐式调用");

            }
        });
    }
}