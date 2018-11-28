package org.d3ifcool.jagosholat.views.fragments;

import android.support.test.rule.ActivityTestRule;
import android.support.v4.view.ViewPager;
import android.view.View;

import org.d3ifcool.jagosholat.R;
import org.d3ifcool.jagosholat.views.activities.MainActivity;
import org.hamcrest.Description;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.regex.Matcher;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

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
public class StatistikFragmentTest {

    @Rule
    public ActivityTestRule<MainActivity> mMainActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void init(){
        mMainActivityTestRule.getActivity().getSupportFragmentManager().beginTransaction();
    }


    @Test
    public void Test(){
        onView(withId(R.id.main_viewpager)).perform(swipeLeft()); // menuju ke Jadwal Fragment
        onView(withId(R.id.main_viewpager)).perform(swipeLeft()); // menuju ke Statistik Fragment
    }
}
