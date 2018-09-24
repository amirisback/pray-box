package org.d3ifcool.jagosholat.Controller.TataCaraFragmentContent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import org.d3ifcool.jagosholat.Controller.ViewPagerAdapter.ImagePagerAdapter;
import org.d3ifcool.jagosholat.R;


public class TataCaraShalatFragment extends Fragment {

    // ---------------------------------------------------------------------------------------------
    private int dotscount;
    private ImageView[] dots;
    private Integer imageResId[] = {R.drawable.sholat_0, R.drawable.sholat_1,R.drawable.sholat_2,
            R.drawable.sholat_3, R.drawable.sholat_4, R.drawable.sholat_5, R.drawable.sholat_6,
            R.drawable.sholat_7, R.drawable.sholat_8, R.drawable.sholat_9, R.drawable.sholat_10};
    // ---------------------------------------------------------------------------------------------


    public TataCaraShalatFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        // -----------------------------------------------------------------------------------------
        View rootView = inflater.inflate(R.layout.fragment_tatacara_image, container, false);
        ViewPager viewPager = (ViewPager) rootView.findViewById(R.id.SliderImage);
        LinearLayout sliderDotspanel = (LinearLayout) rootView.findViewById(R.id.SliderDots);
        // -----------------------------------------------------------------------------------------

        // -----------------------------------------------------------------------------------------
        ImagePagerAdapter imagePagerAdapter = new ImagePagerAdapter(getContext(), imageResId);
        viewPager.setAdapter(imagePagerAdapter);
        dotscount = imagePagerAdapter.getCount();
        dots = new ImageView[dotscount];
        // -----------------------------------------------------------------------------------------

        // -----------------------------------------------------------------------------------------
        for(int i = 0; i < dotscount; i++){
            // -------------------------------------------------------------------------------------
            dots[i] = new ImageView(getContext());
            dots[i].setImageDrawable(ContextCompat.getDrawable(getContext().getApplicationContext(),
                    R.drawable.dot_nonactive));
            // -------------------------------------------------------------------------------------
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(8, 0, 8, 0);
            sliderDotspanel.addView(dots[i], params);
            // -------------------------------------------------------------------------------------
        }
        // -----------------------------------------------------------------------------------------

        dots[0].setImageDrawable(ContextCompat.getDrawable(getContext().getApplicationContext(), R.drawable.dot_active));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for(int i = 0; i< dotscount; i++){
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getContext().getApplicationContext(), R.drawable.dot_nonactive));
                }
                dots[position].setImageDrawable(ContextCompat.getDrawable(getContext().getApplicationContext(), R.drawable.dot_active));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        return rootView;
    }

}
