package com.frogobox.praybox.core

import androidx.viewbinding.ViewBinding
import com.frogobox.praybox.R
import com.frogobox.sdk.FrogoFragment
import com.google.android.gms.ads.AdView

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * PublicSpeakingBooster
 * Copyright (C) 16/08/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * com.frogobox.publicspeakingbooster.base
 *
 */
abstract class BaseFragment<VB : ViewBinding> : FrogoFragment<VB>() {

    private val mActivity: BaseActivity<*> by lazy {
        (activity as BaseActivity<*>)
    }

    protected fun setupShowAdsBanner(ads: AdView) {
        mActivity.showAdBanner(ads)
    }

    protected fun setupShowAdsInterstitial() {
        mActivity.showAdInterstitial(getString(R.string.admob_interstitial))
    }

}