package com.frogobox.praybox.mvvm.main

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.frogobox.praybox.R
import com.frogobox.praybox.core.BaseFragment
import com.frogobox.praybox.databinding.ContentCatatanButtonBinding
import com.frogobox.praybox.databinding.FragmentCatatanBinding
import com.frogobox.praybox.source.DataOperation
import com.frogobox.praybox.util.SingleFunc
import java.util.*

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * 40-mobpro-4002-jago-sholat
 * Copyright (C) 21/09/2018.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com


 * Majors   : D3 Teknik Informatika 2016
 * Campus   : Telkom University
 * -----------------------------------------
 * id.amirisback.frogobox
 */
class CatatanFragment : BaseFragment<FragmentCatatanBinding>() {

    // Deklarasi Requirement Constants
    private val mHadistArab = intArrayOf(R.string.hadis_arab_0, R.string.hadis_arab_1, R.string.hadis_arab_2,
            R.string.hadis_arab_3, R.string.hadis_arab_4, R.string.hadis_arab_5)
    private val mHadistText = intArrayOf(R.string.hadis_text_0, R.string.hadis_text_1, R.string.hadis_text_2,
            R.string.hadis_text_3, R.string.hadis_text_4, R.string.hadis_text_5)

    private lateinit var mDialogView : ContentCatatanButtonBinding

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCatatanBinding {
        return FragmentCatatanBinding.inflate(inflater, container, false)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mDialogView = ContentCatatanButtonBinding.inflate(inflater,null,false)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun setupViewModel() {
    }

    override fun onViewCreatedExt(view: View, savedInstanceState: Bundle?) {
        super.onViewCreatedExt(view, savedInstanceState)
        val methodHelper = SingleFunc.Controller
        val mWaktuHelper = SingleFunc.WaktuShalat
        val crud = DataOperation()

        // Set tampilan tanggal dan waktu
        methodHelper.systemTime
        methodHelper.systemRealTime
        methodHelper.sumRealTime
        binding.apply {
            mWaktuHelper.setJadwalShalat(tvJadwalShalat)
            catatanTextviewTanggal.text = methodHelper.dateToday

            // Set Data Random Hadist untuk XML Layout
            val randomInt = Random()
            val maxRandom = mHadistArab.size - 1
            val minRandom = 0
            val getIndexArrayHadis = randomInt.nextInt(maxRandom - minRandom + 1) + minRandom

            val mResIdHadistArab = mHadistArab[getIndexArrayHadis]
            val mResIdHadistText = mHadistText[getIndexArrayHadis]
            catatanTextviewHadistArab.setText(mResIdHadistArab)
            catatanTextviewHadistText.setText(mResIdHadistText)

            // Deklarasi Element XML Update View
            val mDialogBuilder = AlertDialog.Builder(context)


            // Deklarasi custom dialog
            val mDialogForm = CatatanDialog(
                mDialogBuilder, mDialogView.root,
                methodHelper, context, crud, mWaktuHelper
            )

            // Fungsi untuk menampilkan button simpan
            val mShalatNow = tvJadwalShalat.text.toString()
            mDialogForm.viewSaveButton(catatanButtonCatatIbadah, mShalatNow)

            // Panggil method untuk mencatat
            catatanButtonCatatIbadah.setOnClickListener { mDialogForm.DialogForm() }
        }
    }



}