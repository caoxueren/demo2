package com.example.mysqlitestorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mysqlitestorage.entity.User;
import com.example.mysqlitestorage.service.UserService;

public class RegisterActivity extends AppCompatActivity {

   private EditText username;
   private EditText password;
   private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        findViews();

        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String name=username.getText().toString().trim();
                String pass=password.getText().toString().trim();
                Log.i("TAG",name+"_"+pass);

                UserService uService=new UserService(RegisterActivity.this);
                User user=new User();
                user.setUsername(name);
                user.setPassword(pass);
                uService.register(user);
                Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_LONG).show();

                Intent intent =new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(intent);
            }

        });
    }

    private void findViews() {
        username=(EditText) findViewById(R.id.usernameRegister);
        password=(EditText) findViewById(R.id.passwordRegister);
        register=(Button) findViewById(R.id.Register);

    }

}