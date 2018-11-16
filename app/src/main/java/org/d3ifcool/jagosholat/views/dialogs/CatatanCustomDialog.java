package org.d3ifcool.jagosholat.views.dialogs;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.TimePicker;

import org.d3ifcool.jagosholat.R;
import org.d3ifcool.jagosholat.models.databases.DataOperation;
import org.d3ifcool.jagosholat.presenters.helpers.WaktuShalatHelper;
import org.d3ifcool.jagosholat.presenters.helpers.MethodHelper;

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

    // ---------------------------------------------------------------------------------------------
    // Constructor
    public CatatanCustomDialog(AlertDialog.Builder mDialog, View mDialogView, MethodHelper mMethodHelper, Context mContext, DataOperation mDataOperation, WaktuShalatHelper mWaktuShalatHelper) {
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
        mDialog.setView(mDialogView);
        mDialog.setCancelable(true);
        // -----------------------------------------------------------------------------------------
        View mLayoutShubuh = mDialogView.findViewById(R.id.layout_catatan_button_jam_shubuh);
        View mLayoutDzuhur = mDialogView.findViewById(R.id.layout_catatan_button_jam_dzuhur);
        View mLayoutAshar = mDialogView.findViewById(R.id.layout_catatan_button_jam_ashar);
        View mLayoutMaghrib = mDialogView.findViewById(R.id.layout_catatan_button_jam_maghrib);
        View mLayoutIsya = mDialogView.findViewById(R.id.layout_catatan_button_jam_isya);
        // -----------------------------------------------------------------------------------------
        CheckBox mCheckBoxShubuh = mDialogView.findViewById(R.id.checkbox_catatan_button_jam_shubuh);
        CheckBox mCheckBoxDzuhur = mDialogView.findViewById(R.id.checkbox_catatan_button_jam_dzuhur);
        CheckBox mCheckBoxAshar = mDialogView.findViewById(R.id.checkbox_catatan_button_jam_ashar);
        CheckBox mCheckBoxMaghrib = mDialogView.findViewById(R.id.checkbox_catatan_button_jam_maghrib);
        CheckBox mCheckBoxIsya = mDialogView.findViewById(R.id.checkbox_catatan_button_jam_isya);
        // -----------------------------------------------------------------------------------------
        TextView mTextViewWaktuShubuh = mDialogView.findViewById(R.id.textview_catatan_button_jam_shubuh);
        TextView mTextViewWaktuDzuhur = mDialogView.findViewById(R.id.textview_catatan_button_jam_dzuhur);
        TextView mTextViewWaktuAshar = mDialogView.findViewById(R.id.textview_catatan_button_jam_ashar);
        TextView mTextViewWaktuMaghrib = mDialogView.findViewById(R.id.textview_catatan_button_jam_maghrib);
        TextView mTextViewWaktuIsya = mDialogView.findViewById(R.id.textview_catatan_button_jam_isya);
        // -----------------------------------------------------------------------------------------
        mWaktuShalatHelper.setTimeOnline(mTextViewWaktuShubuh, mTextViewWaktuDzuhur, mTextViewWaktuAshar, mTextViewWaktuMaghrib, mTextViewWaktuIsya);
        // -----------------------------------------------------------------------------------------
        setTimePicker(mTextViewWaktuShubuh);
        setTimePicker(mTextViewWaktuDzuhur);
        setTimePicker(mTextViewWaktuAshar);
        setTimePicker(mTextViewWaktuMaghrib);
        setTimePicker(mTextViewWaktuIsya);
        // -----------------------------------------------------------------------------------------

        mDialog.setPositiveButton("CATAT", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {


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


    private void setTimePicker(final TextView mTextView) {
        // -----------------------------------------------------------------------------------------
        // Set Waktu
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
        // -----------------------------------------------------------------------------------------
    }

}
