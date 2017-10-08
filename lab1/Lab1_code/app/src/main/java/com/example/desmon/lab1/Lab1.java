package com.example.desmon.lab1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Lab1 extends AppCompatActivity {
    Button button = null;
    Button button1 = null;
    TextView t = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab1);
        button = (Button)findViewById(R.id.button1); //登录
        button1 = (Button)findViewById(R.id.button2); //注册
        t = (TextView)findViewById(R.id.textView6); //英文版跳转
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Lab1.this,secondActivity.class);
                startActivity(intent);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Lab1.this,thirdActivity.class);
                startActivity(intent);
            }
        });
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Lab1.this,fourthActivity.class);
                startActivity(intent);
            }
        });
    }
}
