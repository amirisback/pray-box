package com.frogobox.praybox.source;

/*
 * Created by Ikhsan Ramadhan on 11/03/2018.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Deklarasi Nama DatabaseHelper dan Versinya --------------------------------------------------------
    private static final String DATABASE = DataContract.DB;
    private static final int DATABASE_VERSION = 1;
    // ---------------------------------------------------------------------------------------------

    // Constants ini gunanya adalah untuk mendapatkan fungsi dari library SQLiteDatabase ------------
    private SQLiteDatabase sqLiteDatabase = getWritableDatabase();
    // ada dua macam, "WriteableDatabase" dan "ReadableDatabase"
    // ---------------------------------------------------------------------------------------------

    public DatabaseHelper(Context context) {
        super(context, DATABASE, null, DATABASE_VERSION);
        onCreate(sqLiteDatabase);
    }

    // Disini Code Untuk Create Table di database SQLite -------------------------------------------
    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            String sql_create_table = "CREATE TABLE IF NOT EXISTS " + DataContract.DataEntry.TABLE_NAME + " (" +
                    DataContract.DataEntry._ID + " TEXT PRIMARY KEY," +
                    DataContract.DataEntry.COLUMN_TANGGAL + " TEXT NOT NULL," +
                    DataContract.DataEntry.COLUMN_SHALAT + " TEXT NOT NULL," +
                    DataContract.DataEntry.COLUMN_WAKTU + " TEXT NOT NULL," +
                    DataContract.DataEntry.COLUMN_STATUS + " TEXT NOT NULL);";
            db.execSQL(sql_create_table);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // ---------------------------------------------------------------------------------------------

    // Untuk mengupgrade table ---------------------------------------------------------------------
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql_drop_table = "DROP TABLE IF EXISTS " + DataContract.DataEntry.TABLE_NAME;
        db.execSQL(sql_drop_table);
        onCreate(db);
    }
    // ---------------------------------------------------------------------------------------------

}
