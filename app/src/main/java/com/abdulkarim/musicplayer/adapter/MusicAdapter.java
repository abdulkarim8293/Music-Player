package com.abdulkarim.musicplayer.adapter;

import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abdulkarim.musicplayer.model.MusicFiles;
import com.abdulkarim.musicplayer.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MyViewHolder> {

    private Context context;
    private List<MusicFiles> musicFiles;

    public MusicAdapter(Context context, List<MusicFiles> musicFiles) {
        this.context = context;
        this.musicFiles = musicFiles;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_music,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.file_name.setText(musicFiles.get(position).getTitle());
        byte [] image = getAlbumArt(musicFiles.get(position).getPath());
        if (image != null){
            Glide.with(context).asBitmap().load(image).into(holder.file_image);
        }else {
            Glide.with(context).load(R.drawable.ic_launcher_background).into(holder.file_image);
        }

    }

    @Override
    public int getItemCount() {
        return musicFiles.size();
    }
    private byte [] getAlbumArt(String uri){
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(uri);
        byte [] art = retriever.getEmbeddedPicture();
        retriever.release();
        return art;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView file_name;
        private ImageView file_image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            file_name = itemView.findViewById(R.id.music_file_name);
            file_image = itemView.findViewById(R.id.music_image);
        }
    }
}
