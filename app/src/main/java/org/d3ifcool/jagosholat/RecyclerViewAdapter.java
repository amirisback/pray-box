package org.d3ifcool.jagosholat;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.d3ifcool.jagosholat.Controller.StatistikFragmentContent.StatistikWord;
import org.d3ifcool.jagosholat.Model.DataContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ikhsan ramadhan
 * =========================================
 * 40-mobpro-4002-jago-sholat
 * Copyright (C) 10/19/2018.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhamad Ikhsan Ramadhan
 * E-mail   : ikhsanramadhan28@gmail.com
 * Majors   : D3 Teknik Informatika 2016
 * Campus   : Telkom University
 * -----------------------------------------
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private Cursor mCursor;

    public RecyclerViewAdapter(Context Context, Cursor cursor) {

        mContext = Context;
        mCursor = cursor;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.content_statistik_hari_ini,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(!mCursor.move(position)){
            return;
        }


        int waktuColumnIndex = mCursor.getColumnIndex(DataContract.DataEntry.COLUMN_WAKTU);
        int shalatColumnIndex = mCursor.getColumnIndex(DataContract.DataEntry.COLUMN_SHALAT);
        int statusColumnIndex = mCursor.getColumnIndex(DataContract.DataEntry.COLUMN_STATUS);

        String waktu = mCursor.getString(waktuColumnIndex);
        String shalat = mCursor.getString(shalatColumnIndex);
        String status = mCursor.getString(statusColumnIndex);

        holder.stat_shalat.setText(waktu);
        holder.stat_waktu.setText(shalat);

        int outImage;
        if (status.equalsIgnoreCase("shalat")) {
            outImage = R.drawable.ic_statistik_48dp_iya; // Shalat
        } else {
            outImage = R.drawable.ic_statistik_48dp_tidak; // Tidak Shalat
        }
        int resIdImage = outImage;
        holder.img.setImageResource(resIdImage);
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView stat_waktu;
        final TextView stat_shalat;
        final ImageView img;

        ViewHolder(View itemView) {
            super(itemView);
            stat_waktu = (TextView) itemView.findViewById(R.id.txt_harian_waktu);
            stat_shalat = (TextView) itemView.findViewById(R.id.txt_harian_shalat);
            img = (ImageView) itemView.findViewById(R.id.img_harian_shalat);

            itemView.setFocusable(true);


        }
    }

    public void swapCursor(Cursor newCursor){
        if(mCursor != null){
            mCursor.close();
        }

        mCursor = newCursor;
        if (newCursor != null){
            notifyDataSetChanged();
        }
    }
}





