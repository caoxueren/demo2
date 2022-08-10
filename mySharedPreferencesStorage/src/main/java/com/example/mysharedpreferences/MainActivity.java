package com.example.mysharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText edit1;
    private EditText edit2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit1= findViewById(R.id.edit1);
        edit2= findViewById(R.id.edit2);
        /**
         * 读取数据
         */
        //获取SharedPreference对象，通过getSharedPreferences(String ,int)方法,
        // 第一个参数用于指定存储文件名，第二个参数指定文件操作类型，一般是MODE_PRIVATE私有存储方式，其他应用无法访问

        /*Activity.MODE_PRIVATE：表示该文件是私有数据，只能被应用本身访问，在该模式下，写入的内容会覆盖原文件的内容
        Activity.MODE_APPEND：也是私有数据，新写入的内容会追加到原文件中
        Activity.MODE_WORLD_READABLE：表示当前文件可以被其他应用读取
        Activity.MODE_WORLD_WRITEABLE：表示当前文件可以被其他应用写入*/

        SharedPreferences sp=getSharedPreferences("defg",MODE_PRIVATE);

        //其中的getString()方法中的第一个参数是key的名称，
        //第二个参数的含义是：“如果没有找到以该key存储的value，那就返回该参数”，如上代码，没有相应的username的话就返回“”
        String str=sp.getString("username","");
        edit1.setText(str);
    }

    public  void doClick(View view){

        EditText edit1= findViewById(R.id.edit1);
        String username= edit1.getText().toString();

        if(username==null||username.trim().length()==0){
            Toast.makeText(MainActivity.this, "请输入正确的用户名", Toast.LENGTH_SHORT).show();
            return;
        }else{
            /**
             * 存储数据，
             */
            SharedPreferences sharedpreferences=getSharedPreferences("defg",MODE_PRIVATE);
            SharedPreferences.Editor editor= sharedpreferences.edit();
            editor.putString("username",username);
            editor.commit();
            Toast.makeText(MainActivity.this, "保存数据成功", Toast.LENGTH_SHORT).show();
        }

    }
}