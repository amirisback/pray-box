package org.d3ifcool.jagosholat.views.dialogs;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.d3ifcool.jagosholat.R;
import org.d3ifcool.jagosholat.models.databases.DataOperation;
import org.d3ifcool.jagosholat.presenters.helpers.WaktuShalatHelper;
import org.d3ifcool.jagosholat.presenters.helpers.MethodHelper;

import static org.d3ifcool.jagosholat.models.constants.VarConstants.Constants;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * 40-mobpro-4002-jago-sholat
 * Copyright (C) 16/11/2018.
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
public class CatatanCustomDialog {

    // ---------------------------------------------------------------------------------------------
    // Deklarasi XML Alert Dialog
    private AlertDialog.Builder mDialog;
    private View mDialogView;
    private MethodHelper mMethodHelper;
    private Context mContext;
    private DataOperation mDataOperation;
    private WaktuShalatHelper mWaktuShalatHelper;
    // ---------------------------------------------------------------------------------------------
    // Constructor
    public CatatanCustomDialog(AlertDialog.Builder mDialog, View mDialogView, MethodHelper mMethodHelper, Context mContext,
                               DataOperation mDataOperation, WaktuShalatHelper mWaktuShalatHelper) {
        this.mDialog = mDialog;
        this.mDialogView = mDialogView;
        this.mMethodHelper = mMethodHelper;
        this.mContext = mContext;
        this.mDataOperation = mDataOperation;
        this.mWaktuShalatHelper = mWaktuShalatHelper;
    }
    // ---------------------------------------------------------------------------------------------

