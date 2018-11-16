package org.d3ifcool.jagosholat.views.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.d3ifcool.jagosholat.R;
import org.d3ifcool.jagosholat.views.fragments.CatatanFragment;
import org.d3ifcool.jagosholat.views.fragments.JadwalFragment;
import org.d3ifcool.jagosholat.views.fragments.KompasFragment;
import org.d3ifcool.jagosholat.views.fragments.StatistikFragment;
import org.d3ifcool.jagosholat.views.fragments.TataCaraFragment;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        String category = getIntent().getStringExtra("category");
        String title;
        Fragment mFragment;

        switch (category) {
            case "catatan" :
                title = getString(R.string.menu_catatan);
                mFragment = new CatatanFragment();
                break;
            case "jadwal" :
                title = getString(R.string.menu_jadwal);
                mFragment = new JadwalFragment();
                break;
            case "statistik" :
                title = getString(R.string.menu_statistik);
                mFragment = new StatistikFragment();
                break;
            case "kiblat" :
                title = getString(R.string.menu_kiblat);
                mFragment = new KompasFragment();
                break;
            case "tatacara" :
                title = getString(R.string.menu_tatacara);
                mFragment = new TataCaraFragment();
                break;
            default:
                throw new IllegalArgumentException();
        }

        getSupportActionBar().setTitle(title);
        FragmentManager mFragmentManager = getSupportFragmentManager();
        mFragmentManager.beginTransaction().replace(R.id.detail_framelayout, mFragment).commit();
    }
}
