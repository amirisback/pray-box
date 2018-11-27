package org.d3ifcool.jagosholat.views.activities;

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
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import org.d3ifcool.jagosholat.R;
import org.d3ifcool.jagosholat.views.fragments.CatatanFragment;
import org.d3ifcool.jagosholat.views.fragments.JadwalFragment;
import org.d3ifcool.jagosholat.views.fragments.KiblatFragment;
import org.d3ifcool.jagosholat.views.fragments.StatistikFragment;
import org.d3ifcool.jagosholat.views.fragments.TataCaraFragment;
import org.d3ifcool.jagosholat.views.interfaces.ClickHandler;

import static org.d3ifcool.jagosholat.models.constants.VarConstants.Constants;

public class MainActivity extends AppCompatActivity implements ClickHandler {

    // ---------------------------------------------------------------------------------------------
    private boolean isTwoPane;
    private String mSelectedMenuTab;
    // ---------------------------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // -----------------------------------------------------------------------------------------
        isTwoPane = findViewById(R.id.detail_frame_layout) != null;
        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        // -----------------------------------------------------------------------------------------
        if (isTwoPane) {
            mSelectedMenuTab = savedInstanceState!=null ?
                    savedInstanceState.getString(Constants.STRING_EXTRA_FRAGMENT):Constants.TAB_CATATAN;
            menuTabClick(mSelectedMenuTab);
        }
        // -----------------------------------------------------------------------------------------

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (isTwoPane) outState.putString(Constants.STRING_EXTRA_FRAGMENT, mSelectedMenuTab);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void menuTabClick(String menuTab) {
        if (isTwoPane) {
            mSelectedMenuTab = Constants.STRING_EXTRA_FRAGMENT;
            Fragment mFragment;
            switch (menuTab) {
                case Constants.TAB_CATATAN:
                    mFragment = new CatatanFragment();
                    break;
                case Constants.TAB_JADWAL:
                    mFragment = new JadwalFragment();
                    break;
                case Constants.TAB_STATISTIK:
                    mFragment = new StatistikFragment();
                    break;
                case Constants.TAB_KIBLAT:
                    mFragment = new KiblatFragment();
                    break;
                case Constants.TAB_TATACARA:
                    mFragment = new TataCaraFragment();
                    break;
                default:
                    throw new IllegalArgumentException();
            }
            FragmentManager mFragmentManager = getSupportFragmentManager();
            mFragmentManager.beginTransaction().replace(R.id.detail_frame_layout, mFragment).commit();
        } else {
            // Create a new intent to open the {@link NumbersFragment}
            Intent mIntent = new Intent(this, DetailActivity.class);
            mIntent.putExtra(Constants.STRING_EXTRA_FRAGMENT, menuTab);
            // Start the new activity
            startActivity(mIntent);
        }
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

    public int getActionBarSize() {
        TypedArray styledAttributes = getTheme().obtainStyledAttributes(new int[] { android.R.attr.actionBarSize });
        int actionBarSize = (int) styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();
        return actionBarSize;
    }

}