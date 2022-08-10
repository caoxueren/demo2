package com.example.button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginIndex extends AppCompatActivity {

    private TextView number ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_index);

        Button btnin=(Button) findViewById(R.id.btnIn);

        number= (TextView) findViewById(R.id.number_text);

        btnin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(LoginIndex.this,Recharge.class);

                //从LoginIndex发送数据到Recharge
                Bundle bundle =new Bundle();
                String money= number.getText().toString();
                bundle.putString("number",money);
                intent.putExtras(bundle) ;
                /*startActivity(intent);*/
                startActivityForResult(intent,0);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //再Recharge关闭后，返回数据给LoginIndex
        //显示B的回调的数据
        //注意tetle是Recharge传过来name，
        String sum=data.getStringExtra("tetle");
        number.setText(sum);
        number.setTextColor(-65536);
        /*Toast.makeText(LoginIndex.this, data.getExtras().getString("tetle"), Toast.LENGTH_SHORT).show();*/
    }
}