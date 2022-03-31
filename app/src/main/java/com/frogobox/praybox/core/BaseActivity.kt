package com.frogobox.praybox.core

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.viewbinding.ViewBinding
import com.frogobox.admob.ui.FrogoSdkAdmobActivity
import com.frogobox.praybox.R
import com.frogobox.praybox.util.SingleConstant


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
abstract class BaseActivity<VB : ViewBinding> : FrogoSdkAdmobActivity<VB>() {

    protected fun setupCustomTitleToolbar(title: Int) {
        supportActionBar?.setTitle(title)
    }

    protected fun setupNoLimitStatBar() {
        val windows = window // in Activity's onCreate() for instance
        windows.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }

    protected inline fun <reified ClassActivity> setupSplashScreen() {
        Handler().postDelayed({
            frogoStartActivity<ClassActivity>()
            this@BaseActivity.finish()
        }, SingleConstant.Const.SPLASH_INTERVAL.toLong())
    }

    override fun setupDetailActivity(title: String) {
        setTitle(title)
        val upArrow = ContextCompat.getDrawable(this, R.drawable.ic_toolbar_back_home)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(upArrow)
        supportActionBar?.setBackgroundDrawable(
            ColorDrawable(
                ContextCompat.getColor(
                    this,
                    R.color.colorBaseWhite
                )
            )
        )
    }

}