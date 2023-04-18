package com.frogobox.praybox.mvvm.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.frogobox.praybox.core.BaseFragment
import com.frogobox.praybox.databinding.FragmentLainnyaBinding
import com.frogobox.praybox.mvvm.doa.DoaActivity
import com.frogobox.praybox.mvvm.tatacara.TataCaraActivity
import com.frogobox.sdk.ext.startActivityExt

class LainnyaFragment : BaseFragment<FragmentLainnyaBinding>() {

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLainnyaBinding {
        return FragmentLainnyaBinding.inflate(inflater, container, false)
    }

    override fun setupViewModel() {
    }

    override fun onViewCreatedExt(view: View, savedInstanceState: Bundle?) {
        super.onViewCreatedExt(view, savedInstanceState)
        binding.ads.adsPhoneTabBanner?.let { setupShowAdsBanner(it) }
        setupButtonClick()
    }

    private fun setupButtonClick() {
        binding.apply {
            btnDoaActivity.setOnClickListener {
                requireContext().startActivityExt<DoaActivity>()
                setupShowAdsInterstitial()
            }

            btnTataCaraActivity.setOnClickListener {
                requireContext().startActivityExt<TataCaraActivity>()
                setupShowAdsInterstitial()
            }
        }
    }


}