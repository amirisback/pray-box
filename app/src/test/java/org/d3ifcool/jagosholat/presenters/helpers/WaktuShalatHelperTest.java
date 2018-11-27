package org.d3ifcool.jagosholat.presenters.helpers;

import org.d3ifcool.jagosholat.models.constants.VarConstants.Constants;
import org.d3ifcool.jagosholat.presenters.libraries.PrayerTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

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
@RunWith(MockitoJUnitRunner.class)
public class WaktuShalatHelperTest {

    @Mock
    private PrayerTime prayers;
    private ArrayList prayerTimes, prayerNames;
    private int offsets[];
    private Date now;
    private Calendar cal;
    private double timezone;

    @Before
    public void setUp() throws Exception {
        prayers = new PrayerTime();
        offsets = new int[] { 0, 0, 0, 0, 0, 0, 0 }; // {Fajr,Sunrise,Dhuhr,Asr,Sunset,Maghrib,Isha}
        now = new Date();
        cal = Calendar.getInstance();
        timezone = (Calendar.getInstance().getTimeZone().getOffset(Calendar.getInstance().getTimeInMillis())) / (1000 * 60 * 60);
    }

    @Test
    public void MethodWaktuShalatHelper() {

        double latitude = -6.1744;
        double longitude = 106.8294;
        // -----------------------------------------------------------------------------------------
        prayers.setTimeFormat(prayers.Time24);
        prayers.setCalcMethod(prayers.MWL);
        prayers.setAsrJuristic(prayers.Shafii);
        prayers.setAdjustHighLats(prayers.MidNight);
        prayers.tune(offsets);
        // -----------------------------------------------------------------------------------------
        cal.setTime(now);
        prayerTimes = prayers.getPrayerTimes(cal, latitude, longitude, timezone);
        prayerNames = prayers.getTimeNames();

        assertEquals(prayers.getTimeNames().get(0), Constants.SHUBUH.substring(7));
        assertEquals(prayers.getTimeNames().get(2), Constants.DZUHUR.substring(7));
        assertEquals(prayers.getTimeNames().get(3), Constants.ASHAR.substring(7));
        assertEquals(prayers.getTimeNames().get(5), Constants.MAGHRIB.substring(7));
        assertEquals(prayers.getTimeNames().get(6), Constants.ISYA.substring(7));
    }

}