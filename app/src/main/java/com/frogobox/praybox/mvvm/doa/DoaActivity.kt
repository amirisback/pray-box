package com.frogobox.praybox.mvvm.doa

import android.os.Bundle
import com.frogobox.praybox.R
import com.frogobox.praybox.core.BaseActivity
import com.frogobox.praybox.databinding.ActivityDoaBinding
import com.frogobox.praybox.util.PagerHelper

class DoaActivity : BaseActivity<ActivityDoaBinding>() {

    override fun setupViewBinding(): ActivityDoaBinding {
        return ActivityDoaBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() {
    }

    override fun setupUI(savedInstanceState: Bundle?) {
        setupDetailActivity("")
        supportActionBar?.elevation = 0f
        setupViewPager()
    }

    private fun setupViewPager() {
        binding.apply {
            val pagerAdapter = PagerHelper(supportFragmentManager)
            pagerAdapter.setupPagerFragment(
                NiatFragment(),
                resources.getString(R.string.btn_niat_sholat)
            )
            pagerAdapter.setupPagerFragment(DoaFragment(), resources.getString(R.string.btn_doa))
            viewpager.adapter = pagerAdapter
            tablayout.setupWithViewPager(viewpager)
        }
    }

}