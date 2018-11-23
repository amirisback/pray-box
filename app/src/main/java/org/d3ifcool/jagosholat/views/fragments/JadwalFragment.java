package org.d3ifcool.jagosholat.views.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.d3ifcool.jagosholat.presenters.helpers.MethodHelper;
import org.d3ifcool.jagosholat.presenters.helpers.WaktuShalatHelper;
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
        WaktuShalatHelper mWaktuShalatHelper = new WaktuShalatHelper();
        MethodHelper mMethodHelper = new MethodHelper();
        // -----------------------------------------------------------------------------------------
        int jumlahDetikSaatIni = mMethodHelper.getSumWaktuDetik();
        int miliDetik = mMethodHelper.getDetikKeMiliDetik();
        String mJadwal = mWaktuShalatHelper.getJadwalShalat();
        // -----------------------------------------------------------------------------------------
        int detikShubuh = mWaktuShalatHelper.getJmlWaktuShubuh();
        int detikDzuhur = mWaktuShalatHelper.getJmlWaktuDzuhur();
        int detikAshar = mWaktuShalatHelper.getJmlWaktuAshar();
        int detikMaghrib = mWaktuShalatHelper.getJmlWaktuMaghrib();
        int detikIsya = mWaktuShalatHelper.getJmlWaktuIsya();
        int detikAfterMid = mWaktuShalatHelper.getJmlAftMidnight();
        int detikBeforeMid = mWaktuShalatHelper.getJmlBeMidnight();
        // -----------------------------------------------------------------------------------------
        // Deklarasi Elemen XML
        TextView mTextViewCoundown = rootView.findViewById(R.id.jadwal_textview_hitungmundur);
        TextView mTextViewWaktuShubuh = rootView.findViewById(R.id.jadwal_textview_shubuh);
        TextView mTextViewWaktuDzuhur = rootView.findViewById(R.id.jadwal_textview_dzuhur);
        TextView mTextViewWaktuAshar = rootView.findViewById(R.id.jadwal_textview_ashar);
        TextView mTextViewWaktuMaghrib = rootView.findViewById(R.id.jadwal_textview_maghrib);
        TextView mTextViewWaktuIsya = rootView.findViewById(R.id.jadwal_textview_isya);
        TextView mTextViewShalatMendatang = rootView.findViewById(R.id.jadwal_textview_shalat);
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
                mTextViewShalatMendatang.setText(DZUHUR.substring(7));
                countTime = (detikDzuhur - jumlahDetikSaatIni) * miliDetik;
                break;
            case DZUHUR:
                mTextViewShalatMendatang.setText(ASHAR.substring(7));
                countTime = (detikAshar - jumlahDetikSaatIni) * miliDetik;
                break;
            case ASHAR:
                mTextViewShalatMendatang.setText(MAGHRIB.substring(7));
                countTime = (detikMaghrib - jumlahDetikSaatIni) * miliDetik;
                break;
            case MAGHRIB:
                mTextViewShalatMendatang.setText(ISYA.substring(7));
                countTime = (detikIsya - jumlahDetikSaatIni) * miliDetik;
                break;
            case ISYA:
                mTextViewShalatMendatang.setText(SHUBUH.substring(7));
                if ((jumlahDetikSaatIni == detikAfterMid) || (jumlahDetikSaatIni < detikShubuh)) {
                    countTime = (detikShubuh - jumlahDetikSaatIni) * miliDetik;
                } else if ((jumlahDetikSaatIni == detikIsya) || (jumlahDetikSaatIni <= detikBeforeMid)) {
                    countTime =  (detikShubuh + detikBeforeMid - jumlahDetikSaatIni) * miliDetik;
                }
                break;
            default:
                mTextViewShalatMendatang.setText(DZUHUR.substring(7));
                countTime = (detikDzuhur - jumlahDetikSaatIni) * miliDetik;
                break;
        }
        // -----------------------------------------------------------------------------------------
        mWaktuShalatHelper.setTimeOnline(mTextViewWaktuShubuh, mTextViewWaktuDzuhur, mTextViewWaktuAshar, mTextViewWaktuMaghrib, mTextViewWaktuIsya);
        mWaktuShalatHelper.CoundownTime(countTime, mTextViewCoundown);
        // -----------------------------------------------------------------------------------------
        return rootView;
    }
}