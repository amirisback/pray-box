package org.d3ifcool.jagosholat.presenters.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.d3ifcool.jagosholat.presenters.adapters.DoaShalatRecyclerAdapter;
import org.d3ifcool.jagosholat.presenters.helpers.JSONHelper;
import org.d3ifcool.jagosholat.models.DataDoaShalat;
import org.d3ifcool.jagosholat.R;

import java.util.ArrayList;

public class TataCaraDoaFragment extends Fragment {

    public TataCaraDoaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_tatacara_text, container, false);
        // -----------------------------------------------------------------------------------------
        ArrayList<DataDoaShalat> arrayWords = JSONHelper.extractDoaShalat();
        RecyclerView mRecyclerView = (RecyclerView) rootView.findViewById(R.id.tatacara_listview_text);
        DoaShalatRecyclerAdapter adapter = new DoaShalatRecyclerAdapter(getContext(), arrayWords);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        DividerItemDecoration divider = new DividerItemDecoration(getContext(), mLayoutManager.getOrientation());
        // -----------------------------------------------------------------------------------------
        mRecyclerView.addItemDecoration(divider);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(adapter);
        // -----------------------------------------------------------------------------------------
        return rootView;
    }
}