package com.frogobox.praybox.mvvm.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.frogobox.praybox.R
import com.frogobox.praybox.core.BaseFragment
import com.frogobox.praybox.util.SingleConstant
import com.frogobox.praybox.util.SingleFunc
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
        val mWaktuShalatHelper = SingleFunc.WaktuShalat
        val mMethodHelper = SingleFunc.Controller

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
            SingleConstant.Const.SHUBUH -> {
                jadwal_textview_shalat.text = SingleConstant.Const.DZUHUR.substring(7)
                countTime = (detikDzuhur - jumlahDetikSaatIni) * SingleConstant.Const.DETIK_KE_MILI
            }
            SingleConstant.Const.DZUHUR -> {
                jadwal_textview_shalat.text = SingleConstant.Const.ASHAR.substring(7)
                countTime = (detikAshar - jumlahDetikSaatIni) * SingleConstant.Const.DETIK_KE_MILI
            }
            SingleConstant.Const.ASHAR -> {
                jadwal_textview_shalat.text = SingleConstant.Const.MAGHRIB.substring(7)
                countTime = (detikMaghrib - jumlahDetikSaatIni) * SingleConstant.Const.DETIK_KE_MILI
            }
            SingleConstant.Const.MAGHRIB -> {
                jadwal_textview_shalat.text = SingleConstant.Const.ISYA.substring(7)
                countTime = (detikIsya - jumlahDetikSaatIni) * SingleConstant.Const.DETIK_KE_MILI
            }
            SingleConstant.Const.ISYA -> {
                jadwal_textview_shalat.text = SingleConstant.Const.SHUBUH.substring(7)
                if (jumlahDetikSaatIni == detikAfterMid || jumlahDetikSaatIni < detikShubuh) {
                    countTime = (detikShubuh - jumlahDetikSaatIni) * SingleConstant.Const.DETIK_KE_MILI
                } else if (jumlahDetikSaatIni == detikIsya || jumlahDetikSaatIni <= detikBeforeMid) {
                    countTime = (detikShubuh + detikBeforeMid - jumlahDetikSaatIni) * SingleConstant.Const.DETIK_KE_MILI
                }
            }
            else -> {
                jadwal_textview_shalat.text = SingleConstant.Const.DZUHUR.substring(7)
                countTime = (detikDzuhur - jumlahDetikSaatIni) * SingleConstant.Const.DETIK_KE_MILI
            }
        }
        // -----------------------------------------------------------------------------------------
        mWaktuShalatHelper.setTimeOnline(jadwal_textview_shubuh, jadwal_textview_dzuhur, jadwal_textview_ashar, jadwal_textview_maghrib, jadwal_textview_isya)
        mWaktuShalatHelper.CoundownTime(countTime, jadwal_textview_hitungmundur)
        // -----------------------------------------------------------------------------------------
    }
}