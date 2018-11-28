package org.d3ifcool.jagosholat.views.activities;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;

import org.d3ifcool.jagosholat.R;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static java.util.EnumSet.allOf;

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
@RunWith(AndroidJUnit4.class)

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mMainActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void init(){
        mMainActivityTestRule.getActivity().getSupportFragmentManager().beginTransaction();
    }

    @Test
    public void SwipeViewPager() {

        onView(withId(R.id.main_viewpager)).perform(swipeLeft()); // menuju ke Jadwal Fragment
        onView(withId(R.id.main_viewpager)).perform(swipeLeft()); // menuju ke Statistik Fragment
        onView(withId(R.id.main_viewpager)).perform(swipeLeft()); // menuju ke Kiblat Fragment
        onView(withId(R.id.main_viewpager)).perform(swipeLeft()); // menuju ke Tatacara Fragment

//        onView(withId(R.id.tatacara_sliderimage_viewpager)).perform(swipeLeft());
//        onView(withId(R.id.tatacara_sliderimage_viewpager)).perform(swipeLeft());
//        onView(withId(R.id.tatacara_sliderimage_viewpager)).perform(swipeLeft());
//        onView(withId(R.id.tatacara_sliderimage_viewpager)).perform(swipeLeft());
//        onView(withId(R.id.tatacara_sliderimage_viewpager)).perform(swipeLeft());
//        onView(withId(R.id.tatacara_sliderimage_viewpager)).perform(swipeLeft());
//        onView(withId(R.id.tatacara_sliderimage_viewpager)).perform(swipeLeft());
//        onView(withId(R.id.tatacara_sliderimage_viewpager)).perform(swipeLeft());
//        onView(withId(R.id.tatacara_sliderimage_viewpager)).perform(swipeLeft());
//
//        onView(withId(R.id.tatacara_viewpager)).perform(swipeLeft()); // menuju ke sub Tatacara Fragment - Niat Shalat Fragment
//        onView(withId(R.id.tatacara_listview_text)).perform(swipeUp());
//        onView(withId(R.id.tatacara_listview_text)).perform(swipeUp());
//        onView(withId(R.id.tatacara_listview_text)).perform(swipeUp());
//        onView(withId(R.id.tatacara_listview_text)).perform(swipeDown());
//        onView(withId(R.id.tatacara_listview_text)).perform(swipeDown());
//        onView(withId(R.id.tatacara_listview_text)).perform(swipeDown());
//
//        onView(withId(R.id.tatacara_viewpager)).perform(swipeLeft()); // menuju ke sub Tatacara Fragment - Tatacara Wudhu Shalat Fragment
//        onView(withId(R.id.tatacara_sliderimage_viewpager)).perform(swipeLeft());
//        onView(withId(R.id.tatacara_sliderimage_viewpager)).perform(swipeLeft());
//        onView(withId(R.id.tatacara_sliderimage_viewpager)).perform(swipeLeft());
//        onView(withId(R.id.tatacara_sliderimage_viewpager)).perform(swipeLeft());
//        onView(withId(R.id.tatacara_sliderimage_viewpager)).perform(swipeLeft());
//        onView(withId(R.id.tatacara_sliderimage_viewpager)).perform(swipeLeft());
//        onView(withId(R.id.tatacara_sliderimage_viewpager)).perform(swipeLeft());
//        onView(withId(R.id.tatacara_sliderimage_viewpager)).perform(swipeLeft());
//        onView(withId(R.id.tatacara_sliderimage_viewpager)).perform(swipeLeft());
//
//        onView(withId(R.id.tatacara_viewpager)).perform(swipeLeft()); // menuju ke sub Tatacara Fragment - Doa Setelah Shalat Fragment
//        onView(withId(R.id.tatacara_listview_text)).perform(swipeUp());
//        onView(withId(R.id.tatacara_listview_text)).perform(swipeUp());
//        onView(withId(R.id.tatacara_listview_text)).perform(swipeUp());
//        onView(withId(R.id.tatacara_listview_text)).perform(swipeDown());
//        onView(withId(R.id.tatacara_listview_text)).perform(swipeDown());
//        onView(withId(R.id.tatacara_listview_text)).perform(swipeDown());

        onView(withId(R.id.main_viewpager)).perform(swipeRight());
        onView(withId(R.id.main_viewpager)).perform(swipeRight());
        onView(withId(R.id.main_viewpager)).perform(swipeRight());
        onView(withId(R.id.main_viewpager)).perform(swipeRight());

    }


}
