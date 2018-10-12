package org.d3ifcool.jagosholat.Controller.Helper;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.d3ifcool.jagosholat.Model.DataContract;
import org.d3ifcool.jagosholat.Model.DataOperation;

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
public class DialogFormHelper {

    // ---------------------------------------------------------------------------------------------
    // Deklarasi XML Alert Dialog
    private AlertDialog.Builder mDialog;
    private LayoutInflater mInflater;
    private View mDialogView;
    private TextView mTextView;
    private MethodHelper fh;
    private Context context;
    private DataOperation mDataOperation;
    // ---------------------------------------------------------------------------------------------

    // ---------------------------------------------------------------------------------------------
    // Constructor

    public DialogFormHelper(AlertDialog.Builder mDialog, LayoutInflater mInflater, View mDialogView, TextView mTextView, MethodHelper fh, Context context, DataOperation mDataOperation) {
        this.mDialog = mDialog;
        this.mInflater = mInflater;
        this.mDialogView = mDialogView;
        this.mTextView = mTextView;
        this.fh = fh;
        this.context = context;
        this.mDataOperation = mDataOperation;
    }

    // ---------------------------------------------------------------------------------------------

    
    // ---------------------------------------------------------------------------------------------
    // Pop Up update waktu
    public void DialogForm(final String mID, String mWaktu) {
        mDialog.setView(mDialogView);
        mDialog.setCancelable(true);
        mTextView.setText(mWaktu);
        // -----------------------------------------------------------------------------------------

        // -----------------------------------------------------------------------------------------
        // Set Waktu
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TimePickerDialog mTimePickerDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        fh.getFormatTimePicker(mTextView, hourOfDay, minute);
                    }
                }, fh.getSystemJam(), fh.getSystemMenit(), true);



                mTimePickerDialog.show();

            }
        });
        // -----------------------------------------------------------------------------------------


        mDialog.setPositiveButton("CATAT", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                // ---------------------------------------------------------------------------------
                String tempWaktu = mTextView.getText().toString();
                String selection = DataContract.DataEntry._ID + " = '" + mID + "'";
                // ---------------------------------------------------------------------------------

                // ---------------------------------------------------------------------------------
                boolean isUpdated = mDataOperation.updateDataWaktu(context, tempWaktu, selection, null);
                if (isUpdated) {
                    Toast.makeText(context, "Waktu Telah Diubah", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, "Data Not Updadted", Toast.LENGTH_LONG).show();
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