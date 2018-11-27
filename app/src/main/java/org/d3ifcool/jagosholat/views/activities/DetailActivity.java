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

import static org.d3ifcool.jagosholat.models.constants.VarConstants.Constants;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // -----------------------------------------------------------------------------------------
        String menuTab = getIntent().getStringExtra(Constants.STRING_EXTRA_FRAGMENT);
        String title;
        Fragment mFragment;
        // -----------------------------------------------------------------------------------------
        switch (menuTab) {
            case Constants.TAB_CATATAN :
                title = getString(R.string.menu_catatan);
                mFragment = new CatatanFragment();
                break;
            case Constants.TAB_JADWAL :
                title = getString(R.string.menu_jadwal);
                mFragment = new JadwalFragment();
                break;
            case Constants.TAB_STATISTIK :
                title = getString(R.string.menu_statistik);
                mFragment = new StatistikFragment();
                break;
            case Constants.TAB_KIBLAT :
                title = getString(R.string.menu_kiblat);
                mFragment = new KiblatFragment();
                break;
            case Constants.TAB_TATACARA :
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
