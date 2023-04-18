package com.frogobox.praybox.mvvm.statistik

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.frogobox.praybox.R
import com.frogobox.praybox.core.BaseFragment
import com.frogobox.praybox.databinding.FragmentStatistikBinding
import com.frogobox.praybox.util.PagerHelper

/**
 * A simple [Fragment] subclass.
 */
class StatistikFragment : BaseFragment<FragmentStatistikBinding>() {

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentStatistikBinding {
        return FragmentStatistikBinding.inflate(inflater, container, false)
    }

    override fun setupViewModel() {
    }

    override fun onViewCreatedExt(view: View, savedInstanceState: Bundle?) {
        super.onViewCreatedExt(view, savedInstanceState)
        binding.apply {
            val pagerAdapter = PagerHelper(childFragmentManager)
            pagerAdapter.setupPagerFragment(StatistikHarianFragment(), resources.getString(R.string.statistik_harian))
            pagerAdapter.setupPagerFragment(StatistikGrafikFragment(), resources.getString(R.string.statistik_grafik))
            viewpager.adapter = pagerAdapter
            tablayout.setupWithViewPager(viewpager)
        }
    }

}