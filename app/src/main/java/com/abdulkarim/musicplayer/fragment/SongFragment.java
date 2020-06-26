package com.abdulkarim.musicplayer.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abdulkarim.musicplayer.R;
import com.abdulkarim.musicplayer.adapter.MusicAdapter;

import static com.abdulkarim.musicplayer.activity.MainActivity.musicFiles;

public class SongFragment extends Fragment {

    private RecyclerView recyclerView;
    private MusicAdapter musicAdapter;

    public SongFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_songs, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewId);
        recyclerView.setHasFixedSize(true);
        if (!(musicFiles.size() < 1)){
            musicAdapter = new MusicAdapter(getContext(),musicFiles);
            recyclerView.setAdapter(musicAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        }

        return view;
    }

}
