package org.d3ifcool.jagosholat.views.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.d3ifcool.jagosholat.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class StatistikFragment extends Fragment {


    public StatistikFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_statistik, container, false);
        // -----------------------------------------------------------------------------------------
        // Deklarasi Element XML
        Button btn_statGrafik = rootView.findViewById(R.id.button_statistik_grafik);
        Button btn_statHarian = rootView.findViewById(R.id.button_statistik_harian);
        // -----------------------------------------------------------------------------------------
        // Fragment Pertama yang di panggil yaitu Grafik
        final StatistikHarianFragment mStatistikHarianFragment = new StatistikHarianFragment();
        final StatistikGrafikFragment mStatistikGrafikFragment = new StatistikGrafikFragment();
        final int mFrameLayout = R.id.framelayout_statistik;
        exchangeFragment(mFrameLayout, mStatistikHarianFragment);
        // -----------------------------------------------------------------------------------------
        // Button pada Statistik Fragment
        btn_statHarian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exchangeFragment(mFrameLayout, mStatistikHarianFragment);
            }
        });

        btn_statGrafik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exchangeFragment(mFrameLayout, mStatistikGrafikFragment);
            }
        });
        // -----------------------------------------------------------------------------------------

        return rootView;
    }

    // Method untuk pindah Fragment ----------------------------------------------------------------
    public void exchangeFragment(int frameLayout, Fragment mFragment){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(frameLayout, mFragment);
        ft.commit();
    }
    // ---------------------------------------------------------------------------------------------
}