package com.abdulkarim.musicplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.abdulkarim.musicplayer.fragments.MusicFragment;
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

        List<Fragment> fragmentList = new ArrayList<>();

        fragmentList.add(new PlayListFragment());
        fragmentList.add(new SongFragment());
        fragmentList.add(new MusicFragment());

        List<String> titleList = new ArrayList<>();
        titleList.add("PLAYLIST");
        titleList.add("SONGS");
        titleList.add("MUSIC");

        tabLayout = findViewById(R.id.tabLayoutId);
        viewPager = findViewById(R.id.viewPagerId);
        viewPagerAdapter = new ViewPagerAdapter(fragmentList,titleList,getSupportFragmentManager(),10);

        viewPager.setAdapter(viewPagerAdapter);
    }
}
