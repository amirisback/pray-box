package org.d3ifcool.jagosholat.presenters.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.d3ifcool.jagosholat.R;

public class MainFragment extends Fragment {

    private ClickHandler mHandler;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mHandler = (ClickHandler) context;
    }

    public interface ClickHandler {
        void MenuClick(String menu);
    }


}
