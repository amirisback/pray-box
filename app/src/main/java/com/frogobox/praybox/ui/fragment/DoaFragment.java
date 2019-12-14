package com.frogobox.praybox.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.frogobox.praybox.R;
import com.frogobox.praybox.base.view.ui.BaseFragment;
import com.frogobox.praybox.model.DoaShalat;
import com.frogobox.praybox.util.helper.TataCaraJSON;
import com.frogobox.praybox.view.adapter.DoaShalatRecyclerViewAdapter;

import java.util.ArrayList;

public class DoaFragment extends BaseFragment {

    public DoaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_tatacara_text, container, false);
        // -----------------------------------------------------------------------------------------
        ArrayList<DoaShalat> arrayDoaShalat = TataCaraJSON.extractDoaShalat();
        RecyclerView mRecyclerView = rootView.findViewById(R.id.tatacara_listview_text);
        // -----------------------------------------------------------------------------------------
        DoaShalatRecyclerViewAdapter adapter = new DoaShalatRecyclerViewAdapter(getContext(), arrayDoaShalat);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        // -----------------------------------------------------------------------------------------
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(adapter);
        // -----------------------------------------------------------------------------------------
        return rootView;
    }
}