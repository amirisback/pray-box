package org.d3ifcool.jagosholat.views;

/**
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
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import org.d3ifcool.jagosholat.presenters.adapters.MainPagerAdapter;
import org.d3ifcool.jagosholat.R;

public class MainActivity extends AppCompatActivity {

    // ---------------------------------------------------------------------------------------------
    // Deklarasi Kebutuhan
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    // ---------------------------------------------------------------------------------------------
    // Deklarasi Judul
    private String[] pageTitle = {"Catatan", "Jadwal", "Statistik", "Kiblat", "Tata Cara"};
    private int[] pageIcon = {R.drawable.ic_main_catat, R.drawable.ic_main_jadwal,
            R.drawable.ic_main_statistik, R.drawable.ic_main_kompas, R.drawable.ic_main_more};
    // ---------------------------------------------------------------------------------------------


    public int getActionBarSize() {
        TypedArray styledAttributes = getTheme().obtainStyledAttributes(new int[] { android.R.attr.actionBarSize });
        int actionBarSize = (int) styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();
        return actionBarSize;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // -----------------------------------------------------------------------------------------
        // Deklarasi element XML
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        // -----------------------------------------------------------------------------------------
        // Menjalankan Fungsi
        SlideView();// Fungsi Slide di gunakan untuk ViewPager sliding Fragments
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


    public void SlideView(){
        // -----------------------------------------------------------------------------------------
        mTabLayout = (TabLayout) findViewById(R.id.main_tablayout);
        mViewPager = (ViewPager)findViewById(R.id.main_viewpager);
        // -----------------------------------------------------------------------------------------
        for (int i = 0 ; i < pageTitle.length; i ++){
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
    }
}