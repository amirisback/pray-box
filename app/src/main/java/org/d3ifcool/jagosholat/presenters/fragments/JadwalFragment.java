package org.d3ifcool.jagosholat.presenters.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.d3ifcool.jagosholat.presenters.helpers.MethodHelper;
import org.d3ifcool.jagosholat.presenters.helpers.JadwalHelper;
import org.d3ifcool.jagosholat.R;


public class JadwalFragment extends Fragment {

    // ---------------------------------------------------------------------------------------------
    private int countTime;
    // ---------------------------------------------------------------------------------------------

    public JadwalFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_jadwal, container, false);
        // -----------------------------------------------------------------------------------------
        // Deklarasi Class helper
        JadwalHelper jadwalHelper = new JadwalHelper();
        MethodHelper methodHelper = new MethodHelper();
        // -----------------------------------------------------------------------------------------
        int jumlahDetikSaatIni = methodHelper.getSumWaktuDetik();
        int miliDetik = methodHelper.getDetikKeMiliDetik();
        String mJadwal = jadwalHelper.getMJadwalShalat();
        // -----------------------------------------------------------------------------------------
        int detikShubuh = jadwalHelper.getJmlWaktuShubuh();
        int detikDzuhur = jadwalHelper.getJmlWaktuDzuhur();
        int detikAshar = jadwalHelper.getJmlWaktuAshar();
        int detikMaghrib = jadwalHelper.getJmlWaktuMaghrib();
        int detikIsya = jadwalHelper.getJmlWaktuIsya();
        int detikAfterMid = jadwalHelper.getJmlAftMidnight();
        int detikBeforeMid = jadwalHelper.getJmlBeMidnight();
        // -----------------------------------------------------------------------------------------
        // Deklarasi Elemen XML
        TextView txt_coundown = (TextView)rootView.findViewById(R.id.jadwal_textview_hitungmundur);
        TextView txt_waktu_shubuh = (TextView)rootView.findViewById(R.id.jadwal_textview_shubuh);
        TextView txt_waktu_dzuhur = (TextView)rootView.findViewById(R.id.jadwal_textview_dzuhur);
        TextView txt_waktu_ashar = (TextView)rootView.findViewById(R.id.jadwal_textview_ashar);
        TextView txt_waktu_maghrib = (TextView)rootView.findViewById(R.id.jadwal_textview_maghrib);
        TextView txt_waktu_isya = (TextView)rootView.findViewById(R.id.jadwal_textview_isya);
        TextView txt_shalat = (TextView)rootView.findViewById(R.id.jadwal_textview_shalat);
        // -----------------------------------------------------------------------------------------
        // Deklarasi konstanta
        final String SHUBUH = "Shalat Shubuh";
        final String DZUHUR = "Shalat Dzuhur";
        final String ASHAR = "Shalat Ashar";
        final String MAGHRIB = "Shalat Maghrib";
        final String ISYA = "Shalat Isya";
        // -----------------------------------------------------------------------------------------
        switch (mJadwal) {
            case SHUBUH:
                txt_shalat.setText(DZUHUR.substring(7));
                countTime = (detikDzuhur - jumlahDetikSaatIni) * miliDetik;
                break;
            case DZUHUR:
                txt_shalat.setText(ASHAR.substring(7));
                countTime = (detikAshar - jumlahDetikSaatIni) * miliDetik;
                break;
            case ASHAR:
                txt_shalat.setText(MAGHRIB.substring(7));
                countTime = (detikMaghrib - jumlahDetikSaatIni) * miliDetik;
                break;
            case MAGHRIB:
                txt_shalat.setText(ISYA.substring(7));
                countTime = (detikIsya - jumlahDetikSaatIni) * miliDetik;
                break;
            case ISYA:
                txt_shalat.setText(SHUBUH.substring(7));
                if ((jumlahDetikSaatIni == detikAfterMid) || (jumlahDetikSaatIni < detikShubuh)) {
                    countTime = (detikShubuh - jumlahDetikSaatIni) * miliDetik;
                } else if ((jumlahDetikSaatIni == detikIsya) || (jumlahDetikSaatIni <= detikBeforeMid)) {
                    countTime =  (detikShubuh + detikBeforeMid - jumlahDetikSaatIni) * miliDetik;
                }
                break;
            default:
                txt_shalat.setText(DZUHUR.substring(7));
                countTime = (detikDzuhur - jumlahDetikSaatIni) * miliDetik;
                break;
        }
        // -----------------------------------------------------------------------------------------
        jadwalHelper.setTimeOnline(txt_waktu_shubuh, txt_waktu_dzuhur, txt_waktu_ashar, txt_waktu_maghrib, txt_waktu_isya);
        jadwalHelper.CoundownTime(countTime, txt_coundown);
        // -----------------------------------------------------------------------------------------
        return rootView;
    }
}