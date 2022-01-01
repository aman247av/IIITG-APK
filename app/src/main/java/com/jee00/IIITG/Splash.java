package com.jee00.IIITG;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_splash);


        new Thread() {
            @Override
            public void run() {
                try {
                    sleep(2100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(Splash.this, MainActivity.class);
                startActivity(intent);
                Splash.this.finish();
            }
        }.start();
    }
}