package com.frogobox.praybox.mvvm.doa

import android.view.View
import android.view.ViewGroup
import com.frogobox.praybox.model.NiatShalat
import com.frogobox.recycler.core.FrogoRecyclerViewAdapter
import com.frogobox.recycler.core.FrogoRecyclerViewHolder
import kotlinx.android.synthetic.main.content_tatacara_text_niat.view.*

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
class NiatViewAdapter : FrogoRecyclerViewAdapter<NiatShalat>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FrogoRecyclerViewHolder<NiatShalat> {
        return NiatViewHolder(viewLayout(parent))
    }

    inner class NiatViewHolder(view: View) : FrogoRecyclerViewHolder<NiatShalat>(view) {

        private val txt_rakaat = view.niat_textview_rakaat
        private val txt_shalat = view.niat_textview_shalat
        private val txt_arab = view.niat_textview_arab
        private val txt_latin = view.niat_textview_latin_arab
        private val txt_terjemah = view.niat_textview_arti

        override fun initComponent(data: NiatShalat) {

            txt_rakaat.text = data.rakaat
            txt_shalat.text = data.shalat
            txt_latin.text = data.latin
            txt_arab.text = data.arab
            txt_terjemah.text = data.terjemah

        }
    }
}