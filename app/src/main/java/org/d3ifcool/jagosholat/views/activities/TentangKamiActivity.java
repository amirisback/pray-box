package org.d3ifcool.jagosholat.views.activities;

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
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.d3ifcool.jagosholat.R;

public class TentangKamiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tentang_kami);

        // -----------------------------------------------------------------------------------------
        ImageView imageIcan = findViewById(R.id.imageican);
        ImageView imageBryan = findViewById(R.id.imagebrian);
        ImageView imageAmir = findViewById(R.id.imageamir);
        // -----------------------------------------------------------------------------------------
        Picasso.get().load(R.drawable.about_us_amir).into(imageAmir);
        Picasso.get().load(R.drawable.about_us_ican).into(imageIcan);
        Picasso.get().load(R.drawable.about_us_bryan).into(imageBryan);
        // -----------------------------------------------------------------------------------------

    }
}