    // ---------------------------------------------------------------------------------------------
    // Pop Up update waktu
    public void DialogForm() {

        // -----------------------------------------------------------------------------------------
        mDialog.setView(mDialogView);
        mDialog.setCancelable(true);
        // -----------------------------------------------------------------------------------------
        View mLayoutShubuh = mDialogView.findViewById(R.id.layout_catatan_button_jam_shubuh);
        View mLayoutDzuhur = mDialogView.findViewById(R.id.layout_catatan_button_jam_dzuhur);
        View mLayoutAshar = mDialogView.findViewById(R.id.layout_catatan_button_jam_ashar);
        View mLayoutMaghrib = mDialogView.findViewById(R.id.layout_catatan_button_jam_maghrib);
        View mLayoutIsya = mDialogView.findViewById(R.id.layout_catatan_button_jam_isya);
        // -----------------------------------------------------------------------------------------
        final CheckBox mCheckBoxShubuh = mDialogView.findViewById(R.id.checkbox_catatan_button_jam_shubuh);
        final CheckBox mCheckBoxDzuhur = mDialogView.findViewById(R.id.checkbox_catatan_button_jam_dzuhur);
        final CheckBox mCheckBoxAshar = mDialogView.findViewById(R.id.checkbox_catatan_button_jam_ashar);
        final CheckBox mCheckBoxMaghrib = mDialogView.findViewById(R.id.checkbox_catatan_button_jam_maghrib);
        final CheckBox mCheckBoxIsya = mDialogView.findViewById(R.id.checkbox_catatan_button_jam_isya);
        // -----------------------------------------------------------------------------------------
        final TextView mTextViewWaktuShubuh = mDialogView.findViewById(R.id.textview_catatan_button_jam_shubuh);
        final TextView mTextViewWaktuDzuhur = mDialogView.findViewById(R.id.textview_catatan_button_jam_dzuhur);
        final TextView mTextViewWaktuAshar = mDialogView.findViewById(R.id.textview_catatan_button_jam_ashar);
        final TextView mTextViewWaktuMaghrib = mDialogView.findViewById(R.id.textview_catatan_button_jam_maghrib);
        final TextView mTextViewWaktuIsya = mDialogView.findViewById(R.id.textview_catatan_button_jam_isya);
        // -----------------------------------------------------------------------------------------
        mWaktuShalatHelper.setTimeOnline(mTextViewWaktuShubuh, mTextViewWaktuDzuhur, mTextViewWaktuAshar, mTextViewWaktuMaghrib, mTextViewWaktuIsya);
        // -----------------------------------------------------------------------------------------
        setTimePicker(mTextViewWaktuShubuh);
        setTimePicker(mTextViewWaktuDzuhur);
        setTimePicker(mTextViewWaktuAshar);
        setTimePicker(mTextViewWaktuMaghrib);
        setTimePicker(mTextViewWaktuIsya);
        // -----------------------------------------------------------------------------------------

        if (mWaktuShalatHelper.saatWaktunyaIsyaPagi()) {
            if (!isEmptyDataToday()) {
                if (isDataNotExistID(Constants.SHUBUH)) mLayoutShubuh.setVisibility(View.VISIBLE);
                if (isDataNotExistID(Constants.DZUHUR)) mLayoutDzuhur.setVisibility(View.VISIBLE);
                if (isDataNotExistID(Constants.ASHAR)) mLayoutAshar.setVisibility(View.VISIBLE);
                if (isDataNotExistID(Constants.MAGHRIB)) mLayoutMaghrib.setVisibility(View.VISIBLE);
                if (isDataNotExistID(Constants.ISYA)) mLayoutIsya.setVisibility(View.VISIBLE);
            } else {
                mLayoutShubuh.setVisibility(View.VISIBLE);
                mLayoutDzuhur.setVisibility(View.VISIBLE);
                mLayoutAshar.setVisibility(View.VISIBLE);
                mLayoutMaghrib.setVisibility(View.VISIBLE);
                mLayoutIsya.setVisibility(View.VISIBLE);
            }
        } else if (mWaktuShalatHelper.saatWaktunyaShubuh()) {
            if (!isEmptyDataToday()) {
                if (isDataNotExistID(Constants.SHUBUH)) mLayoutShubuh.setVisibility(View.VISIBLE);
            } else {
                mLayoutShubuh.setVisibility(View.VISIBLE);
            }
        } else if (mWaktuShalatHelper.saatWaktunyaDzuhur()){
            if (!isEmptyDataToday()){
                if (isDataNotExistID(Constants.SHUBUH)) mLayoutShubuh.setVisibility(View.VISIBLE);
                if (isDataNotExistID(Constants.DZUHUR)) mLayoutDzuhur.setVisibility(View.VISIBLE);
            } else {
                mLayoutShubuh.setVisibility(View.VISIBLE);
                mLayoutDzuhur.setVisibility(View.VISIBLE);
            }
        } else if (mWaktuShalatHelper.saatWaktunyaAshar()){
            if (!isEmptyDataToday()){
                if (isDataNotExistID(Constants.SHUBUH)) mLayoutShubuh.setVisibility(View.VISIBLE);
                if (isDataNotExistID(Constants.DZUHUR)) mLayoutDzuhur.setVisibility(View.VISIBLE);
                if (isDataNotExistID(Constants.ASHAR)) mLayoutAshar.setVisibility(View.VISIBLE);
            } else {
                mLayoutShubuh.setVisibility(View.VISIBLE);
                mLayoutDzuhur.setVisibility(View.VISIBLE);
                mLayoutAshar.setVisibility(View.VISIBLE);
            }

        } else if (mWaktuShalatHelper.saatWaktunyaMaghrib()){
            if (!isEmptyDataToday()){
                if (isDataNotExistID(Constants.SHUBUH)) mLayoutShubuh.setVisibility(View.VISIBLE);
                if (isDataNotExistID(Constants.DZUHUR)) mLayoutDzuhur.setVisibility(View.VISIBLE);
                if (isDataNotExistID(Constants.ASHAR)) mLayoutAshar.setVisibility(View.VISIBLE);
                if (isDataNotExistID(Constants.MAGHRIB)) mLayoutMaghrib.setVisibility(View.VISIBLE);
            } else {
                mLayoutShubuh.setVisibility(View.VISIBLE);
                mLayoutDzuhur.setVisibility(View.VISIBLE);
                mLayoutAshar.setVisibility(View.VISIBLE);
                mLayoutMaghrib.setVisibility(View.VISIBLE);
            }

        } else if (mWaktuShalatHelper.saatWaktunyaIsyaMalam()){
            if (!isEmptyDataToday()){
                if (isDataNotExistID(Constants.SHUBUH)) mLayoutShubuh.setVisibility(View.VISIBLE);
                if (isDataNotExistID(Constants.DZUHUR)) mLayoutDzuhur.setVisibility(View.VISIBLE);
                if (isDataNotExistID(Constants.ASHAR)) mLayoutAshar.setVisibility(View.VISIBLE);
                if (isDataNotExistID(Constants.MAGHRIB)) mLayoutMaghrib.setVisibility(View.VISIBLE);
                if (isDataNotExistID(Constants.ISYA)) mLayoutIsya.setVisibility(View.VISIBLE);
            } else {
                mLayoutShubuh.setVisibility(View.VISIBLE);
                mLayoutDzuhur.setVisibility(View.VISIBLE);
                mLayoutAshar.setVisibility(View.VISIBLE);
                mLayoutMaghrib.setVisibility(View.VISIBLE);
                mLayoutIsya.setVisibility(View.VISIBLE);
            }
        }

        mDialog.setPositiveButton("CATAT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // ---------------------------------------------------------------------------------

                if (mCheckBoxShubuh.isChecked()){
                    String tempIDShubuh = "IDS11" + mMethodHelper.getRandomChar();
                    String tempWaktuShubuh = mTextViewWaktuShubuh.getText().toString();
                    addData(tempIDShubuh, Constants.SHUBUH, tempWaktuShubuh);
                }

                if (mCheckBoxDzuhur.isChecked()){
                    String tempIDDzuhur = "IDS22" + mMethodHelper.getRandomChar();
                    String tempWaktuDzuhur = mTextViewWaktuDzuhur.getText().toString();
                    addData(tempIDDzuhur, Constants.DZUHUR, tempWaktuDzuhur);
                }

                if (mCheckBoxAshar.isChecked()){
                    String tempIDAshar = "IDS33" + mMethodHelper.getRandomChar();
                    String tempWaktuAshar = mTextViewWaktuAshar.getText().toString();
                    addData(tempIDAshar, Constants.ASHAR, tempWaktuAshar);
                }

                if (mCheckBoxMaghrib.isChecked()){
                    String tempIDMaghrib = "IDS44" + mMethodHelper.getRandomChar();
                    String tempWaktuMaghrib = mTextViewWaktuMaghrib.getText().toString();
                    addData(tempIDMaghrib, Constants.MAGHRIB, tempWaktuMaghrib);
                }

                if (mCheckBoxIsya.isChecked()){
                    String tempIDIsya = "IDS55" + mMethodHelper.getRandomChar();
                    String tempWaktuIsya = mTextViewWaktuIsya.getText().toString();
                    addData(tempIDIsya, Constants.ISYA, tempWaktuIsya);
                }

                Toast.makeText(mContext, "Alhamdulillah Shalat", Toast.LENGTH_LONG).show();
                // ---------------------------------------------------------------------------------
                dialog.dismiss(); // Keluar Dari Dialog
                if (mDialogView.getParent() != null) {
                    ((ViewGroup) mDialogView.getParent()).removeView(mDialogView);
                }
            }
        });

        mDialog.setNegativeButton("BATAL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); // Keluar Dari Dialog
                if (mDialogView.getParent() != null) {
                    ((ViewGroup) mDialogView.getParent()).removeView(mDialogView);
                }
            }
        });

        mDialog.show();
    }
    // ---------------------------------------------------------------------------------------------


    // Cek di dalam table belum ada data sama sekali -----------------------------------------------
    private boolean isEmptyDataToday() {
        Cursor res = mDataOperation.getDataToday(mContext, mMethodHelper.getDateToday());
        int cek = res.getCount();
        return cek == 0;
    }
    // ---------------------------------------------------------------------------------------------

    // Cek di dalam table belum ada data sama sekali -----------------------------------------------
    private boolean isDataNotExistID(String mShalat) {
        Cursor res = null;
        try {
            res = mDataOperation.getDataDateShalat(mContext, mMethodHelper.getDateToday(), mShalat);
            int cek = res.getCount();
            return cek == 0;
        } finally {
            res.close();
        }
    }
    // ---------------------------------------------------------------------------------------------

    // Method langsung isi dalam database ----------------------------------------------------------
    private void insertDataToDatabase(String id_ibadah, String isi_shalat, String isi_waktu) {
        String isi_tanggal = mMethodHelper.getDateToday();
        String isi_status = "Shalat";
        mDataOperation.insertData(mContext, id_ibadah, isi_tanggal, isi_shalat, isi_waktu, isi_status);
    }
    // ---------------------------------------------------------------------------------------------

    // Method untuk menyimpan data ketika button "Simpan" di tekan ---------------------------------
    private void addData(String mId, String mShalat, String mWaktu) {
        try {
            if (isDataNotExistID(mShalat)) {
                if (!mShalat.equals(Constants.BUKAN_WAKTU_SHOLAT)) {
                    insertDataToDatabase(mId, mShalat, mWaktu);
                }
            } else {
                if (!isDataNotExistID(mShalat)) {
                    insertDataToDatabase(mId, mShalat, mWaktu);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // ---------------------------------------------------------------------------------------------

    // Set Waktu -----------------------------------------------------------------------------------
    private void setTimePicker(final TextView mTextView) {

        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog mTimePickerDialog = new TimePickerDialog(mContext, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        mMethodHelper.getFormatTimePicker(mTextView, hourOfDay, minute);
                    }
                }, mMethodHelper.getSystemJam(), mMethodHelper.getSystemMenit(), true);
                mTimePickerDialog.show();

            }
        });
    }
    // ---------------------------------------------------------------------------------------------

    // Mengatur Tombol -----------------------------------------------------------------------------
    public void viewSaveButton(Button mButton, String mShalat){
        if (mShalat.equalsIgnoreCase(Constants.BUKAN_WAKTU_SHOLAT)){
            mButton.setVisibility(View.GONE);
        } else {
            if (isDataNotExistID(mShalat)){
                mButton.setVisibility(View.VISIBLE);
            } else {
                mButton.setVisibility(View.GONE);
            }
        }
    }
    // ---------------------------------------------------------------------------------------------

}