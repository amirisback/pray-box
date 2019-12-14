package org.d3ifcool.jagosholat.ui.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.d3ifcool.jagosholat.util.helper.MethodHelper;
import org.d3ifcool.jagosholat.util.helper.WaktuShalatHelper;
import org.d3ifcool.jagosholat.R;

import static org.d3ifcool.jagosholat.util.helper.VarConstants.Constants;


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
            case Constants.SHUBUH:
                mTextViewShalatMendatang.setText(Constants.DZUHUR.substring(7));
                countTime = (detikDzuhur - jumlahDetikSaatIni) * Constants.DETIK_KE_MILI;
                break;
            case Constants.DZUHUR:
                mTextViewShalatMendatang.setText(Constants.ASHAR.substring(7));
                countTime = (detikAshar - jumlahDetikSaatIni) * Constants.DETIK_KE_MILI;
                break;
            case Constants.ASHAR:
                mTextViewShalatMendatang.setText(Constants.MAGHRIB.substring(7));
                countTime = (detikMaghrib - jumlahDetikSaatIni) * Constants.DETIK_KE_MILI;
                break;
            case Constants.MAGHRIB:
                mTextViewShalatMendatang.setText(Constants.ISYA.substring(7));
                countTime = (detikIsya - jumlahDetikSaatIni) * Constants.DETIK_KE_MILI;
                break;
            case Constants.ISYA:
                mTextViewShalatMendatang.setText(Constants.SHUBUH.substring(7));
                if ((jumlahDetikSaatIni == detikAfterMid) || (jumlahDetikSaatIni < detikShubuh)) {
                    countTime = (detikShubuh - jumlahDetikSaatIni) * Constants.DETIK_KE_MILI;
                } else if ((jumlahDetikSaatIni == detikIsya) || (jumlahDetikSaatIni <= detikBeforeMid)) {
                    countTime =  (detikShubuh + detikBeforeMid - jumlahDetikSaatIni) * Constants.DETIK_KE_MILI;
                }
                break;
            default:
                mTextViewShalatMendatang.setText(Constants.DZUHUR.substring(7));
                countTime = (detikDzuhur - jumlahDetikSaatIni) * Constants.DETIK_KE_MILI;
                break;
        }
        // -----------------------------------------------------------------------------------------
        mWaktuShalatHelper.setTimeOnline(mTextViewWaktuShubuh, mTextViewWaktuDzuhur, mTextViewWaktuAshar, mTextViewWaktuMaghrib, mTextViewWaktuIsya);
        mWaktuShalatHelper.CoundownTime(countTime, mTextViewCoundown);
        // -----------------------------------------------------------------------------------------
        return rootView;
    }
}