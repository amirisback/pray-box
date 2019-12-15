package com.frogobox.praybox.view.ui.activitiy

import android.os.Bundle
import com.frogobox.praybox.R
import com.frogobox.praybox.base.view.ui.BaseActivity
import com.frogobox.praybox.view.ui.fragment.TataCaraShalatFragment
import com.frogobox.praybox.view.ui.fragment.TataCaraWudhuFragment
import com.frogobox.praybox.util.helper.PagerHelper
import kotlinx.android.synthetic.main.activity_tata_cara.*

class TataCaraActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tata_cara)
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
