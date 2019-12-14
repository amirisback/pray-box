package org.d3ifcool.jagosholat.view.adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.d3ifcool.jagosholat.source.model.DoaShalat;
import org.d3ifcool.jagosholat.R;

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
public class DoaShalatRecyclerViewAdapter extends RecyclerView.Adapter<DoaShalatRecyclerViewAdapter.ViewHolder> {

    public Context context;
    private ArrayList<DoaShalat> mArrayListDoaShalat;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTextViewDoaArab, mTextViewDoaLatin, mTextViewDoaTerjemahan;

        public ViewHolder(View itemView) {
            super(itemView);
            // -------------------------------------------------------------------------------------
            mTextViewDoaArab = itemView.findViewById(R.id.doa_setelah_shalat_arab);
            mTextViewDoaLatin = itemView.findViewById(R.id.doa_setelah_shalat_latin);
            mTextViewDoaTerjemahan = itemView.findViewById(R.id.doa_setelah_shalat_arti);
            // -------------------------------------------------------------------------------------
        }
    }

    public DoaShalatRecyclerViewAdapter(Context context, ArrayList<DoaShalat> mArrayList) {
        this.context = context;
        this.mArrayListDoaShalat = mArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_tatacara_text_doa, parent, false);
        DoaShalatRecyclerViewAdapter.ViewHolder ViewHolder = new DoaShalatRecyclerViewAdapter.ViewHolder(v);
        return ViewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // -----------------------------------------------------------------------------------------
        holder.mTextViewDoaArab.setText(mArrayListDoaShalat.get(position).getArabDoa());
        holder.mTextViewDoaLatin.setText(mArrayListDoaShalat.get(position).getLatinDoa());
        holder.mTextViewDoaTerjemahan.setText(mArrayListDoaShalat.get(position).getTerjemahDoa());
        // -----------------------------------------------------------------------------------------
    }

    @Override
    public int getItemCount() {
        return mArrayListDoaShalat.size();
    }
}