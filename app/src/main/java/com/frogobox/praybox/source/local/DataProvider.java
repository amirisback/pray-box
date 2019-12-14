package com.frogobox.praybox.source.local;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.Log;

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
 * Line     : bullbee117
 * Phone    : 081357108568
 * Majors   : D3 Teknik Informatika 2016
 * Campus   : Telkom University
 * -----------------------------------------
 * id.amirisback.frogobox
 */

public class DataProvider extends ContentProvider {

    // ---------------------------------------------------------------------------------------------
    // Deklarasi Kebutuhan Constants
    // Setiap Table memiliki alamat sendiri
    public static final String LOG_TAG = DataProvider.class.getSimpleName();
    private static final int DATA = 100; // Projection All
    private static final int DATA_ID = 101; // Projection ID
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private DatabaseHelper mDbHelper;
    // ---------------------------------------------------------------------------------------------
    static {
        sUriMatcher.addURI(DataContract.CONTENT_AUTHORITY, DataContract.PATH_DATA, DATA);
        sUriMatcher.addURI(DataContract.CONTENT_AUTHORITY, DataContract.PATH_DATA + "/#", DATA_ID);
    }
    // ---------------------------------------------------------------------------------------------

    @Override
    public boolean onCreate() {
        mDbHelper = new DatabaseHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase database = mDbHelper.getReadableDatabase(); // Get readable database
        Cursor cursor;
        int match = sUriMatcher.match(uri);
        switch (match) {
            case DATA:
                cursor = database.query(DataContract.DataEntry.TABLE_NAME, projection, selection,
                        selectionArgs, null,null,sortOrder); // Select Semua Data
                break;
            case DATA_ID:

                selection = DataContract.DataEntry._ID + "=?"; // Where ID = "?"
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };

                cursor = database.query(DataContract.DataEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }

        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case DATA :
                return insertData(uri, values);
            default :
                throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }
    }

    private Uri insertData(Uri uri, ContentValues values){
        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        long id = database.insert(DataContract.DataEntry.TABLE_NAME, null , values);

        if (id ==-1){
            Log.e(LOG_TAG, "Failed to insert row for "+uri);
            return null;
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {

        final int match = sUriMatcher.match(uri);
        switch (match) {
            case DATA :
                return updateData(uri, contentValues, selection, selectionArgs);
            case DATA_ID :
                selection = DataContract.DataEntry._ID + "=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};
                return  updateData(uri, contentValues, selection, selectionArgs);
            default :
                throw new IllegalArgumentException("Update is not Supported");
        }
    }

    private int updateData(Uri uri, ContentValues values, String selection, String[] selectionArgs){
        if (values.size() == 0){
            return 0;
        }
        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        int rowUpdated = database.update(DataContract.DataEntry.TABLE_NAME, values, selection, selectionArgs);
        if (rowUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowUpdated;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        int rowsDeleted;
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case DATA:
                rowsDeleted = database.delete(DataContract.DataEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case DATA_ID:
                selection = DataContract.DataEntry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
                rowsDeleted = database.delete(DataContract.DataEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Deletion is not supported for " + uri);
        }
        if (rowsDeleted != 0) { getContext().getContentResolver().notifyChange(uri, null); }
        return rowsDeleted;
    }

    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case DATA:
                return DataContract.DataEntry.CONTENT_LIST_TYPE;
            case DATA_ID:
                return DataContract.DataEntry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalStateException("Unknown URI " + uri + " with match " + match);
        }
    }
}