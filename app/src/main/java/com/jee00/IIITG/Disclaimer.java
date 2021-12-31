package com.jee00.IIITG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Disclaimer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disclaimer);

        Button btn1=findViewById(R.id.Poole);
        Button btn2=findViewById(R.id.thompson);
        Button btn3=findViewById(R.id.C);
        Button btn4=findViewById(R.id.Morris);
        Button btn5=findViewById(R.id.Hayt);
        Button btn6=findViewById(R.id.Gaonkar);



        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.amazon.in/Linear-Algebra-Introduction-David-Poole/dp/8131530248"));
                startActivity(i);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.amazon.in/Calculus-Analytic-Geometry-THOMAS/dp/8177583255"));
                startActivity(i);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.amazon.in/Let-Us-C-Yashavant-Kanetkar/dp/8183331637"));
                startActivity(i);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.amazon.in/Digital-Design-M-MORRIS-Mano/dp/0132129949"));
                startActivity(i);
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.amazon.in/Engineering-Circuit-Analysis-William-Hayt/dp/125909863X"));
                startActivity(i);
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.amazon.in/Books-Ramesh-Gaonkar/s?rh=n%3A976389031%2Cp_27%3ARamesh+Gaonkar"));
                startActivity(i);
            }
        });


    }
    @Override
    public void onBackPressed() {
        Intent i=new Intent(Disclaimer.this,MainActivity.class);
        startActivity(i);
        Disclaimer.this.finish();
        super.onBackPressed();
    }
}