package com.jee00.IIITG;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.jee00.IIITG.Model.Books;

public class MainAdapter extends FirebaseRecyclerAdapter<Books,MainAdapter.myViewHolder> {

    public MainAdapter(@NonNull FirebaseRecyclerOptions<Books> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull Books model) {
        holder.title.setText(model.getTitle());
        holder.subject.setText(model.getSub());
        if(model.getCat().equals("link"))
              holder.icon.setImageResource(R.drawable.link);

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse(model.getLink()));
                holder.relativeLayout.getContext().startActivity(i);

            }
        });

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.books_btns_firebase,parent,false);
        return new myViewHolder(v);
    }

    static class myViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView subject;
        ImageView icon;
        RelativeLayout relativeLayout;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            icon=itemView.findViewById(R.id.img1);
            relativeLayout=itemView.findViewById(R.id.whole_btn);
            title=itemView.findViewById(R.id.bname);
            subject=itemView.findViewById(R.id.subject);

        }
    }
}
