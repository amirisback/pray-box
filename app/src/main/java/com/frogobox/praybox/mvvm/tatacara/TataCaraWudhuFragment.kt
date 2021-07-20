package com.frogobox.praybox.mvvm.tatacara

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.frogobox.praybox.R
import com.frogobox.praybox.core.BaseFragment

class TataCaraWudhuFragment : BaseFragment() {
    // ---------------------------------------------------------------------------------------------
    private var dotscount = 0
    private lateinit var dots: Array<ImageView?>
    private val imageResId = arrayOf(
        R.drawable.wudhu_0, R.drawable.wudhu_1, R.drawable.wudhu_2,
        R.drawable.wudhu_3, R.drawable.wudhu_4, R.drawable.wudhu_5, R.drawable.wudhu_6,
        R.drawable.wudhu_7, R.drawable.wudhu_8, R.drawable.wudhu_9
    )

    // ---------------------------------------------------------------------------------------------
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        // -----------------------------------------------------------------------------------------
        val rootView = inflater.inflate(R.layout.fragment_tatacara_image, container, false)
        val mViewPager: ViewPager = rootView.findViewById(R.id.tatacara_sliderimage_viewpager)
        val sliderDotspanel =
            rootView.findViewById<View>(R.id.tatacara_sliderdots_linearLayout) as LinearLayout
        // -----------------------------------------------------------------------------------------
        val tataCaraPagerAdapter = TataCaraPager(requireContext(), imageResId)
        mViewPager.adapter = tataCaraPagerAdapter
        dotscount = tataCaraPagerAdapter.count
        dots = arrayOfNulls(dotscount)
        // -----------------------------------------------------------------------------------------
        for (i in 0 until dotscount) {
            // -------------------------------------------------------------------------------------
            dots[i] = ImageView(context)
            dots[i]!!.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext().applicationContext,
                    R.drawable.ic_tatacara_dot_nonactive
                )
            )
            // -------------------------------------------------------------------------------------
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(8, 0, 8, 0)
            sliderDotspanel.addView(dots[i], params)
            // -------------------------------------------------------------------------------------
        }
        // -----------------------------------------------------------------------------------------
        dots[0]!!
            .setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext().applicationContext,
                    R.drawable.ic_tatacara_dot_active
                )
            )
        mViewPager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                for (i in 0 until dotscount) {
                    dots[i]!!.setImageDrawable(
                        ContextCompat.getDrawable(
                            context!!.applicationContext,
                            R.drawable.ic_tatacara_dot_nonactive
                        )
                    )
                }
                dots[position]!!.setImageDrawable(
                    ContextCompat.getDrawable(
                        context!!.applicationContext,
                        R.drawable.ic_tatacara_dot_active
                    )
                )
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
        return rootView
    }
}