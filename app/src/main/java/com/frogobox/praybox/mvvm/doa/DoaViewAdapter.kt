package com.frogobox.praybox.mvvm.doa

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.frogobox.praybox.R
import com.frogobox.praybox.model.DoaShalat
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
class DoaViewAdapter : FrogoRecyclerViewAdapter<DoaShalat>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FrogoRecyclerViewHolder<DoaShalat> {
        return DoaViewHolder(viewLayout(parent))
    }

    inner class DoaViewHolder(view: View) : FrogoRecyclerViewHolder<DoaShalat>(view) {

        private val mTextViewDoaArab = view.findViewById<TextView>(R.id.doa_setelah_shalat_arab)
        private val mTextViewDoaLatin = view.findViewById<TextView>(R.id.doa_setelah_shalat_latin)
        private val mTextViewDoaTerjemahan =
            view.findViewById<TextView>(R.id.doa_setelah_shalat_arti)

        override fun initComponent(data: DoaShalat) {
            mTextViewDoaArab.text = data.arabDoa
            mTextViewDoaLatin.text = data.latinDoa
            mTextViewDoaTerjemahan.text = data.terjemahDoa

        }
    }

}