package com.jee00.IIITG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jee00.IIITG.Model.Faculty;

import java.util.concurrent.TimeUnit;

public class Mail extends AppCompatActivity {

    RecyclerView recyclerView;
    EMailAdapter emailAdapter;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);

//firebase_calling
            database= FirebaseDatabase.getInstance();
            reference=database.getReference("Faculty");

            recyclerView=findViewById(R.id.rv);
            GridLayoutManager layoutManager=new GridLayoutManager(this,2);
            recyclerView.setLayoutManager(layoutManager);
          //  recyclerView.setLayoutManager(new LinearLayoutManager(this));

            //waiting msg while loading recyclerView
            long duration= TimeUnit.SECONDS.toMillis(2);
            new CountDownTimer(duration, 1000) {
                @Override
                public void onTick(long l) {
                    Toast.makeText(Mail.this,"Please wait! Connecting to the Firebase",Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFinish() {
                    ProgressBar textView=findViewById(R.id.warn);
                    textView.setVisibility(View.INVISIBLE);
                }
            }.start();

//firebase_gathering info
            FirebaseRecyclerOptions<Faculty> options=new FirebaseRecyclerOptions.Builder<Faculty>()
                    .setQuery(reference,Faculty.class)
                    .build();

            emailAdapter=new EMailAdapter(options);
            recyclerView.setAdapter(emailAdapter);

        }

        @Override
        protected void onStart() {
            super.onStart();
            emailAdapter.startListening();
        }

        @Override
        protected void onStop() {
            super.onStop();
            emailAdapter.stopListening();
        }

    @Override
    public void onBackPressed() {
        Intent i=new Intent(Mail.this,MainActivity.class);
        startActivity(i);
        Mail.this.finish();
        super.onBackPressed();
    }
}