package com.frogobox.praybox.source;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * JagoSholat
 * Copyright (C) 30/03/2018.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : f.miir117@gmail.com


 * Majors   : D3 Teknik Informatika 2016
 * Campus   : Telkom University
 * -----------------------------------------
 * id.amirisback.frogobox
 */

public class DataOperation {

    public DataOperation() {
    }
    // ---------------------------------------------------------------------------------------------
    // Constants projection ini gunanya untuk memilih column pada database
    // Guna variable projection ini sama seperti * pada SQL
    private String projection[] = {DataContract.DataEntry._ID,
            DataContract.DataEntry.COLUMN_TANGGAL,
            DataContract.DataEntry.COLUMN_SHALAT,
            DataContract.DataEntry.COLUMN_WAKTU,
            DataContract.DataEntry.COLUMN_STATUS
    };
    // ---------------------------------------------------------------------------------------------

//    private long result; // result ini untuk mendapatkan boolean dari insert data (true/false)

    // Insert Data dalam DatabaseHelper ------------------------------------------------------------------
    public boolean insertData(Context context, String id, String tanggal, String shalat, String waktu, String status) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataContract.DataEntry._ID, id);
        contentValues.put(DataContract.DataEntry.COLUMN_TANGGAL, tanggal);
        contentValues.put(DataContract.DataEntry.COLUMN_SHALAT, shalat);
        contentValues.put(DataContract.DataEntry.COLUMN_WAKTU, waktu);
        contentValues.put(DataContract.DataEntry.COLUMN_STATUS, status);
        Uri resultUri = context.getContentResolver().insert(DataContract.DataEntry.CONTENT_URI,contentValues);
        if (resultUri == null) {
            return false;
        } else {
            return true;
        }
    }
    // ---------------------------------------------------------------------------------------------

    public boolean deleteDataId(Context context, String mID) {
        String selection = DataContract.DataEntry._ID + " = '" + mID + "'";
        int resultUri = context.getContentResolver().delete(DataContract.DataEntry.CONTENT_URI, selection, null);
        if (resultUri == 0) {
            return false;
        } else {
            return true;
        }
    }


    public boolean updateDataTime(Context context, String waktu, String mID) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataContract.DataEntry.COLUMN_WAKTU,waktu);
        String selection = DataContract.DataEntry._ID + " = '" + mID + "'";
        int resultUri = context.getContentResolver().update(DataContract.DataEntry.CONTENT_URI, contentValues, selection, null);
        if (resultUri == 0) {
            return false;
        } else {
            return true;
        }
    }

    // Method Cursor untuk menarik semua data sementara dari database ------------------------------
    // dalam method ini menggambil semua data tanpa arguments
    public Cursor getDataAll(Context context){
        return context.getContentResolver().query(
            DataContract.DataEntry.CONTENT_URI,
            projection,
            null,
            null,
            DataContract.DataEntry.COLUMN_TANGGAL);
    }
    // ---------------------------------------------------------------------------------------------


    // Mengambil semua data dengan kondisi dimana tanggal sama dengan tanggal inputan --------------
    public Cursor getDataToday(Context context, String tanggal){
        String selection = DataContract.DataEntry.COLUMN_TANGGAL + " = '" + tanggal + "'";
        return context.getContentResolver().query(
            DataContract.DataEntry.CONTENT_URI,
            projection,
            selection,
            null,
            null);
    }
    // ---------------------------------------------------------------------------------------------

    // Kondisi dimana tanggal dan shalat sama dengan inputan ---------------------------------------
    public Cursor getDataDateShalat(Context context, String tanggal, String shalat) {
        String selection = DataContract.DataEntry.COLUMN_TANGGAL + " = '" + tanggal +
                "' AND " + DataContract.DataEntry.COLUMN_SHALAT + " = '" + shalat + "'";
        return context.getContentResolver().query(
            DataContract.DataEntry.CONTENT_URI,
            projection,
            selection,
            null,
            DataContract.DataEntry._ID);

    }
    // ---------------------------------------------------------------------------------------------

    public Cursor getDataSameDate(Context context){
        String projectionTanggal[] = {"DISTINCT " + DataContract.DataEntry.COLUMN_TANGGAL};
        return context.getContentResolver().query(
            DataContract.DataEntry.CONTENT_URI,
            projectionTanggal,
            null,
            null,
            null);
    }

    public String[] getProjection() {
        return projection;
    }



}