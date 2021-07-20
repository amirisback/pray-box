package com.frogobox.praybox.mvvm.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.frogobox.praybox.R
import com.frogobox.praybox.core.BaseFragment
import com.frogobox.praybox.mvvm.doa.DoaActivity
import com.frogobox.praybox.mvvm.tatacara.TataCaraActivity
import kotlinx.android.synthetic.main.ads_phone_tab_banner.*
import kotlinx.android.synthetic.main.fragment_lainnya.*

class LainnyaFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? { // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lainnya, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupShowAdsBanner(ads_phone_tab_banner)
        setupButtonClick()

    }

    private fun setupButtonClick(){
        btn_doa_activity.setOnClickListener {
            baseStartActivity<DoaActivity>()
            setupShowAdsInterstitial()
        }

        btn_tata_cara_activity.setOnClickListener {
            baseStartActivity<TataCaraActivity>()
            setupShowAdsInterstitial()
        }
    }


}