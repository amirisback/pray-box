package org.d3ifcool.jagosholat.views.fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.d3ifcool.jagosholat.views.dialogs.CatatanCustomDialog;
import org.d3ifcool.jagosholat.presenters.helpers.MethodHelper;
import org.d3ifcool.jagosholat.presenters.helpers.WaktuShalatHelper;
import org.d3ifcool.jagosholat.models.databases.DataOperation;
import org.d3ifcool.jagosholat.R;

import java.util.Random;

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

public class CatatanFragment extends Fragment{
    // ---------------------------------------------------------------------------------------------
    // Deklarasi Class helper Buatan Sendiri
    private MethodHelper methodHelper = new MethodHelper();
    private WaktuShalatHelper waktuShalatHelper = new WaktuShalatHelper();
    private DataOperation crud = new DataOperation();
    // ---------------------------------------------------------------------------------------------
    // Deklarasi Requirement Constants
    private int[] mHadistArab = {R.string.hadis_arab_0, R.string.hadis_arab_1, R.string.hadis_arab_2,
            R.string.hadis_arab_3, R.string.hadis_arab_4, R.string.hadis_arab_5};
    private int[] mHadistText = {R.string.hadis_text_0, R.string.hadis_text_1, R.string.hadis_text_2,
            R.string.hadis_text_3, R.string.hadis_text_4, R.string.hadis_text_5};
    // ---------------------------------------------------------------------------------------------

    public CatatanFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_catatan, container, false);
        // -----------------------------------------------------------------------------------------
        // Inisiasi element XML layout
        TextView mTextViewTanggal = rootView.findViewById(R.id.catatan_textview_tanggal);
        TextView mTextViewShalat = rootView.findViewById(R.id.jadwal_textview_shalat);
        TextView mTextViewHadistArab = rootView.findViewById(R.id.catatan_textview_hadist_arab);
        TextView mTextViewHadistText = rootView.findViewById(R.id.catatan_textview_hadist_text);
        Button mButtonCatat = rootView.findViewById(R.id.catatan_button_catat_ibadah);
        // -----------------------------------------------------------------------------------------
        // Set tampilan tanggal dan waktu
        methodHelper.getSystemTime();
        methodHelper.getSystemRealTime();
        methodHelper.getSumRealTime();
        waktuShalatHelper.setJadwalShalat(mTextViewShalat);
        mTextViewTanggal.setText(methodHelper.getDateToday());
        // -----------------------------------------------------------------------------------------
        // Set Data Random Hadist untuk XML Layout
        Random randomInt = new Random();
        int maxRandom = mHadistArab.length - 1;
        int minRandom = 0;
        int getIndexArrayHadis = randomInt.nextInt(maxRandom - minRandom + 1) + minRandom;
        // -----------------------------------------------------------------------------------------
        int mResIdHadistArab = mHadistArab[getIndexArrayHadis];
        int mResIdHadistText = mHadistText[getIndexArrayHadis];
        mTextViewHadistArab.setText(mResIdHadistArab);
        mTextViewHadistText.setText(mResIdHadistText);
        // -----------------------------------------------------------------------------------------
        // Deklarasi Element XML Update View
        AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(getContext());
        View mDialogView = inflater.inflate(R.layout.content_catatan_button, null);
        // -----------------------------------------------------------------------------------------
        // Deklarasi custom dialog
        final CatatanCustomDialog mDialogForm = new CatatanCustomDialog(mDialogBuilder, mDialogView,
                methodHelper, getContext(), crud, waktuShalatHelper);
        // -----------------------------------------------------------------------------------------
        // Fungsi untuk menampilkan button simpan
        String mShalatNow = mTextViewShalat.getText().toString();
        mDialogForm.viewSaveButton(mButtonCatat, mShalatNow);
        // -----------------------------------------------------------------------------------------
        // Panggil method untuk mencatat
        mButtonCatat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialogForm.DialogForm();
            }
        });
        // -----------------------------------------------------------------------------------------
        return rootView;
    }

}