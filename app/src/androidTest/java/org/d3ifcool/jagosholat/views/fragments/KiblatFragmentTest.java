package org.d3ifcool.jagosholat.views.fragments;

import androidx.test.rule.ActivityTestRule;

import org.d3ifcool.jagosholat.views.activities.MainActivity;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * 40-mobpro-4002-jago-sholat
 * Copyright (C) 28/11/2018.
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
public class KiblatFragmentTest {

    @Rule
    public ActivityTestRule<MainActivity> mMainActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void init(){
        mMainActivityTestRule.getActivity().getSupportFragmentManager().beginTransaction();
    }


    @Test
    public void Test(){

    }
}
