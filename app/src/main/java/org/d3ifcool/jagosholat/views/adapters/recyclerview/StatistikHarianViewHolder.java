package org.d3ifcool.jagosholat.views.adapters.recyclerview;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.d3ifcool.jagosholat.R;
import org.d3ifcool.jagosholat.models.databases.DataContract;
import org.d3ifcool.jagosholat.views.dialogs.StatistikCustomDialog;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * 40-mobpro-4002-jago-sholat
 * Copyright (C) 29/10/2018.
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
public class StatistikHarianViewHolder extends RecyclerView.ViewHolder {

    public TextView stat_waktu, stat_shalat;
    public ImageView img;

    public StatistikHarianViewHolder(View itemView) {
        super(itemView);
        stat_waktu = (TextView)itemView.findViewById(R.id.textview_harian_waktu);
        stat_shalat = (TextView)itemView.findViewById(R.id.textview_harian_shalat);
        img = (ImageView)itemView.findViewById(R.id.imageview_harian_shalat);

    }

    public void setData(Cursor cursor,final StatistikCustomDialog mDialogForm) {
        // Mencari index dalam database
        int idColumnIndex = cursor.getColumnIndex(DataContract.DataEntry._ID);
        int waktuColumnIndex = cursor.getColumnIndex(DataContract.DataEntry.COLUMN_WAKTU);
        int shalatColumnIndex = cursor.getColumnIndex(DataContract.DataEntry.COLUMN_SHALAT);
        int statusColumnIndex = cursor.getColumnIndex(DataContract.DataEntry.COLUMN_STATUS);
        // -----------------------------------------------------------------------------------------
        // Mendapat data dari database berdasarkan index
        final String id = cursor.getString(idColumnIndex);
        final String waktu = cursor.getString(waktuColumnIndex);
        String shalat = cursor.getString(shalatColumnIndex);
        String status = cursor.getString(statusColumnIndex);
        // -----------------------------------------------------------------------------------------
        // Mengecek gambar checklist
        int outImage;
        if (status.equalsIgnoreCase("shalat")){
            outImage = R.drawable.ic_statistik_iya; // Shalat
        } else {
            outImage = R.drawable.ic_statistik_tidak; // Tidak Shalat
        }
        int resIdImage = outImage;
        // -----------------------------------------------------------------------------------------
        // Menset Text berdasarkan String yang sudah di dapat
        stat_shalat.setText(shalat);
        stat_waktu.setText(waktu);
        img.setImageResource(resIdImage);
        // -----------------------------------------------------------------------------------------

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialogForm.DialogForm(id, waktu);
            }
        });

    }
}