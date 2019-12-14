package com.frogobox.praybox.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.frogobox.praybox.R
import com.frogobox.praybox.base.view.ui.BaseFragment
import com.frogobox.praybox.util.helper.PagerHelper
import kotlinx.android.synthetic.main.fragment_viewpager_tab.*

class LainnyaFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? { // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_viewpager_tab, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewPager()
    }

    private fun setupViewPager() {
        val pagerAdapter = PagerHelper(childFragmentManager)
        pagerAdapter.setupPagerFragment(TataCaraWudhuFragment(), resources.getString(R.string.btn_tutor_wudhu))
        pagerAdapter.setupPagerFragment(TataCaraNiatFragment(), resources.getString(R.string.btn_niat_sholat))
        pagerAdapter.setupPagerFragment(TataCaraShalatFragment(), resources.getString(R.string.btn_tutor_sholat))
        pagerAdapter.setupPagerFragment(TataCaraDoaFragment(), resources.getString(R.string.btn_doa))
        viewpager.adapter = pagerAdapter
        tablayout.setupWithViewPager(viewpager)
    }

}