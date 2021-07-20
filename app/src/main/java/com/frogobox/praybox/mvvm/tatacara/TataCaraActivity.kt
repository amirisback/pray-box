package com.frogobox.praybox.mvvm.tatacara

import android.os.Bundle
import com.frogobox.praybox.R
import com.frogobox.praybox.core.BaseActivity
import com.frogobox.praybox.databinding.ActivityTataCaraBinding
import com.frogobox.praybox.util.helper.PagerHelper
import kotlinx.android.synthetic.main.activity_tata_cara.*

class TataCaraActivity : BaseActivity<ActivityTataCaraBinding>() {

    override fun setupViewBinding(): ActivityTataCaraBinding {
        return ActivityTataCaraBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() {
    }

    override fun setupUI(savedInstanceState: Bundle?) {
        setupDetailActivity("")
        supportActionBar?.elevation = 0f
        setupViewPager()
    }

    private fun setupViewPager() {
        val pagerAdapter = PagerHelper(supportFragmentManager)
        pagerAdapter.setupPagerFragment(TataCaraWudhuFragment(), resources.getString(R.string.btn_tutor_wudhu))
        pagerAdapter.setupPagerFragment(TataCaraShalatFragment(), resources.getString(R.string.btn_tutor_sholat))
        viewpager.adapter = pagerAdapter
        tablayout.setupWithViewPager(viewpager)
    }


}
