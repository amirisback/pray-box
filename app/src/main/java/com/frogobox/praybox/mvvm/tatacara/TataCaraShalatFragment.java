package com.frogobox.praybox.mvvm.tatacara;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.frogobox.praybox.R;
import com.frogobox.praybox.core.BaseFragment;

public class TataCaraShalatFragment extends BaseFragment {

    // ---------------------------------------------------------------------------------------------
    private int dotscount;
    private ImageView[] dots;
    private Integer imageResId[] = {R.drawable.sholat_0, R.drawable.sholat_1, R.drawable.sholat_2,
            R.drawable.sholat_3, R.drawable.sholat_4, R.drawable.sholat_5, R.drawable.sholat_6,
            R.drawable.sholat_7, R.drawable.sholat_8, R.drawable.sholat_9, R.drawable.sholat_10};
    // ---------------------------------------------------------------------------------------------

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // -----------------------------------------------------------------------------------------
        View rootView = inflater.inflate(R.layout.fragment_tatacara_image, container, false);
        ViewPager mViewPager = rootView.findViewById(R.id.tatacara_sliderimage_viewpager);
        LinearLayout sliderDotspanel = (LinearLayout) rootView.findViewById(R.id.tatacara_sliderdots_linearLayout);
        // -----------------------------------------------------------------------------------------
        ImagePager imagePagerAdapter = new ImagePager(getContext(), imageResId);
        mViewPager.setAdapter(imagePagerAdapter);
        dotscount = imagePagerAdapter.getCount();
        dots = new ImageView[dotscount];
        // -----------------------------------------------------------------------------------------
        for (int i = 0; i < dotscount; i++) {
            // -------------------------------------------------------------------------------------
            dots[i] = new ImageView(getContext());
            dots[i].setImageDrawable(ContextCompat.getDrawable(getContext().getApplicationContext(),
                    R.drawable.ic_tatacara_dot_nonactive));
            // -------------------------------------------------------------------------------------
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(8, 0, 8, 0);
            sliderDotspanel.addView(dots[i], params);
            // -------------------------------------------------------------------------------------
        }
        // -----------------------------------------------------------------------------------------

        dots[0].setImageDrawable(ContextCompat.getDrawable(getContext().getApplicationContext(), R.drawable.ic_tatacara_dot_active));

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < dotscount; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getContext().getApplicationContext(), R.drawable.ic_tatacara_dot_nonactive));
                }
                dots[position].setImageDrawable(ContextCompat.getDrawable(getContext().getApplicationContext(), R.drawable.ic_tatacara_dot_active));
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        return rootView;
    }
}