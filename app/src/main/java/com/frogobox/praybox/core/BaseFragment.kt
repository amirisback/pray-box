package com.frogobox.praybox.core

import androidx.viewbinding.ViewBinding
import com.frogobox.admob.callback.FrogoAdmobInterstitialCallback
import com.frogobox.praybox.R
import com.frogobox.sdk.view.FrogoBindFragment
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
abstract class BaseFragment<VB : ViewBinding> : FrogoBindFragment<VB>() {

    private val mActivity: BaseActivity<*> by lazy {
        (activity as BaseActivity<*>)
    }

    protected fun setupShowAdsInterstitial(callback: BaseAdCallback) {
        mActivity.showAdInterstitial(getString(R.string.admob_interstitial_id),
            object : FrogoAdmobInterstitialCallback {
                override fun onAdLoaded(tag: String, message: String) {}

                override fun onAdShowed(tag: String, message: String) {}

                override fun onAdDismissed(tag: String, message: String) {
                    callback.onAfterLoad()
                }

                override fun onAdFailed(tag: String, errorMessage: String) {
                    callback.onAfterLoad()
                }

                override fun onHideAdRequestProgress(tag: String, message: String) {
                    callback.onHideProgress()
                }

                override fun onShowAdRequestProgress(tag: String, message: String) {
                    callback.onShowProgress()
                }
            })
    }

}