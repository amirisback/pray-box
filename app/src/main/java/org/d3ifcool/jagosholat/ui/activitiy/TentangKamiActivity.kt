package org.d3ifcool.jagosholat.ui.activitiy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_tentang_kami.*
import org.d3ifcool.jagosholat.R

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * 40-mobpro-4002-jago-sholat
 * Copyright (C) 05/09/2018.
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
class TentangKamiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tentang_kami)
        // -----------------------------------------------------------------------------------------
        Picasso.get().load(R.drawable.about_us_amir).into(imageican)
        Picasso.get().load(R.drawable.about_us_ican).into(imageican)
        Picasso.get().load(R.drawable.about_us_bryan).into(imagebrian)
        // -----------------------------------------------------------------------------------------
    }
}