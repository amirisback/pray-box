package org.d3ifcool.jagosholat.views.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.d3ifcool.jagosholat.R;
import org.d3ifcool.jagosholat.views.fragments.CatatanFragment;
import org.d3ifcool.jagosholat.views.fragments.JadwalFragment;
import org.d3ifcool.jagosholat.views.fragments.KiblatFragment;
import org.d3ifcool.jagosholat.views.fragments.StatistikFragment;
import org.d3ifcool.jagosholat.views.fragments.TataCaraFragment;

public class DetailActivity extends AppCompatActivity {

    // ---------------------------------------------------------------------------------------------
    private final String STRING_EXTRA_FRAGMENT = "menufragment";
    private final String TAB_CATATAN = "catatan";
    private final String TAB_JADWAL = "jadwal";
    private final String TAB_STATISTIK = "statistik";
    private final String TAB_KIBLAT = "kiblat";
    private final String TAB_TATACARA = "tatacara";
    // ---------------------------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // -----------------------------------------------------------------------------------------
        String menuTab = getIntent().getStringExtra(STRING_EXTRA_FRAGMENT);
        String title;
        Fragment mFragment;
        // -----------------------------------------------------------------------------------------
        switch (menuTab) {
            case TAB_CATATAN :
                title = getString(R.string.menu_catatan);
                mFragment = new CatatanFragment();
                break;
            case TAB_JADWAL :
                title = getString(R.string.menu_jadwal);
                mFragment = new JadwalFragment();
                break;
            case TAB_STATISTIK :
                title = getString(R.string.menu_statistik);
                mFragment = new StatistikFragment();
                break;
            case TAB_KIBLAT :
                title = getString(R.string.menu_kiblat);
                mFragment = new KiblatFragment();
                break;
            case TAB_TATACARA :
                title = getString(R.string.menu_tatacara);
                mFragment = new TataCaraFragment();
                break;
            default:
                throw new IllegalArgumentException();
        }
        // -----------------------------------------------------------------------------------------
        getSupportActionBar().setTitle(title);
        FragmentManager mFragmentManager = getSupportFragmentManager();
        mFragmentManager.beginTransaction().replace(R.id.detail_framelayout, mFragment).commit();
        // -----------------------------------------------------------------------------------------
    }
}
