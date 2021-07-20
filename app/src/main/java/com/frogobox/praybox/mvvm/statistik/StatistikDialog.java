package com.frogobox.praybox.mvvm.statistik;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import com.frogobox.praybox.R;
import com.frogobox.praybox.source.local.DataOperation;
import com.frogobox.praybox.util.SingleFunc;

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
public class StatistikDialog {

    // ---------------------------------------------------------------------------------------------
    // Deklarasi XML Alert Dialog
    private AlertDialog.Builder mDialog;
    private View mDialogView;
    private SingleFunc.Controller mMethodHelper;
    private Context mContext;
    private DataOperation mDataOperation;
    // ---------------------------------------------------------------------------------------------

    // ---------------------------------------------------------------------------------------------
    // Constructor
    public StatistikDialog(AlertDialog.Builder mDialog, View mDialogView, SingleFunc.Controller mMethodHelper, Context mContext, DataOperation mDataOperation) {
        this.mDialog = mDialog;
        this.mDialogView = mDialogView;
        this.mMethodHelper = mMethodHelper;
        this.mContext = mContext;
        this.mDataOperation = mDataOperation;
    }
    // ---------------------------------------------------------------------------------------------
    
    // ---------------------------------------------------------------------------------------------
    // Pop Up update waktu
    public void DialogForm(final String mID, String mWaktu) {
        mDialog.setView(mDialogView);
        mDialog.setCancelable(true);
        final TextView mTextView = mDialogView.findViewById(R.id.textview_statistik_update);
        mTextView.setText(mWaktu);
        // -----------------------------------------------------------------------------------------

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


        mDialog.setPositiveButton("CATAT", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // ---------------------------------------------------------------------------------
                String tempWaktu = mTextView.getText().toString();
                // ---------------------------------------------------------------------------------
                boolean isUpdated = mDataOperation.updateDataTime(mContext, tempWaktu, mID);
                if (isUpdated) {
                    Toast.makeText(mContext, "Waktu Telah Diubah", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(mContext, "Data Not Updadted", Toast.LENGTH_LONG).show();
                }
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

}