package com.frogobox.praybox.view.adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.frogobox.praybox.source.model.NiatShalat;

import com.frogobox.praybox.R;

import java.util.ArrayList;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * JagoSholat
 * Copyright (C) 12/09/2018.
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
public class NiatShalatRecyclerViewAdapter extends RecyclerView.Adapter<NiatShalatRecyclerViewAdapter.MyViewHolder>{

    public Context context;
    public ArrayList<NiatShalat> mArrayList;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txt_rakaat, txt_shalat, txt_arab, txt_latin, txt_terjemah;

        public MyViewHolder(View itemView) {
            super(itemView);
            // -------------------------------------------------------------------------------------
            txt_rakaat = (TextView)itemView.findViewById(R.id.niat_textview_rakaat);
            txt_shalat = (TextView)itemView.findViewById(R.id.niat_textview_shalat);
            txt_arab = (TextView)itemView.findViewById(R.id.niat_textview_arab);
            txt_latin = (TextView)itemView.findViewById(R.id.niat_textview_latin_arab);
            txt_terjemah = (TextView)itemView.findViewById(R.id.niat_textview_arti);
            // -------------------------------------------------------------------------------------
        }
    }

    public NiatShalatRecyclerViewAdapter(Context context, ArrayList<NiatShalat> mArrayList) {
        this.context = context;
        this.mArrayList = mArrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_tatacara_text_niat, parent, false);
        NiatShalatRecyclerViewAdapter.MyViewHolder ViewHolder = new NiatShalatRecyclerViewAdapter.MyViewHolder(v);
        return ViewHolder;
    }
    

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // -----------------------------------------------------------------------------------------
        holder.txt_rakaat.setText(mArrayList.get(position).getRakaat());
        holder.txt_shalat.setText(mArrayList.get(position).getShalat());
        holder.txt_arab.setText(mArrayList.get(position).getArab());
        holder.txt_latin.setText(mArrayList.get(position).getLatin());
        holder.txt_terjemah.setText(mArrayList.get(position).getTerjemah());
        // -----------------------------------------------------------------------------------------
    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }
}