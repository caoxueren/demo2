package com.example.myservice2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;

import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private Button bindServiceBtn;
    private Button unbindServiceBtn;

    private Button servicestatus;

    //保持所启动的Service的IBinder对象,同时定义一个ServiceConnection对象
    MyService.MyBinder binder;
    private ServiceConnection connection = new ServiceConnection() {

        //Activity与Service连接成功时回调该方法
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            binder = (MyService.MyBinder) service;
        }

        //Activity与Service断开连接时回调该方法
        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bindServiceBtn = (Button)findViewById(R.id.bind_service);
        unbindServiceBtn = (Button)findViewById(R.id.unbind_service);
        servicestatus = (Button)findViewById(R.id.service_status);



        bindServiceBtn.setOnClickListener(this);
        unbindServiceBtn.setOnClickListener(this);
        servicestatus.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.bind_service:

                Intent intent = new Intent(this, MyService.class);
                bindService(intent, connection, BIND_AUTO_CREATE);
                break;
            case R.id.unbind_service:
                unbindService(connection);
                break;

            case R.id.service_status:

                Log.d("dddddddddddddd","Service的count的值为"+binder.getCount());

                break;
        }
    }
}