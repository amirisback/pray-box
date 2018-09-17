package org.d3ifcool.jagosholat.StatistikFragmentContent.StatistikAdapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import org.d3ifcool.jagosholat.Model.DataContract.DataEntry;

import org.d3ifcool.jagosholat.R;

public class StatistikHarianCursorAdapter extends CursorAdapter {

    public StatistikHarianCursorAdapter(Context context, Cursor c) {
        super(context, c, 0 /* flags */);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // Mereturn layout content dari listview yang akan di pakai
        // disini mereturn content_statistik_harian
        return LayoutInflater.from(context).inflate(R.layout.content_statistik_harian, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        // -----------------------------------------------------------------------------------------
        // Deklarasi Element XML
        TextView stat_waktu = (TextView)view.findViewById(R.id.stat_waktu);
        TextView stat_shalat = (TextView)view.findViewById(R.id.stat_shalat);
        ImageView img = (ImageView)view.findViewById(R.id.img_status);
        // -----------------------------------------------------------------------------------------

        // -----------------------------------------------------------------------------------------
        // Mencari index dalam database
        int waktuColumnIndex = cursor.getColumnIndex(DataEntry.COLUMN_WAKTU);
        int shalatColumnIndex = cursor.getColumnIndex(DataEntry.COLUMN_SHALAT);
        int statusColumnIndex = cursor.getColumnIndex(DataEntry.COLUMN_STATUS);
        // -----------------------------------------------------------------------------------------

        // -----------------------------------------------------------------------------------------
        // Mendapat data dari database berdasarkan index
        String waktu = cursor.getString(waktuColumnIndex);
        String shalat = cursor.getString(shalatColumnIndex);
        String status = cursor.getString(statusColumnIndex);
        // -----------------------------------------------------------------------------------------

        // -----------------------------------------------------------------------------------------
        // Mengecek gambar checklist
        String resourceImageStat[] = {"ic_done_white_48px", "ic_undone_white_48px"};
        String outImage;
        if (status.equalsIgnoreCase("shalat")){
            outImage = resourceImageStat[0]; // Tidak Shalat
        } else {
            outImage = resourceImageStat[1]; // Shalat
        }
        int resIdImage = context.getResources().getIdentifier(outImage, "drawable", context.getPackageName());
        // -----------------------------------------------------------------------------------------

        // -----------------------------------------------------------------------------------------
        // Menset Text berdasarkan String yang sudah di dapat
        stat_shalat.setText(shalat);
        stat_waktu.setText(waktu);
        img.setImageResource(resIdImage);
        // -----------------------------------------------------------------------------------------

    }

}
