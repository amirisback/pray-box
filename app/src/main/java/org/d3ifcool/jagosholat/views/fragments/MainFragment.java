package org.d3ifcool.jagosholat.views.fragments;


import android.graphics.PorterDuff;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.d3ifcool.jagosholat.R;
import org.d3ifcool.jagosholat.views.adapters.viewpagers.MainPagerAdapter;

public class MainFragment extends Fragment {

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        // -----------------------------------------------------------------------------------------
        // Deklarasi element XML
        final TabLayout mTabLayout = rootView.findViewById(R.id.main_tablayout);
        final ViewPager mViewPager = rootView.findViewById(R.id.main_viewpager);
        // -----------------------------------------------------------------------------------------
        final String[] pageTitle = {"Catatan", "Jadwal", "Statistik", "Kiblat", "Tata Cara"};
        int[] pageIcon = {R.drawable.ic_main_catat, R.drawable.ic_main_jadwal,
                R.drawable.ic_main_statistik, R.drawable.ic_main_kompas, R.drawable.ic_main_more};
        // -----------------------------------------------------------------------------------------
        // Menjalankan Fungsi
        for (int i = 0 ; i < pageTitle.length; i ++){
            mTabLayout.addTab(mTabLayout.newTab().setIcon(pageIcon[i]));
        }
        // -----------------------------------------------------------------------------------------
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        MainPagerAdapter Adatapters = new MainPagerAdapter(getChildFragmentManager(), mTabLayout.getTabCount());
        mViewPager.setAdapter(Adatapters);
        // -----------------------------------------------------------------------------------------
        mViewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
                getActivity().setTitle(pageTitle[tab.getPosition()]);
                int tabIconColor = ContextCompat.getColor(getContext(), R.color.IconSelect);
                tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
                // ---------------------------------------------------------------------------------
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                int tabIconColor = ContextCompat.getColor(getActivity(), R.color.IconUnselect);
                tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        // -----------------------------------------------------------------------------------------

        return rootView;
    }

}
