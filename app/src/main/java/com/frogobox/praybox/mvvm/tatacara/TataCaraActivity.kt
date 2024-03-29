package com.frogobox.praybox.mvvm.tatacara

import android.os.Bundle
import com.frogobox.praybox.R
import com.frogobox.praybox.core.BaseActivity
import com.frogobox.praybox.databinding.ActivityTataCaraBinding
import com.frogobox.praybox.util.PagerHelper

class TataCaraActivity : BaseActivity<ActivityTataCaraBinding>() {

    override fun setupViewBinding(): ActivityTataCaraBinding {
        return ActivityTataCaraBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() {
    }

    override fun onCreateExt(savedInstanceState: Bundle?) {
        super.onCreateExt(savedInstanceState)
        setupDetailActivity("")
        supportActionBar?.elevation = 0f
        setupViewPager()
    }

    private fun setupViewPager() {
        binding.apply {
            val pagerAdapter = PagerHelper(supportFragmentManager)
            pagerAdapter.setupPagerFragment(
                TataCaraWudhuFragment(),
                resources.getString(R.string.btn_tutor_wudhu)
            )
            pagerAdapter.setupPagerFragment(
                TataCaraShalatFragment(),
                resources.getString(R.string.btn_tutor_sholat)
            )
            viewpager.adapter = pagerAdapter
            tablayout.setupWithViewPager(viewpager)
        }
    }


}
