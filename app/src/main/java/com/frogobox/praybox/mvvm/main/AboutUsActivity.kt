package com.frogobox.praybox.mvvm.main

import android.os.Bundle
import com.frogobox.praybox.core.BaseActivity
import com.frogobox.praybox.databinding.ActivityAboutUsBinding

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * 40-mobpro-4002-jago-sholat
 * Copyright (C) 05/09/2018.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Line     : bullbee117
 * Phone    : 081357108568
 * Majors   : D3 Teknik Informatika 2016
 * Campus   : Telkom University
 * -----------------------------------------
 * id.amirisback.frogobox
 */
class AboutUsActivity : BaseActivity<ActivityAboutUsBinding>() {

    override fun setupViewBinding(): ActivityAboutUsBinding {
        return ActivityAboutUsBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() {
    }

    override fun setupOnCreate(savedInstanceState: Bundle?) {
        setupDetailActivity("")
    }

}