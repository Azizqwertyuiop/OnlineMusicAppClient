package com.example.onlinemusicappclient.Adapter;

import android.content.Context;
import android.location.GnssAntennaInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinemusicappclient.Model.GetSongs;
import com.example.onlinemusicappclient.Model.Utility;
import com.example.onlinemusicappclient.R;
import com.google.android.material.transition.Hold;

import java.util.List;

public class JcSongsAdapter extends RecyclerView.Adapter<JcSongsAdapter.SongsAdapterViewHolder> {
    private int selectedPosition;


    Context context ;
    List<GetSongs> arrayListSongs;
    private RecyclerItemClickListener listener;

    public JcSongsAdapter(Context context, List<GetSongs> arrayListSongs, RecyclerItemClickListener listener) {
        this.context = context;
        this.arrayListSongs = arrayListSongs;
        this.listener = listener;
    }

    @NonNull
    @Override
    public JcSongsAdapter.SongsAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.songs_row, parent,false);

        return  new SongsAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JcSongsAdapter.SongsAdapterViewHolder holder, int position) {

        GetSongs getSongs = arrayListSongs.get(position);

        if (getSongs != null){
            if (selectedPosition == position){
                holder.itemView.setBackgroundColor(ContextCompat.getColor(context, com.example.jean.jcplayer.R.color.colorPrimary));
                holder.iv_play_active.setVisibility(View.VISIBLE);
            }else {
                holder.itemView.setBackgroundColor(ContextCompat.getColor(context , R.color.transparent));
                holder.iv_play_active.setVisibility(View.INVISIBLE );

            }


        }


        holder.tv_title.setText(getSongs.getSongTitle());
        holder.tv_artist.setText(getSongs.getArtist());
        String duration = Utility.convertDuration(Long.parseLong(getSongs.getSongDuration()));
        holder.tv_duration.setText(duration);

        holder.bind(getSongs, listener);

    }

    @Override
    public int getItemCount() {
        return arrayListSongs.size();
    }

    public class SongsAdapterViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_title , tv_artist , tv_duration;
        ImageView iv_play_active;
        public SongsAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_title = itemView.findViewById(R.id.tv_title);
            tv_artist = itemView.findViewById(R.id.tv_artist);
            tv_duration = itemView.findViewById(R.id.tv_duration);
            iv_play_active = itemView.findViewById(R.id.iv_play_active);
        }

        public void bind(GetSongs getSongs, RecyclerItemClickListener listener) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    listener.onClickListener(getSongs,getAdapterPosition());
                }
            });
        }
    }

    public interface RecyclerItemClickListener {

        void onClickListener (GetSongs songs, int position);
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
    }
}
