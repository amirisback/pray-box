package org.d3ifcool.jagosholat.views.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.d3ifcool.jagosholat.R;
import org.d3ifcool.jagosholat.views.interfaces.ClickHandler;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainTabFragment extends Fragment implements ClickHandler {

    private ClickHandler mHandler;
    public final String TAB_CATATAN = "catatan";
    public final String TAB_JADWAL = "jadwal";
    public final String TAB_STATISTIK = "statistik";
    public final String TAB_KIBLAT = "kiblat";
    public final String TAB_TATACARA = "tatacara";

    public MainTabFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_main_tab, container, false);
        // -----------------------------------------------------------------------------------------
        TextView mTextViewMenuCatatan = rootView.findViewById(R.id.menu_tab_catatan);
        TextView mTextViewMenuJadwal = rootView.findViewById(R.id.menu_tab_jadwal);
        TextView mTextViewMenuStatistik = rootView.findViewById(R.id.menu_tab_statistik);
        TextView mTextViewMenuKiblat = rootView.findViewById(R.id.menu_tab_kiblat);
        TextView mTextViewMenuTatacara = rootView.findViewById(R.id.menu_tab_tatacara);
        // -----------------------------------------------------------------------------------------
        mTextViewMenuCatatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHandler.menuTabClick(TAB_CATATAN);
            }
        });
        // -----------------------------------------------------------------------------------------
        mTextViewMenuJadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHandler.menuTabClick(TAB_JADWAL);
            }
        });
        // -----------------------------------------------------------------------------------------
        mTextViewMenuStatistik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHandler.menuTabClick(TAB_STATISTIK);
            }
        });
        // -----------------------------------------------------------------------------------------
        mTextViewMenuKiblat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHandler.menuTabClick(TAB_KIBLAT);
            }
        });
        // -----------------------------------------------------------------------------------------
        mTextViewMenuTatacara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHandler.menuTabClick(TAB_TATACARA);
            }
        });
        // -----------------------------------------------------------------------------------------

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mHandler = (ClickHandler) context;
    }

    @Override
    public void menuTabClick(String menuTab) {
    }
}
