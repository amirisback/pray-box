package org.d3ifcool.jagosholat.Controller.ViewPagerAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import org.d3ifcool.jagosholat.Controller.MainContent.CatatanFragment;
import org.d3ifcool.jagosholat.Controller.MainContent.JadwalFragment;
import org.d3ifcool.jagosholat.Controller.MainContent.KompasFragment;
import org.d3ifcool.jagosholat.Controller.MainContent.StatistikFragment;
import org.d3ifcool.jagosholat.Controller.MainContent.TataCaraFragment;

/**
 * Created by Faisal Amir on 25/02/2018.
 */

public class MainPagerAdapter extends FragmentPagerAdapter {

    private int NumberTab;

    public MainPagerAdapter(FragmentManager fm, int numberTab) {
        super(fm);
        NumberTab = numberTab;
    }


    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0 :
                return new CatatanFragment();
            case 1 :
                return new JadwalFragment();
            case 2 :
                return new StatistikFragment();
            case 3 :
                return new KompasFragment();
            case 4 :
                return new TataCaraFragment();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return NumberTab;
    }
}
