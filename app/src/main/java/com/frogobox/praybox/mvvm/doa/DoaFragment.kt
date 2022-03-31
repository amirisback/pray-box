package com.frogobox.praybox.mvvm.doa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.frogobox.praybox.R
import com.frogobox.praybox.core.BaseFragment
import com.frogobox.praybox.databinding.FragmentTatacaraTextBinding
import com.frogobox.praybox.model.DoaShalat
import com.frogobox.praybox.util.TataCaraJSON
import com.frogobox.recycler.core.FrogoRecyclerNotifyListener
import com.frogobox.recycler.core.FrogoRecyclerViewListener

class DoaFragment : BaseFragment<FragmentTatacaraTextBinding>() {

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTatacaraTextBinding {
        return FragmentTatacaraTextBinding.inflate(inflater, container, false)
    }

    override fun setupViewModel() {
    }

    override fun setupOnViewCreated(view: View, savedInstanceState: Bundle?) {
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.apply {
            val arrayDoaShalat = TataCaraJSON.extractDoaShalat()
            val adapter = DoaViewAdapter()
            adapter.setupRequirement(R.layout.content_tatacara_text_doa, arrayDoaShalat,
                object : FrogoRecyclerViewListener<DoaShalat> {
                    override fun onItemClicked(
                        view: View,
                        data: DoaShalat,
                        position: Int,
                        notifyListener: FrogoRecyclerNotifyListener<DoaShalat>
                    ) {}

                    override fun onItemLongClicked(
                        view: View,
                        data: DoaShalat,
                        position: Int,
                        notifyListener: FrogoRecyclerNotifyListener<DoaShalat>
                    ) {}
                })
            tatacaraListviewText.setHasFixedSize(true)
            tatacaraListviewText.layoutManager = LinearLayoutManager(activity)
            tatacaraListviewText.adapter = adapter
        }
    }

}