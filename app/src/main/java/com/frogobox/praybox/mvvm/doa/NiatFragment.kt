package com.frogobox.praybox.mvvm.doa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.frogobox.praybox.R
import com.frogobox.praybox.core.BaseFragment
import com.frogobox.praybox.databinding.FragmentTatacaraTextBinding
import com.frogobox.praybox.model.NiatShalat
import com.frogobox.praybox.util.TataCaraJSON
import com.frogobox.recycler.core.FrogoRecyclerViewListener

class NiatFragment : BaseFragment<FragmentTatacaraTextBinding>() {

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup
    ): FragmentTatacaraTextBinding {
        return FragmentTatacaraTextBinding.inflate(inflater, container, false)
    }

    override fun setupViewModel() {
    }

    override fun setupUI(savedInstanceState: Bundle?) {
        binding?.apply {

            val arrayNiatShalat = TataCaraJSON.extractNiatShalat()
            val adapter = NiatViewAdapter()
            adapter.setupRequirement(R.layout.content_tatacara_text_niat, arrayNiatShalat,
                object : FrogoRecyclerViewListener<NiatShalat> {
                    override fun onItemClicked(data: NiatShalat) {}
                    override fun onItemLongClicked(data: NiatShalat) {}
                })
            tatacaraListviewText.setHasFixedSize(true)
            tatacaraListviewText.layoutManager = LinearLayoutManager(activity)
            tatacaraListviewText.adapter = adapter
        }
    }

}