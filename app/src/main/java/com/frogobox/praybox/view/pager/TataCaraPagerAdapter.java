package com.frogobox.praybox.view.pager;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.frogobox.praybox.ui.fragment.TataCaraDoaFragment;
import com.frogobox.praybox.ui.fragment.TataCaraNiatFragment;
import com.frogobox.praybox.ui.fragment.TataCaraShalatFragment;
import com.frogobox.praybox.ui.fragment.TataCaraWudhuFragment;

import com.frogobox.praybox.R;

/**
 * Created by ikhsan ramadhan on 3/18/2018.
 */

public class TataCaraPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public TataCaraPagerAdapter(Context context, FragmentManager fm ) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 :
                return new TataCaraWudhuFragment();
            case 1 :
                return new TataCaraNiatFragment();
            case 2 :
                return new TataCaraShalatFragment();
            case 3 :
                return new TataCaraDoaFragment();
            default:
                throw new IllegalArgumentException();
        }

    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0 :
                return mContext.getString(R.string.btn_tutor_wudhu);
            case 1 :
                return mContext.getString(R.string.btn_niat_sholat);
            case 2 :
                return mContext.getString(R.string.btn_tutor_sholat);
            case 3 :
                return mContext.getString(R.string.btn_doa);
            default:
                return null;
        }

    }
}
