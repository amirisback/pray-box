package com.frogobox.praybox.mvvm.doa

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.frogobox.praybox.R
import com.frogobox.praybox.model.NiatShalat
import com.frogobox.recycler.core.FrogoRecyclerNotifyListener
import com.frogobox.recycler.core.FrogoRecyclerViewAdapter
import com.frogobox.recycler.core.FrogoRecyclerViewHolder

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
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FrogoRecyclerViewHolder<NiatShalat> {
        return NiatViewHolder(viewLayout(parent))
    }

    inner class NiatViewHolder(view: View) : FrogoRecyclerViewHolder<NiatShalat>(view) {

        private val txt_rakaat = view.findViewById<TextView>(R.id.niat_textview_rakaat)
        private val txt_shalat = view.findViewById<TextView>(R.id.niat_textview_shalat)
        private val txt_arab = view.findViewById<TextView>(R.id.niat_textview_arab)
        private val txt_latin = view.findViewById<TextView>(R.id.niat_textview_latin_arab)
        private val txt_terjemah = view.findViewById<TextView>(R.id.niat_textview_arti)

        override fun initComponent(
            data: NiatShalat,
            position: Int,
            notifyListener: FrogoRecyclerNotifyListener<NiatShalat>
        ) {

            txt_rakaat.text = data.rakaat
            txt_shalat.text = data.shalat
            txt_latin.text = data.latin
            txt_arab.text = data.arab
            txt_terjemah.text = data.terjemah

        }
    }
}