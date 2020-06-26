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

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.MyHolder> {
    private Context context;
    private List<MusicFiles> albumFiles;
    View view;

    public AlbumAdapter(Context context, List<MusicFiles> albumFiles) {
        this.context = context;
        this.albumFiles = albumFiles;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.item_album,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumAdapter.MyHolder holder, int position) {
        holder.album_name.setText(albumFiles.get(position).getAlbum());
        byte [] image = getAlbumArt(albumFiles.get(position).getPath());
        if (image != null){
            Glide.with(context).asBitmap().load(image).into(holder.album_image);
        }else {
            Glide.with(context).load(R.drawable.ic_launcher_background).into(holder.album_image);
        }

    }

    @Override
    public int getItemCount() {
        return albumFiles.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        private ImageView album_image;
        private TextView album_name;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            album_image = itemView.findViewById(R.id.album_image);
            album_name = itemView.findViewById(R.id.album_name);
        }
    }

    private byte [] getAlbumArt(String uri){
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(uri);
        byte [] art = retriever.getEmbeddedPicture();
        retriever.release();
        return art;
    }
}
