package org.d3ifcool.jagosholat.Controller.StatistikFragmentContent.StatistikAdapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.d3ifcool.jagosholat.Model.DataContract;
import org.d3ifcool.jagosholat.R;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * 40-mobpro-4002-jago-sholat
 * Copyright (C) 18/09/2018.
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
public class StatistikSemuaCursorViewHolder extends StatistikSemuaCursorRecyclerViewAdapter<StatistikSemuaCursorViewHolder.ViewHolder> {

    private TextView stat_tanggal, stat_waktu, stat_shalat;

    public StatistikSemuaCursorViewHolder(Context context, Cursor c) {
        super(context, c);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView stat_tanggal, stat_waktu, stat_shalat;
        public ViewHolder(View view) {
            super(view);
            // -------------------------------------------------------------------------------------
            // Deklarasi Element XML
            stat_tanggal = view.findViewById(R.id.txt_stat_semua_tanggal);
            stat_waktu = view.findViewById(R.id.txt_stat_semua_waktu);
            stat_shalat = view.findViewById(R.id.txt_stat_semua_shalat);
            // -------------------------------------------------------------------------------------
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_statistik_semua, parent, false);
        ViewHolder vh = new ViewHolder(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Cursor cursor) {
        // -----------------------------------------------------------------------------------------
        // Mencari index dalam database
        int waktuColumnIndex = cursor.getColumnIndex(DataContract.DataEntry.COLUMN_WAKTU);
        int shalatColumnIndex = cursor.getColumnIndex(DataContract.DataEntry.COLUMN_SHALAT);
        int tanggalColumnIndex = cursor.getColumnIndex(DataContract.DataEntry.COLUMN_TANGGAL);
        // -----------------------------------------------------------------------------------------

        // -----------------------------------------------------------------------------------------
        // Mendapat data dari database berdasarkan index
        String waktu = cursor.getString(waktuColumnIndex);
        String shalat = cursor.getString(shalatColumnIndex);
        String tanggal = cursor.getString(tanggalColumnIndex);
        // -----------------------------------------------------------------------------------------

        // -----------------------------------------------------------------------------------------
        // Untuk Merubah Shalat Dzuhur menjadi -> DZUHUR
        String subStringShalat = shalat.substring(7, shalat.length());
        String upperCaseShalat = subStringShalat.toUpperCase();
        // -----------------------------------------------------------------------------------------

        // -----------------------------------------------------------------------------------------
        // Menset Text berdasarkan String yang sudah di dapat
        stat_shalat.setText(upperCaseShalat);
        stat_waktu.setText(waktu);
        stat_tanggal.setText(tanggal);
        // -----------------------------------------------------------------------------------------
    }

}
