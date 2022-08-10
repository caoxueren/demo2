package com.example.mytextview;

import androidx.appcompat.app.AppCompatActivity;

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
        Button btn=(Button) findViewById(R.id.btn);
        edit1 =findViewById(R.id.edit_text1);
        edit2 =findViewById(R.id.edit_text2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text1=edit1.getText().toString();
                String text2=edit2.getText().toString();
                Log.e("leg","EditText的内容"+text1);
                Log.e("leg","EditText的内容"+text2);
            }
        });
    }


}