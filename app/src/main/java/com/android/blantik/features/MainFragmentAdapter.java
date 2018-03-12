package com.android.blantik.features;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.android.blantik.features.home.ProductFragment;
import com.android.blantik.features.popular_product.PopularProductFragment;

/**
 * Created by agustinaindah on 25/07/2017.
 */

public class MainFragmentAdapter extends FragmentPagerAdapter {

    private String tabTitles[] = new String[]{"Home", "Popular Product"};

    public MainFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = ProductFragment.newInstance();
                break;
            case 1:
                fragment = PopularProductFragment.newInstance();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
