package com.frogobox.praybox.mvvm.statistik;

import android.database.Cursor;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.frogobox.praybox.R;
import com.frogobox.praybox.source.DataContract;

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


 * Majors   : D3 Teknik Informatika 2016
 * Campus   : Telkom University
 * -----------------------------------------
 * id.amirisback.frogobox
 */
public class StatistikViewHolder extends RecyclerView.ViewHolder {

    private TextView stat_waktu, stat_shalat;
    private ImageView img;
    private String id, waktu;

    StatistikViewHolder(View itemView) {
        super(itemView);

        stat_waktu = itemView.findViewById(R.id.textview_harian_waktu);
        stat_shalat = itemView.findViewById(R.id.textview_harian_shalat);
        img = itemView.findViewById(R.id.imageview_harian_shalat);

    }

    public void setData(Cursor cursor) {
        // Mencari index dalam database
        int idColumnIndex = cursor.getColumnIndex(DataContract.DataEntry._ID);
        int waktuColumnIndex = cursor.getColumnIndex(DataContract.DataEntry.COLUMN_WAKTU);
        int shalatColumnIndex = cursor.getColumnIndex(DataContract.DataEntry.COLUMN_SHALAT);
        int statusColumnIndex = cursor.getColumnIndex(DataContract.DataEntry.COLUMN_STATUS);

        id = cursor.getString(idColumnIndex);
        waktu = cursor.getString(waktuColumnIndex);
        String shalat = cursor.getString(shalatColumnIndex);
        String status = cursor.getString(statusColumnIndex);

        int outImage;
        if (status.equalsIgnoreCase("shalat")) {
            outImage = R.drawable.ic_statistik_iya; // Shalat
        } else {
            outImage = R.drawable.ic_statistik_tidak; // Tidak Shalat
        }
        int resIdImage = outImage;

        stat_shalat.setText(shalat);
        stat_waktu.setText(waktu);
        img.setImageResource(resIdImage);

    }

    public String getId() {
        return id;
    }

    public String getWaktu() {
        return waktu;
    }

}