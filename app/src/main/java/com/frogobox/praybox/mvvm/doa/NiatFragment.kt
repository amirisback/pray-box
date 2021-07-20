package com.frogobox.praybox.mvvm.doa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.frogobox.praybox.R
import com.frogobox.praybox.core.BaseFragment
import com.frogobox.praybox.model.NiatShalat
import com.frogobox.praybox.util.TataCaraJSON
import com.frogobox.recycler.core.FrogoRecyclerViewListener
import kotlinx.android.synthetic.main.fragment_tatacara_text.*

class NiatFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tatacara_text, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val arrayNiatShalat = TataCaraJSON.extractNiatShalat()
        val adapter = NiatViewAdapter()
        adapter.setupRequirement(R.layout.content_tatacara_text_niat, arrayNiatShalat,
            object : FrogoRecyclerViewListener<NiatShalat> {
                override fun onItemClicked(data: NiatShalat) {}
                override fun onItemLongClicked(data: NiatShalat) {}
            })
        tatacara_listview_text.setHasFixedSize(true)
        tatacara_listview_text.layoutManager = LinearLayoutManager(activity)
        tatacara_listview_text.adapter = adapter
    }

}