package com.example.desmon.lab1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;

public class fourthActivity extends AppCompatActivity {
    TextView t = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        t = (TextView)findViewById(R.id.textView6);
        t.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setClass(fourthActivity.this, Lab1.class);
                startActivity(intent);
            }
        });
    }
}
