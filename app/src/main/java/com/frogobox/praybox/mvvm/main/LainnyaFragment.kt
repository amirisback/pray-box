package com.frogobox.praybox.mvvm.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.frogobox.praybox.core.BaseAdCallback
import com.frogobox.praybox.core.BaseFragment
import com.frogobox.praybox.databinding.FragmentLainnyaBinding
import com.frogobox.praybox.mvvm.doa.DoaActivity
import com.frogobox.praybox.mvvm.tatacara.TataCaraActivity
import com.frogobox.sdk.ext.gone
import com.frogobox.sdk.ext.startActivityExt
import com.frogobox.sdk.ext.visible

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
        setupButtonClick()
    }

    private fun setupButtonClick() {
        binding.apply {
            btnDoaActivity.setOnClickListener {
                setupShowAdsInterstitial(object : BaseAdCallback {

                    override fun onAfterLoad() {
                        requireContext().startActivityExt<DoaActivity>()
                    }

                    override fun onShowProgress() {
                        progressBar.visible()
                    }

                    override fun onHideProgress() {
                        progressBar.gone()
                    }

                })
            }

            btnTataCaraActivity.setOnClickListener {
                setupShowAdsInterstitial(object : BaseAdCallback {

                    override fun onAfterLoad() {
                        requireContext().startActivityExt<TataCaraActivity>()
                    }

                    override fun onShowProgress() {
                        progressBar.visible()
                    }

                    override fun onHideProgress() {
                        progressBar.gone()
                    }

                })
            }
        }
    }


}