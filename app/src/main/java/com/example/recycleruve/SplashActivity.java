package com.example.recycleruve;

import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    public SplashActivity(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getSupportActionBar().hide();
        //ponemos el diley para el splahs
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        },3000);

    }
}
