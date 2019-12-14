package com.frogobox.praybox.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.frogobox.praybox.R
import com.frogobox.praybox.base.view.ui.BaseFragment
import com.frogobox.praybox.util.helper.PagerHelper
import kotlinx.android.synthetic.main.fragment_viewpager_tab.*

/**
 * A simple [Fragment] subclass.
 */
class StatistikFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? { // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_viewpager_tab, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewPager()
    }

    private fun setupViewPager(){
        val pagerAdapter = PagerHelper(childFragmentManager)
        pagerAdapter.setupPagerFragment(StatistikHarianFragment(), resources.getString(R.string.statistik_harian))
        pagerAdapter.setupPagerFragment(StatistikGrafikFragment(), resources.getString(R.string.statistik_grafik))
        viewpager.adapter = pagerAdapter
        tablayout.setupWithViewPager(viewpager)
    }

}