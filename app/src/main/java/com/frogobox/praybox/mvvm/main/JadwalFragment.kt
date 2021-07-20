package com.frogobox.praybox.mvvm.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.frogobox.praybox.R
import com.frogobox.praybox.core.BaseFragment
import com.frogobox.praybox.util.helper.ConstHelper
import com.frogobox.praybox.util.helper.MethodHelper
import com.frogobox.praybox.util.helper.WaktuShalatHelper
import kotlinx.android.synthetic.main.fragment_jadwal.*

class JadwalFragment : BaseFragment() {

    private var countTime = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? { // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_jadwal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Deklarasi Class helper
        val mWaktuShalatHelper = WaktuShalatHelper()
        val mMethodHelper = MethodHelper()

        val jumlahDetikSaatIni = mMethodHelper.sumWaktuDetik
        val mJadwal = mWaktuShalatHelper.jadwalShalat

        val detikShubuh = mWaktuShalatHelper.jmlWaktuShubuh
        val detikDzuhur = mWaktuShalatHelper.jmlWaktuDzuhur
        val detikAshar = mWaktuShalatHelper.jmlWaktuAshar
        val detikMaghrib = mWaktuShalatHelper.jmlWaktuMaghrib
        val detikIsya = mWaktuShalatHelper.jmlWaktuIsya
        val detikAfterMid = mWaktuShalatHelper.jmlAftMidnight
        val detikBeforeMid = mWaktuShalatHelper.jmlBeMidnight

        when (mJadwal) {
            ConstHelper.Const.SHUBUH -> {
                jadwal_textview_shalat.text = ConstHelper.Const.DZUHUR.substring(7)
                countTime = (detikDzuhur - jumlahDetikSaatIni) * ConstHelper.Const.DETIK_KE_MILI
            }
            ConstHelper.Const.DZUHUR -> {
                jadwal_textview_shalat.text = ConstHelper.Const.ASHAR.substring(7)
                countTime = (detikAshar - jumlahDetikSaatIni) * ConstHelper.Const.DETIK_KE_MILI
            }
            ConstHelper.Const.ASHAR -> {
                jadwal_textview_shalat.text = ConstHelper.Const.MAGHRIB.substring(7)
                countTime = (detikMaghrib - jumlahDetikSaatIni) * ConstHelper.Const.DETIK_KE_MILI
            }
            ConstHelper.Const.MAGHRIB -> {
                jadwal_textview_shalat.text = ConstHelper.Const.ISYA.substring(7)
                countTime = (detikIsya - jumlahDetikSaatIni) * ConstHelper.Const.DETIK_KE_MILI
            }
            ConstHelper.Const.ISYA -> {
                jadwal_textview_shalat.text = ConstHelper.Const.SHUBUH.substring(7)
                if (jumlahDetikSaatIni == detikAfterMid || jumlahDetikSaatIni < detikShubuh) {
                    countTime = (detikShubuh - jumlahDetikSaatIni) * ConstHelper.Const.DETIK_KE_MILI
                } else if (jumlahDetikSaatIni == detikIsya || jumlahDetikSaatIni <= detikBeforeMid) {
                    countTime = (detikShubuh + detikBeforeMid - jumlahDetikSaatIni) * ConstHelper.Const.DETIK_KE_MILI
                }
            }
            else -> {
                jadwal_textview_shalat.text = ConstHelper.Const.DZUHUR.substring(7)
                countTime = (detikDzuhur - jumlahDetikSaatIni) * ConstHelper.Const.DETIK_KE_MILI
            }
        }
        // -----------------------------------------------------------------------------------------
        mWaktuShalatHelper.setTimeOnline(jadwal_textview_shubuh, jadwal_textview_dzuhur, jadwal_textview_ashar, jadwal_textview_maghrib, jadwal_textview_isya)
        mWaktuShalatHelper.CoundownTime(countTime, jadwal_textview_hitungmundur)
        // -----------------------------------------------------------------------------------------
    }
}