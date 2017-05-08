package com.example.dell.fbloginsample;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Login extends Activity {
    TextView textView;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        textView=(TextView)findViewById(R.id.textView3);
        Intent intent=getIntent();
        String email=intent.getStringExtra("email");
        String pass=intent.getStringExtra("pass");
        String name=intent.getStringExtra("name");
        textView.setText("Name: "+name+"\nEmail: "+email);
    }

    public void logOut(View v){
        Intent intent=new Intent(Login.this,MainActivity.class);
        startActivity(intent);
    }
}

