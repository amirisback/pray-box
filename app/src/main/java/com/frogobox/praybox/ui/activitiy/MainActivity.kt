package com.frogobox.praybox.ui.activitiy

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.frogobox.praybox.R
import com.frogobox.praybox.base.view.ui.BaseActivity
import com.frogobox.praybox.ui.fragment.CatatanFragment
import com.frogobox.praybox.ui.fragment.JadwalFragment
import com.frogobox.praybox.ui.fragment.StatistikFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_main.*

/*
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * JagoSholat
 * Copyright (C) 25/04/2018.
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

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupToolbar()
        setupBottomNav(R.id.framelayout_main_container)
        setupFragment(savedInstanceState)
    }

    private fun setupFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            bottom_nav_main_menu.selectedItemId = R.id.bottom_menu_catatan
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.toolbar_menu_about -> {
                baseStartActivity<TentangKamiActivity>()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupBottomNav(frameLayout: Int) {
        bottom_nav_main_menu.clearAnimation()
        bottom_nav_main_menu.itemIconTintList = null
        bottom_nav_main_menu.setOnNavigationItemSelectedListener {

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

            }

            true
        }

    }
}