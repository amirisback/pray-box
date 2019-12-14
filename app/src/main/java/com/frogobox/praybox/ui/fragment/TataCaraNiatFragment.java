package com.frogobox.praybox.ui.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.frogobox.praybox.R;
import com.frogobox.praybox.model.NiatShalat;
import com.frogobox.praybox.util.helper.TataCaraJSON;
import com.frogobox.praybox.view.adapter.NiatShalatRecyclerViewAdapter;

import java.util.ArrayList;

public class TataCaraNiatFragment extends Fragment {

    public TataCaraNiatFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_tatacara_text, container, false);
        // -----------------------------------------------------------------------------------------
        ArrayList<NiatShalat> arrayNiatShalat = TataCaraJSON.extractNiatShalat();
        RecyclerView mRecyclerView = rootView.findViewById(R.id.tatacara_listview_text);
        // -----------------------------------------------------------------------------------------
        NiatShalatRecyclerViewAdapter adapter = new NiatShalatRecyclerViewAdapter(getContext(), arrayNiatShalat);
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