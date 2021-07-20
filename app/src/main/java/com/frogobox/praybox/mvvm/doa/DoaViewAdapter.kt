package com.frogobox.praybox.mvvm.doa

import android.view.View
import android.view.ViewGroup
import com.frogobox.praybox.core.BaseViewAdapter
import com.frogobox.praybox.core.BaseViewHolder
import com.frogobox.praybox.model.DoaShalat
import kotlinx.android.synthetic.main.content_tatacara_text_doa.view.*

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * Release-Pray-Box
 * Copyright (C) 15/12/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * com.frogobox.praybox.view.adapter
 *
 */
class DoaViewAdapter : BaseViewAdapter<DoaShalat>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<DoaShalat> {
        return DoaViewHolder(viewLayout(parent))
    }

    inner class DoaViewHolder(view: View) : BaseViewHolder<DoaShalat>(view) {

        private val mTextViewDoaArab = view.doa_setelah_shalat_arab
        private val mTextViewDoaLatin = view.doa_setelah_shalat_latin
        private val mTextViewDoaTerjemahan = view.doa_setelah_shalat_arti

        override fun initComponent(data: DoaShalat) {
            super.initComponent(data)

            mTextViewDoaArab.text = data.arabDoa
            mTextViewDoaLatin.text = data.latinDoa
            mTextViewDoaTerjemahan.text = data.terjemahDoa

        }
    }

}