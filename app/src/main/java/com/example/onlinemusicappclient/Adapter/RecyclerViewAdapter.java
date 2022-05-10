package com.example.onlinemusicappclient.Adapter;

import android.content.Context;
import android.content.Intent;
import android.transition.Slide;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.onlinemusicappclient.Model.Upload;
import com.example.onlinemusicappclient.R;
import com.example.onlinemusicappclient.SongsActivity;
import com.google.android.material.slider.Slider;

import java.security.Guard;
import java.time.Instant;
import java.util.List;

import javax.microedition.khronos.opengles.GL;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{

    private Context mContext;
    private List <Upload> uploads;

    public RecyclerViewAdapter(Context mContext, List<Upload> uploads) {
        this.mContext = mContext;
        this.uploads= uploads;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.card_view_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final Upload upload = uploads.get(position);
        holder.tv_book_title.setText(upload.getName());

        Glide.with(mContext).load(upload.getUrl()).into(holder.imd_book_thumnail);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, SongsActivity.class);
                intent.putExtra("songsCategory", upload.getSongsCategory());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });




    }

    @Override
    public int getItemCount() {
        return uploads.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_book_title;
        ImageView imd_book_thumnail;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_book_title = itemView.findViewById(R.id.book_title_id);
            imd_book_thumnail = itemView.findViewById(R.id.book_img_id);
            cardView = itemView.findViewById(R.id.card_view_id);
        }
    }
}