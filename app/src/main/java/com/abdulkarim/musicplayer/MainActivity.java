package com.abdulkarim.musicplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.abdulkarim.musicplayer.fragments.AlbumFragment;
import com.abdulkarim.musicplayer.fragments.ArtistFragment;
import com.abdulkarim.musicplayer.fragments.PlayListFragment;
import com.abdulkarim.musicplayer.fragments.SongFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();

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

        tabLayout.setupWithViewPager(viewPager);
        viewPagerAdapter = new ViewPagerAdapter(fragmentList,titleList,getSupportFragmentManager(),10);

        viewPager.setAdapter(viewPagerAdapter);
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //setSearchtollbar();
    }
}
