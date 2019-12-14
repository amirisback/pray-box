package org.d3ifcool.jagosholat.view.adapter;

import android.database.Cursor;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.d3ifcool.jagosholat.R;
import org.d3ifcool.jagosholat.source.local.DataContract;
import org.d3ifcool.jagosholat.callback.ClickHandlerActionMode;

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
public class StatistikHarianViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

    private TextView stat_waktu, stat_shalat;
    private ImageView img;
    private ClickHandlerActionMode mClickHandler;
    private String id, waktu;

    public StatistikHarianViewHolder(View itemView, ClickHandlerActionMode handler) {
        super(itemView);
        // -----------------------------------------------------------------------------------------
        this.mClickHandler = handler;
        // -----------------------------------------------------------------------------------------
        stat_waktu = (TextView)itemView.findViewById(R.id.textview_harian_waktu);
        stat_shalat = (TextView)itemView.findViewById(R.id.textview_harian_shalat);
        img = (ImageView)itemView.findViewById(R.id.imageview_harian_shalat);
        // -----------------------------------------------------------------------------------------
        itemView.setFocusable(true);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
        // -----------------------------------------------------------------------------------------
    }

    public void setData(Cursor cursor) {
        // Mencari index dalam database
        int idColumnIndex = cursor.getColumnIndex(DataContract.DataEntry._ID);
        int waktuColumnIndex = cursor.getColumnIndex(DataContract.DataEntry.COLUMN_WAKTU);
        int shalatColumnIndex = cursor.getColumnIndex(DataContract.DataEntry.COLUMN_SHALAT);
        int statusColumnIndex = cursor.getColumnIndex(DataContract.DataEntry.COLUMN_STATUS);
        // -----------------------------------------------------------------------------------------
        // Mendapat data dari database berdasarkan index
        id = cursor.getString(idColumnIndex);
        waktu = cursor.getString(waktuColumnIndex);
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
    }

    // ---------------------------------------------------------------------------------------------
    public String getId() {
        return id;
    }

    public String getWaktu() {
        return waktu;
    }

    // ---------------------------------------------------------------------------------------------
    @Override
    public boolean onLongClick(View view) {
        return mClickHandler.onItemLongClick(getAdapterPosition());
    }
    // ---------------------------------------------------------------------------------------------
    @Override
    public void onClick(View itemView) {
        mClickHandler.onItemClick(getAdapterPosition());
    }
    // ---------------------------------------------------------------------------------------------
}