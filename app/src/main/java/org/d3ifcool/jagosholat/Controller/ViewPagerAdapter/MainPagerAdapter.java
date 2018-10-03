package org.d3ifcool.jagosholat.Controller.ViewPagerAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import org.d3ifcool.jagosholat.Controller.MainContent.CatatanFragment;
import org.d3ifcool.jagosholat.Controller.MainContent.StatistikFragment;
import org.d3ifcool.jagosholat.Controller.MainContent.JadwalFragment;
import org.d3ifcool.jagosholat.Controller.MainContent.KompasFragment;
import org.d3ifcool.jagosholat.Controller.MainContent.TataCaraFragment;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * JagoSholat
 * Copyright (C) 12/09/2018.
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
public class MainPagerAdapter extends FragmentPagerAdapter{

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
