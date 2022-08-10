package com.example.button;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Recharge extends AppCompatActivity {

    private EditText re_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);
        Button rebn1=(Button) findViewById(R.id.re_btn1);
        Button rebn2=(Button) findViewById(R.id.re_btn2);
        re_edit=(EditText) findViewById(R.id.re_money);

        //接收LoginIndex传过来的数据
        final  Bundle bundle=getIntent().getExtras();
        String a=bundle.getString("number");


        rebn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String b=re_edit.getText().toString().trim();
                int c=Integer.parseInt(a)+Integer.parseInt(b);
                String d=Integer.toString(c);
                Log.d("lag","LoginIndex传过来的数据："+a);
                Log.d("lag","充值金额："+b);
                Log.d("lag","充值金额+本来的金额："+c);
                Intent intent=new Intent();
                Bundle bundle1=new Bundle();
                bundle1.putString("tetle",d);
                intent.putExtras(bundle1);
                setResult(Activity.RESULT_OK,intent);
                finish();//把当前页面关闭掉

            }
        });
        rebn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent();
                Bundle bundle1=new Bundle();
                bundle1.putString("tetle",a);
                intent.putExtras(bundle1);
                setResult(Activity.RESULT_OK,intent);
                finish();//把当前页面关闭掉
            }
        });

    }

}