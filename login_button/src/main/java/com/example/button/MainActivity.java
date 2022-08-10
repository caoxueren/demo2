package com.example.button;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText edit1;
    private EditText edit2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit1=(EditText) findViewById(R.id.edit1);
        edit2=(EditText) findViewById(R.id.edit2);
        Button btn=(Button) findViewById(R.id.loginButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName=edit1.getText().toString();
                String userPassword=edit1.getText().toString();
                Log.d("lag","用户名:"+userName);
                Log.d("lag","用户密码:"+userPassword);

               if(userName.equals("q") && userPassword.equals("q")){

                    Log.d("lag","登入成功");
                    //给btn添加点击事件
                    Intent intent=new Intent(MainActivity.this,LoginIndex.class);
                    //启动
                    startActivity(intent);
                }else{

                    Log.d("lag","登入失败");
                    onResume();
                }

            }
        });
    }




    //刷新原页面
    @Override
    protected void onResume() {
        super.onResume();
    }
}