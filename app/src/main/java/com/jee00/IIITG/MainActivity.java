package com.jee00.IIITG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;
import com.jee00.IIITG.Model.Books;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    MainAdapter mainAdapter;
    FirebaseDatabase database;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseMessaging.getInstance().subscribeToTopic("Notification");

//firebase_calling
        database= FirebaseDatabase.getInstance();
        reference=database.getReference("Books");

        recyclerView=findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //waiting msg while loading recyclerView
        long duration= TimeUnit.SECONDS.toMillis(2);
        new CountDownTimer(duration, 1000) {
            @Override
            public void onTick(long l) {
                Toast.makeText(MainActivity.this,"Please wait! Connecting to the Firebase",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFinish() {
                ProgressBar textView=findViewById(R.id.warn);
                textView.setVisibility(View.INVISIBLE);
            }
        }.start();

//firebase_gathering info
        FirebaseRecyclerOptions<Books> options=new FirebaseRecyclerOptions.Builder<Books>()
                .setQuery(reference,Books.class)
                .build();

        mainAdapter=new MainAdapter(options);
        recyclerView.setAdapter(mainAdapter);


        ItemTouchHelper itemTouchHelper=new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);



    }
    ItemTouchHelper.SimpleCallback simpleCallback=new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            Toast.makeText(MainActivity.this, "Deleting", Toast.LENGTH_SHORT).show();
        }
    };


    @Override
    protected void onStart() {
        super.onStart();
        mainAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainAdapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search,menu);
        MenuItem item=menu.findItem(R.id.search);
        MenuItem item2=menu.findItem(R.id.email);
        MenuItem item3=menu.findItem(R.id.Disclaimer);
        MenuItem item4=menu.findItem(R.id.feedback);

        item2.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent i=new Intent(MainActivity.this,Mail.class);
                startActivity(i);
                MainActivity.this.finish();
                return false;
            }
        });

        item3.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent i=new Intent(MainActivity.this,Disclaimer.class);
                startActivity(i);
                MainActivity.this.finish();
                return false;
            }
        });

        item4.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent i=new Intent(Intent.ACTION_SENDTO);
                String mailto="mailto:"+ Uri.encode("aman247av@gmail.com")+"?subject="+
                        Uri.encode("Feedback of IIITG apk")+Uri.encode("");
                Uri m=Uri.parse(mailto);
                i.setData(m);
                startActivity(Intent.createChooser(i,"Send Email"));
                return false;
            }
        });



        SearchView searchView=(SearchView)item.getActionView();
        searchView.setQueryHint("Search Subject");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                txtSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                txtSearch(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void txtSearch(String str){



            FirebaseRecyclerOptions<Books> options = new FirebaseRecyclerOptions.Builder<Books>()
                    .setQuery(reference.orderByChild("sub").startAt(str).endAt(str + "~"), Books.class)
                    .build();

            mainAdapter = new MainAdapter(options);
            mainAdapter.startListening();
            recyclerView.setAdapter(mainAdapter);

    }


}