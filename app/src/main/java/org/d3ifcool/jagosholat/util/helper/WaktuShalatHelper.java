package org.d3ifcool.jagosholat.util.helper;

import android.os.CountDownTimer;
import android.widget.TextView;

import org.d3ifcool.jagosholat.util.lib.PrayerTime;
import org.d3ifcool.jagosholat.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.d3ifcool.jagosholat.util.helper.VarConstants.Constants;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * 40-mobpro-4002-jago-sholat
 * Copyright (C) 21/09/2018.
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
public class WaktuShalatHelper {

    // Deklarasi Method helper ---------------------------------------------------------------------
    private PrayerTime prayers = new PrayerTime();
    private Date now = new Date();
    private Calendar cal = Calendar.getInstance();
    // ---------------------------------------------------------------------------------------------

    // Deklarasi Requirement Kebutuhan -------------------------------------------------------------
    private int waktuSaatIni,
                jmlWaktuShubuh,
                jmlWaktuTerbit,
                jmlWaktuTerbenam,
                jmlWaktuDzuhur,
                jmlWaktuAshar,
                jmlWaktuMaghrib,
                jmlWaktuIsya,
                jmlBeMidnight,
                jmlAftMidnight;
    private String jamWaktuShubuh,
                jamWaktuDzuhur,
                jamWaktuAshar,
                jamWaktuMaghrib,
                jamWaktuIsya;
    // ---------------------------------------------------------------------------------------------

    // ---------------------------------------------------------------------------------------------
    private double timezone = (Calendar.getInstance().getTimeZone().getOffset(Calendar.getInstance().getTimeInMillis())) / (1000 * 60 * 60);
    // ---------------------------------------------------------------------------------------------

    // ---------------------------------------------------------------------------------------------
    private int offsets [] = { 0, 0, 0, 0, 0, 0, 0 }; // {Fajr,Sunrise,Dhuhr,Asr,Sunset,Maghrib,Isha}
    private ArrayList prayerTimes, prayerNames;
    // ---------------------------------------------------------------------------------------------

    public WaktuShalatHelper() {
        MethodHelper methodHelper = new MethodHelper();
        this.jmlBeMidnight = (23 * Constants.JAM_KE_DETIK) + (59 * Constants.MENIT_KE_DETIK); // 86.340
        this.jmlAftMidnight = 0; // 0
        setJmlWaktu();
        methodHelper.getSystemRealTime();
        this.waktuSaatIni = methodHelper.getSumWaktuDetik();
    }


    // ---------------------------------------------------------------------------------------------

    public boolean saatWaktunyaShubuh() {
        return (waktuSaatIni == jmlWaktuShubuh) || (waktuSaatIni < jmlWaktuTerbit);
    }

    public boolean saatWaktunyaDzuhur(){
        return (waktuSaatIni == jmlWaktuDzuhur) || (waktuSaatIni < jmlWaktuAshar);
    }

    public boolean saatWaktunyaAshar(){
        return (waktuSaatIni == jmlWaktuAshar) || (waktuSaatIni < jmlWaktuMaghrib);
    }

    public boolean saatWaktunyaMaghrib(){
        return (waktuSaatIni == jmlWaktuMaghrib) || (waktuSaatIni < jmlWaktuIsya);
    }

    public boolean saatWaktunyaIsyaPagi(){
        return (waktuSaatIni == jmlAftMidnight) || (waktuSaatIni < jmlWaktuShubuh);
    }

    public boolean saatWaktunyaIsyaMalam(){
        return (waktuSaatIni == jmlWaktuIsya) || (waktuSaatIni <= jmlBeMidnight);
    }

    public boolean saatWaktunyaBukan(){
        return (waktuSaatIni == jmlWaktuTerbit) || (waktuSaatIni < jmlWaktuDzuhur);
    }

    public void setJadwalShalat(TextView txt){
        if (saatWaktunyaIsyaPagi()) {
            txt.setText(Constants.ISYA); // isya
        } else if (saatWaktunyaShubuh()) {
            txt.setText(Constants.SHUBUH); // shubuh
        } else if (saatWaktunyaBukan()) {
            txt.setText(Constants.BUKAN_WAKTU_SHOLAT); // bukan waktu shalat
            txt.setTextSize(20);
        } else if (saatWaktunyaDzuhur()) {
            txt.setText(Constants.DZUHUR); // dzuhur
        } else if (saatWaktunyaAshar()) {
            txt.setText(Constants.ASHAR); // ashar
        } else if (saatWaktunyaMaghrib()) {
            txt.setText(Constants.MAGHRIB); // maghrib
        } else if (saatWaktunyaIsyaMalam()) {
            txt.setText(Constants.ISYA); // isya
        }  else {
            txt.setText(Constants.BUKAN_WAKTU_SHOLAT);
            txt.setTextSize(20);
        }
    }

    public String getJadwalShalat() {
        if (saatWaktunyaIsyaPagi()){
            return Constants.ISYA; // isya
        }else if (saatWaktunyaShubuh()) {
            return Constants.SHUBUH; // shubuh
        }else if (saatWaktunyaBukan()) {
            return Constants.BUKAN_WAKTU_SHOLAT; // bukan waktu shalat
        } else if (saatWaktunyaDzuhur()) {
            return Constants.DZUHUR; // dzuhur
        } else if (saatWaktunyaAshar()) {
            return Constants.ASHAR; // ashar
        } else if (saatWaktunyaMaghrib()) {
            return Constants.MAGHRIB; // maghrib
        } else if (saatWaktunyaIsyaMalam()) {
            return Constants.ISYA; // isya
        }  else {
            return Constants.BUKAN_WAKTU_SHOLAT; // bukan waktu shalat
        }
    }


