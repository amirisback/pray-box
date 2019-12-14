package com.frogobox.praybox.ui.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.frogobox.praybox.R;
import com.frogobox.praybox.util.helper.MethodHelper;
import com.frogobox.praybox.util.helper.VarConstants;
import com.frogobox.praybox.util.helper.WaktuShalatHelper;


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
        switch (mJadwal) {
            case VarConstants.Constants.SHUBUH:
                mTextViewShalatMendatang.setText(VarConstants.Constants.DZUHUR.substring(7));
                countTime = (detikDzuhur - jumlahDetikSaatIni) * VarConstants.Constants.DETIK_KE_MILI;
                break;
            case VarConstants.Constants.DZUHUR:
                mTextViewShalatMendatang.setText(VarConstants.Constants.ASHAR.substring(7));
                countTime = (detikAshar - jumlahDetikSaatIni) * VarConstants.Constants.DETIK_KE_MILI;
                break;
            case VarConstants.Constants.ASHAR:
                mTextViewShalatMendatang.setText(VarConstants.Constants.MAGHRIB.substring(7));
                countTime = (detikMaghrib - jumlahDetikSaatIni) * VarConstants.Constants.DETIK_KE_MILI;
                break;
            case VarConstants.Constants.MAGHRIB:
                mTextViewShalatMendatang.setText(VarConstants.Constants.ISYA.substring(7));
                countTime = (detikIsya - jumlahDetikSaatIni) * VarConstants.Constants.DETIK_KE_MILI;
                break;
            case VarConstants.Constants.ISYA:
                mTextViewShalatMendatang.setText(VarConstants.Constants.SHUBUH.substring(7));
                if ((jumlahDetikSaatIni == detikAfterMid) || (jumlahDetikSaatIni < detikShubuh)) {
                    countTime = (detikShubuh - jumlahDetikSaatIni) * VarConstants.Constants.DETIK_KE_MILI;
                } else if ((jumlahDetikSaatIni == detikIsya) || (jumlahDetikSaatIni <= detikBeforeMid)) {
                    countTime =  (detikShubuh + detikBeforeMid - jumlahDetikSaatIni) * VarConstants.Constants.DETIK_KE_MILI;
                }
                break;
            default:
                mTextViewShalatMendatang.setText(VarConstants.Constants.DZUHUR.substring(7));
                countTime = (detikDzuhur - jumlahDetikSaatIni) * VarConstants.Constants.DETIK_KE_MILI;
                break;
        }
        // -----------------------------------------------------------------------------------------
        mWaktuShalatHelper.setTimeOnline(mTextViewWaktuShubuh, mTextViewWaktuDzuhur, mTextViewWaktuAshar, mTextViewWaktuMaghrib, mTextViewWaktuIsya);
        mWaktuShalatHelper.CoundownTime(countTime, mTextViewCoundown);
        // -----------------------------------------------------------------------------------------
        return rootView;
    }
}