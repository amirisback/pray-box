package com.frogobox.praybox.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.frogobox.praybox.R
import com.frogobox.praybox.base.view.ui.BaseFragment
import com.frogobox.praybox.ui.activitiy.DoaActivity
import com.frogobox.praybox.ui.activitiy.TataCaraActivity
import kotlinx.android.synthetic.main.fragment_lainnya.*

class LainnyaFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? { // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lainnya, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_doa_activity.setOnClickListener {
            baseStartActivity<DoaActivity>()

        }

        btn_tata_cara_activity.setOnClickListener {
            baseStartActivity<TataCaraActivity>()
        }
    }


}