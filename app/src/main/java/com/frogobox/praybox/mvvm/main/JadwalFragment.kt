package com.frogobox.praybox.mvvm.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.frogobox.praybox.core.BaseFragment
import com.frogobox.praybox.databinding.FragmentJadwalBinding
import com.frogobox.praybox.util.SingleConstant
import com.frogobox.praybox.util.SingleFunc
class JadwalFragment : BaseFragment<FragmentJadwalBinding>() {

    private var countTime = 0

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup
    ): FragmentJadwalBinding {
        return FragmentJadwalBinding.inflate(inflater, container, false)
    }

    override fun setupViewModel() {
    }

    override fun setupUI(savedInstanceState: Bundle?) {

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

        binding?.apply {
            when (mJadwal) {
                SingleConstant.Const.SHUBUH -> {
                    jadwalTextviewShalat.text = SingleConstant.Const.DZUHUR.substring(7)
                    countTime =
                        (detikDzuhur - jumlahDetikSaatIni) * SingleConstant.Const.DETIK_KE_MILI
                }
                SingleConstant.Const.DZUHUR -> {
                    jadwalTextviewShalat.text = SingleConstant.Const.ASHAR.substring(7)
                    countTime =
                        (detikAshar - jumlahDetikSaatIni) * SingleConstant.Const.DETIK_KE_MILI
                }
                SingleConstant.Const.ASHAR -> {
                    jadwalTextviewShalat.text = SingleConstant.Const.MAGHRIB.substring(7)
                    countTime =
                        (detikMaghrib - jumlahDetikSaatIni) * SingleConstant.Const.DETIK_KE_MILI
                }
                SingleConstant.Const.MAGHRIB -> {
                    jadwalTextviewShalat.text = SingleConstant.Const.ISYA.substring(7)
                    countTime =
                        (detikIsya - jumlahDetikSaatIni) * SingleConstant.Const.DETIK_KE_MILI
                }
                SingleConstant.Const.ISYA -> {
                    jadwalTextviewShalat.text = SingleConstant.Const.SHUBUH.substring(7)
                    if (jumlahDetikSaatIni == detikAfterMid || jumlahDetikSaatIni < detikShubuh) {
                        countTime =
                            (detikShubuh - jumlahDetikSaatIni) * SingleConstant.Const.DETIK_KE_MILI
                    } else if (jumlahDetikSaatIni == detikIsya || jumlahDetikSaatIni <= detikBeforeMid) {
                        countTime =
                            (detikShubuh + detikBeforeMid - jumlahDetikSaatIni) * SingleConstant.Const.DETIK_KE_MILI
                    }
                }
                else -> {
                    jadwalTextviewShalat.text = SingleConstant.Const.DZUHUR.substring(7)
                    countTime =
                        (detikDzuhur - jumlahDetikSaatIni) * SingleConstant.Const.DETIK_KE_MILI
                }
            }
            // -----------------------------------------------------------------------------------------
            mWaktuShalatHelper.setTimeOnline(
                jadwalTextviewShubuh,
                jadwalTextviewDzuhur,
                jadwalTextviewAshar,
                jadwalTextviewMaghrib,
                jadwalTextviewIsya
            )
            mWaktuShalatHelper.CoundownTime(countTime, jadwalTextviewHitungmundur)
            // -----------------------------------------------------------------------------------------
        }
    }
    

}