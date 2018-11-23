package org.d3ifcool.jagosholat.views.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.d3ifcool.jagosholat.views.adapters.viewpagers.TataCaraPagerAdapter;
import org.d3ifcool.jagosholat.R;

public class TataCaraFragment extends Fragment {

    public TataCaraFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_tatacara, container, false);
        // -----------------------------------------------------------------------------------------
        // Deklarasi Element XML
        TabLayout mTabLayout = rootView.findViewById(R.id.tatacara_tablayout);
        ViewPager mViewPager = rootView.findViewById(R.id.tatacara_viewpager);
        // -----------------------------------------------------------------------------------------
        // Membuat ViewPager (SLIDER)
        TataCaraPagerAdapter tataCaraPagerAdapter = new TataCaraPagerAdapter(getActivity(),getChildFragmentManager());
        mViewPager.setAdapter(tataCaraPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        // -----------------------------------------------------------------------------------------
        return rootView;
    }
}