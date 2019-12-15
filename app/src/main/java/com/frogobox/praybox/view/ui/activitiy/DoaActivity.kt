package com.frogobox.praybox.view.ui.activitiy

import android.os.Bundle
import com.frogobox.praybox.R
import com.frogobox.praybox.base.view.ui.BaseActivity
import com.frogobox.praybox.view.ui.fragment.DoaFragment
import com.frogobox.praybox.view.ui.fragment.NiatFragment
import com.frogobox.praybox.util.helper.PagerHelper
import kotlinx.android.synthetic.main.activity_doa.*

class DoaActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doa)
        setupDetailActivity("")
        supportActionBar?.elevation = 0f
        setupViewPager()
    }

    private fun setupViewPager() {
        val pagerAdapter = PagerHelper(supportFragmentManager)
        pagerAdapter.setupPagerFragment(NiatFragment(), resources.getString(R.string.btn_niat_sholat))
        pagerAdapter.setupPagerFragment(DoaFragment(), resources.getString(R.string.btn_doa))
        viewpager.adapter = pagerAdapter
        tablayout.setupWithViewPager(viewpager)
    }

}
