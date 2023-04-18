package com.frogobox.praybox.mvvm.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.frogobox.praybox.R
import com.frogobox.praybox.core.BaseActivity
import com.frogobox.praybox.databinding.ActivityMainBinding
import com.frogobox.praybox.mvvm.statistik.StatistikFragment
import com.frogobox.sdk.ext.startActivityExt

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * JagoSholat
 * Copyright (C) 25/04/2018.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * -----------------------------------------
 * id.amirisback.frogobox
 */

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun setupViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun setupViewModel() {
    }

    override fun onCreateExt(savedInstanceState: Bundle?) {
        super.onCreateExt(savedInstanceState)
        setupToolbar()
        setupBottomNav(binding.framelayoutMainContainer.id)
        setupFragment(savedInstanceState)
    }

    private fun setupFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            binding.bottomNavMainMenu.selectedItemId = R.id.bottom_menu_catatan
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar.toolbarMain)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.toolbar_menu_about -> {
                startActivityExt<AboutUsActivity>()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupBottomNav(frameLayout: Int) {
        binding.bottomNavMainMenu.apply {
            clearAnimation()
            itemIconTintList = null
            setOnNavigationItemSelectedListener {

                when (it.itemId) {
                    R.id.bottom_menu_catatan -> {
                        setupCustomTitleToolbar(R.string.menu_catatan)
                        setupChildFragment(frameLayout, CatatanFragment())
                    }

                    R.id.bottom_menu_statistik -> {
                        setupCustomTitleToolbar(R.string.menu_statistik)
                        setupChildFragment(frameLayout, StatistikFragment())
                    }

                    R.id.bottom_menu_jadwal -> {
                        setupCustomTitleToolbar(R.string.menu_jadwal)
                        setupChildFragment(frameLayout, JadwalFragment())
                    }

                    R.id.bottom_menu_lainnya -> {
                        setupCustomTitleToolbar(R.string.menu_tatacara)
                        setupChildFragment(frameLayout, LainnyaFragment())
                    }

                }

                true
            }

        }
    }
}