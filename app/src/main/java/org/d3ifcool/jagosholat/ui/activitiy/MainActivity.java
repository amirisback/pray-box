package org.d3ifcool.jagosholat.ui.activitiy;

/*
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * JagoSholat
 * Copyright (C) 25/04/2018.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Line     : bullbee117
 * Phone    : 081357108568
 * Majors   : D3 Teknik Informatika 2016
 * Campus   : Telkom University
 * -----------------------------------------
 * id.amirisback.frogobox
 */

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import org.d3ifcool.jagosholat.R;
import org.d3ifcool.jagosholat.view.pager.MainPagerAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // -----------------------------------------------------------------------------------------
        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        // -----------------------------------------------------------------------------------------

        // Deklarasi element XML
        final TabLayout mTabLayout = findViewById(R.id.main_tablayout);
        final ViewPager mViewPager = findViewById(R.id.main_viewpager);
        // -----------------------------------------------------------------------------------------
        final String[] pageTitle = {"Catatan", "Jadwal", "Statistik", "Kiblat", "Tata Cara"};
        int[] pageIcon = {R.drawable.ic_main_catat, R.drawable.ic_main_jadwal,
                R.drawable.ic_main_statistik, R.drawable.ic_main_kompas, R.drawable.ic_main_more};
        // -----------------------------------------------------------------------------------------
        // Menjalankan Fungsi
        for (int i = 0; i < pageTitle.length; i++) {
            mTabLayout.addTab(mTabLayout.newTab().setIcon(pageIcon[i]));
        }
        // -----------------------------------------------------------------------------------------
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        MainPagerAdapter Adatapters = new MainPagerAdapter(getSupportFragmentManager(), mTabLayout.getTabCount());
        mViewPager.setAdapter(Adatapters);
        // -----------------------------------------------------------------------------------------
        mViewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
                setTitle(pageTitle[tab.getPosition()]);
                int tabIconColor = ContextCompat.getColor(getApplicationContext(), R.color.IconSelect);
                tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
                // ---------------------------------------------------------------------------------
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                int tabIconColor = ContextCompat.getColor(getApplicationContext(), R.color.IconUnselect);
                tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        // -----------------------------------------------------------------------------------------

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // Membuat Tombol Menu di Ujung Kanan Aplikasi
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Melihat Info Apps dengan cara Toolbar
        switch (item.getItemId()) {
            case R.id.info_apps:
                Intent i = new Intent(MainActivity.this, TentangKamiActivity.class);
                startActivity(i);
                return true;
        }
        //noinspection SimplifiableIfStatemen
        return super.onOptionsItemSelected(item);
    }


}