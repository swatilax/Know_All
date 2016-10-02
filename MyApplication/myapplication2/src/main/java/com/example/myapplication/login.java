package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View view) {

        TextView tv = (TextView)findViewById(R.id.editText);
        Intent intent = new Intent(login.this, Main2Activity.class).putExtra("user",tv.getText().toString() );
        startActivity(intent);
    }
}