    // ---------------------------------------------------------------------------------------------
    public void CoundownTime(int waktu, final TextView mTextView){
        new CountDownTimer(waktu, 1000) { // adjust the milli seconds here

            public void onTick(long millisUntilFinished) {
                mTextView.setText("- "+ String.format(Constants.FORMAT_COUNTDOWN,
                        TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
            }

            public void onFinish() {
                mTextView.setText(R.string.jadwal_helper_saatnya_shalat);
            }

        }.start();
    }
    // ---------------------------------------------------------------------------------------------


    private void MethodWaktuShalatHelper(){
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
        // -----------------------------------------------------------------------------------------
    }

    private void setJmlWaktu(){
        // -----------------------------------------------------------------------------------------
        MethodWaktuShalatHelper();
        // -----------------------------------------------------------------------------------------

        for (int i = 0; i < prayerTimes.size(); i++) {
            // -------------------------------------------------------------------------------------
            String tempWaktu = prayerTimes.get(i).toString();
            String hours = tempWaktu.substring(0, 2);
            String minutes = tempWaktu.substring(5, tempWaktu.length());
            // -------------------------------------------------------------------------------------
            int jam = Integer.parseInt(hours);
            int menit = Integer.parseInt(minutes);
            // -------------------------------------------------------------------------------------
            int total = (jam * Constants.JAM_KE_DETIK) + (menit * Constants.MENIT_KE_DETIK);
            // -------------------------------------------------------------------------------------
            if (prayerNames.get(i).equals(Constants.SHUBUH.substring(7))) {
                this.jmlWaktuShubuh = total;
            } else if (prayerNames.get(i).equals(Constants.DZUHUR.substring(7))){
                this.jmlWaktuDzuhur = total;
            } else if (prayerNames.get(i).equals(Constants.ASHAR.substring(7))) {
                this.jmlWaktuAshar = total;
            } else if (prayerNames.get(i).equals(Constants.MAGHRIB.substring(7))) {
                this.jmlWaktuMaghrib = total;
            } else if (prayerNames.get(i).equals(Constants.ISYA.substring(7))) {
                this.jmlWaktuIsya = total;
            }  else if (prayerNames.get(i).equals(Constants.MATAHARI_TERBIT)){
                this.jmlWaktuTerbit = total;
            } else if (prayerNames.get(i).equals(Constants.MATAHARI_TERBENAM)) {
                this.jmlWaktuTerbenam = total;
            }
            // -------------------------------------------------------------------------------------
        }
    }

    public void setTimeOnline(TextView mTextViewWaktuShubuh, TextView mTextViewWaktuDzuhur,
                              TextView mTextViewWaktuAshar, TextView mTextViewWaktuMaghrib,
                              TextView mTextViewWaktuIsya) {
        // -----------------------------------------------------------------------------------------
        MethodWaktuShalatHelper();
        // -----------------------------------------------------------------------------------------
        // Menset Waktu Sholat
        for (int i = 0; i < prayerTimes.size(); i++) {
            if (prayerNames.get(i).equals(Constants.SHUBUH.substring(7))) {
                mTextViewWaktuShubuh.setText(prayerTimes.get(i).toString());
                this.jamWaktuShubuh = prayerTimes.get(i).toString();

            } else if (prayerNames.get(i).equals(Constants.DZUHUR.substring(7))){
                mTextViewWaktuDzuhur.setText(prayerTimes.get(i).toString());
                this.jamWaktuDzuhur = prayerTimes.get(i).toString();

            } else if (prayerNames.get(i).equals(Constants.ASHAR.substring(7))) {
                mTextViewWaktuAshar.setText(prayerTimes.get(i).toString());
                this.jamWaktuAshar = prayerTimes.get(i).toString();

            } else if (prayerNames.get(i).equals(Constants.MAGHRIB.substring(7))) {
                mTextViewWaktuMaghrib.setText(prayerTimes.get(i).toString());
                this.jamWaktuMaghrib = prayerTimes.get(i).toString();

            } else if (prayerNames.get(i).equals(Constants.ISYA.substring(7))) {
                mTextViewWaktuIsya.setText(prayerTimes.get(i).toString());
                this.jamWaktuIsya = prayerTimes.get(i).toString();
            }
        }
        // -----------------------------------------------------------------------------------------

    }

    // ---------------------------------------------------------------------------------------------

    public int getJmlWaktuShubuh() {
        return jmlWaktuShubuh;
    }

    public int getJmlWaktuTerbit() {
        return jmlWaktuTerbit;
    }

    public int getJmlWaktuDzuhur() {
        return jmlWaktuDzuhur;
    }

    public int getJmlWaktuAshar() {
        return jmlWaktuAshar;
    }

    public int getJmlWaktuMaghrib() {
        return jmlWaktuMaghrib;
    }

    public int getJmlWaktuIsya() {
        return jmlWaktuIsya;
    }

    public int getJmlBeMidnight() {
        return jmlBeMidnight;
    }

    public int getJmlAftMidnight() {
        return jmlAftMidnight;
    }

    public int getJmlWaktuTerbenam() {
        return jmlWaktuTerbenam;
    }

    public String getJamWaktuShubuh() {
        return jamWaktuShubuh;
    }

    public String getJamWaktuDzuhur() {
        return jamWaktuDzuhur;
    }

    public String getJamWaktuAshar() {
        return jamWaktuAshar;
    }

    public String getJamWaktuMaghrib() {
        return jamWaktuMaghrib;
    }

    public String getJamWaktuIsya() {
        return jamWaktuIsya;
    }

}