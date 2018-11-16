package org.d3ifcool.jagosholat.views.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.d3ifcool.jagosholat.views.interfaces.InterfaceCatatanFragment;
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

public class CatatanFragment extends Fragment implements InterfaceCatatanFragment {

    // ---------------------------------------------------------------------------------------------
    // Deklarasi Class helper Buatan Sendiri
    private MethodHelper methodHelper = new MethodHelper();
    private WaktuShalatHelper waktuShalatHelper = new WaktuShalatHelper();
    private DataOperation crud = new DataOperation();
    // ---------------------------------------------------------------------------------------------
    // Deklarasi Requirement Variable
    private String cekid;
    private final String bukanWaktuSholat = "Belum Masuk Waktu Sholat";
    private int[] mHadistArab = {R.string.hadis_arab_0, R.string.hadis_arab_1, R.string.hadis_arab_2,
            R.string.hadis_arab_3, R.string.hadis_arab_4, R.string.hadis_arab_5};
    private int[] mHadistText = {R.string.hadis_text_0, R.string.hadis_text_1, R.string.hadis_text_2,
            R.string.hadis_text_3, R.string.hadis_text_4, R.string.hadis_text_5};
    // ---------------------------------------------------------------------------------------------
    // Deklarasi element layout
    private Button btn_simpan;
    private String isi_tanggal, isi_sholat, isi_waktu, isi_status;
    private String id_ibadah;
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
        TextView txt_tanggal = rootView.findViewById(R.id.catatan_textview_tanggal);
        TextView txt_sholat = rootView.findViewById(R.id.jadwal_textview_shalat);
        TextView txt_hadist_arab = rootView.findViewById(R.id.catatan_textview_hadist_arab);
        TextView txt_hadist_text = rootView.findViewById(R.id.catatan_textview_hadist_text);
        btn_simpan = rootView.findViewById(R.id.catatan_button_catat_ibadah);
        // -----------------------------------------------------------------------------------------
        // Set tampilan tanggal dan waktu
        methodHelper.getSystemTime();
        methodHelper.getSystemRealTime();
        methodHelper.getSumRealTime();
        waktuShalatHelper.setJadwalShalat(txt_sholat);
        txt_tanggal.setText(methodHelper.getDateToday());
        // -----------------------------------------------------------------------------------------
        // Get Data dari XML Layout
        isi_sholat = txt_sholat.getText().toString();
        isi_waktu = methodHelper.getOutputStringTime();
        isi_tanggal = methodHelper.getDateToday();
        isi_status = "Shalat";
        // -----------------------------------------------------------------------------------------
        // Set Data Random Hadist untuk XML Layout
        Random randomInt = new Random();
        int maxRandom = mHadistArab.length - 1;
        int minRandom = 0;
        int getIndexArrayHadis = randomInt.nextInt(maxRandom - minRandom + 1) + minRandom;
        // -----------------------------------------------------------------------------------------
        int mResIdHadistArab = mHadistArab[getIndexArrayHadis];
        int mResIdHadistText = mHadistText[getIndexArrayHadis];
        txt_hadist_arab.setText(mResIdHadistArab);
        txt_hadist_text.setText(mResIdHadistText);
        // -----------------------------------------------------------------------------------------
        // Panggil method untuk mencatat
        id_ibadah = "IDS" + methodHelper.getRandomChar();
        tampilanButtonSimpan(isi_sholat);
        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addData(isi_sholat);
            }
        });
        // -----------------------------------------------------------------------------------------
        return rootView;
    }

    // Cek di dalam table belum ada data sama sekali -----------------------------------------------
    public boolean isEmptyRowTable() {
        Cursor res = null;
        try {
            res = crud.getDataTanggalJenis(getContext(), isi_tanggal, isi_sholat);
            int cek = res.getCount();
            return cek == 0;
        } finally {
            res.close();
        }
    }
    // ---------------------------------------------------------------------------------------------

    // Method untuk mengecek apakah data sudah terisi / dengan cara search di database -------------
    public String cekDataSudahAda() {
        Cursor res = crud.getDataTanggalJenis(getContext(), isi_tanggal, isi_sholat);
        try{
            while (res.moveToNext()) {
                cekid = res.getString(2);
            }
        } finally {
            res.close();
        }
        return cekid;
    }
    // ---------------------------------------------------------------------------------------------

    // Method langsung isi dalam database ----------------------------------------------------------
    public void insertDataToDatabase() {
        boolean isInserted = crud.insertData(getContext(), id_ibadah, isi_tanggal, isi_sholat, isi_waktu, isi_status);
        if (isInserted) {
            Toast.makeText(getActivity(), "Alhamdullilah " + isi_sholat, Toast.LENGTH_LONG).show();
            btn_simpan.setVisibility(View.GONE);
        } else {
            Toast.makeText(getActivity(), "Data Not Inserted", Toast.LENGTH_LONG).show();
        }
    }
    // ---------------------------------------------------------------------------------------------

    // Method untuk menyimpan data ketika button "Simpan" di tekan ---------------------------------
    public void addData(String mShalat) {
        try {
            if (isEmptyRowTable()) {
                if (mShalat.equals(bukanWaktuSholat)) {
                    Toast.makeText(getActivity(), bukanWaktuSholat, Toast.LENGTH_LONG).show();
                } else {
                    insertDataToDatabase();
                }
            } else {
                if (cekDataSudahAda().equals(mShalat)) {
                    Toast.makeText(getActivity(), "Data Sudah Tercatat", Toast.LENGTH_LONG).show();
                } else if (mShalat.equals(bukanWaktuSholat)) {
                    Toast.makeText(getActivity(), bukanWaktuSholat, Toast.LENGTH_LONG).show();
                } else {
                    insertDataToDatabase();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // ---------------------------------------------------------------------------------------------

    // ---------------------------------------------------------------------------------------------
    // Mengatur Tombol
    public void tampilanButtonSimpan(String mShalat){
        if (mShalat.equalsIgnoreCase(bukanWaktuSholat)){
            btn_simpan.setVisibility(View.GONE);
        } else {
            if (isEmptyRowTable()){
                btn_simpan.setVisibility(View.VISIBLE);
            } else {
                if (!cekDataSudahAda().equals(mShalat)){
                    btn_simpan.setVisibility(View.VISIBLE);
                } else {
                    btn_simpan.setVisibility(View.GONE);
                }
            }
        }
    }
    // ---------------------------------------------------------------------------------------------
}