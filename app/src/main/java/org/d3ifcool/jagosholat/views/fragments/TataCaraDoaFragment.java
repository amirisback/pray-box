package org.d3ifcool.jagosholat.views.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.d3ifcool.jagosholat.models.json.TataCaraJSON;
import org.d3ifcool.jagosholat.views.adapters.recyclerview.DoaShalatRecyclerViewAdapter;
import org.d3ifcool.jagosholat.models.dataclass.DoaShalat;
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
        ArrayList<DoaShalat> arrayWords = TataCaraJSON.extractDoaShalat();
        RecyclerView mRecyclerView = (RecyclerView) rootView.findViewById(R.id.tatacara_listview_text);
        DoaShalatRecyclerViewAdapter adapter = new DoaShalatRecyclerViewAdapter(getContext(), arrayWords);
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