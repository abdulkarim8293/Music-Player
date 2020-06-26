package com.abdulkarim.musicplayer.activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.abdulkarim.musicplayer.model.MusicFiles;
import com.abdulkarim.musicplayer.R;
import com.abdulkarim.musicplayer.adapter.ViewPagerAdapter;
import com.abdulkarim.musicplayer.fragment.AlbumFragment;
import com.abdulkarim.musicplayer.fragment.ArtistFragment;
import com.abdulkarim.musicplayer.fragment.PlayListFragment;
import com.abdulkarim.musicplayer.fragment.SongFragment;
import com.google.android.material.tabs.TabLayout;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1;
    public static List<MusicFiles> musicFiles;

    private Toolbar searchtollbar;
    private Menu search_menu;
    private MenuItem item_search;
    private LinearLayout linearLayout;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        musicFiles = new ArrayList<>();

        permission();


        List<Fragment> fragmentList = new ArrayList<>();

        fragmentList.add(new PlayListFragment());
        fragmentList.add(new ArtistFragment());
        fragmentList.add(new AlbumFragment());
        fragmentList.add(new SongFragment());

        List<String> titleList = new ArrayList<>();
        titleList.add("PLAYLIST");
        titleList.add("ARTISTS");
        titleList.add("ALBUMS");
        titleList.add("SONGS");


        tabLayout = findViewById(R.id.tabLayoutId);
        viewPager = findViewById(R.id.viewPagerId);

        linearLayout = findViewById(R.id.tabLayoutSection);

        tabLayout.setupWithViewPager(viewPager);
        viewPagerAdapter = new ViewPagerAdapter(fragmentList, titleList, getSupportFragmentManager(), 10);

        viewPager.setAdapter(viewPagerAdapter);
    }

    private void permission() {
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
        } else {

            musicFiles = getAllAudio(this);
            Toast.makeText(this, "Success" + musicFiles.get(1).getTitle(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {


                musicFiles = getAllAudio(this);
                Toast.makeText(this, "Success " + musicFiles.size(), Toast.LENGTH_SHORT).show();

            } else {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
            }
        }
    }

    public static ArrayList<MusicFiles> getAllAudio(Context context) {
        ArrayList<MusicFiles> audioList = new ArrayList<>();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {
                MediaStore.Audio.Media.ALBUM,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.ARTIST,
        };
        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String album = cursor.getString(0);
                String title = cursor.getString(1);
                String duration = cursor.getString(2);
                String path = cursor.getString(3);
                String artist = cursor.getString(4);

                MusicFiles musicFiles = new MusicFiles(path, title, artist, album, duration);
                audioList.add(musicFiles);
            }
            cursor.close();
        }
        return audioList;
    }

}
