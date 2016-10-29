package com.lcarino.bucketlist.ui.list.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lcarino.bucketlist.ui.list.InspirationListFragment;
import com.lcarino.bucketlist.ui.list.MyListFragment;

/**
 * Simple tabs fragment pager adapter.
 *
 * @author Luis Carino.
 */

public class TabsViewPagerAdapter extends FragmentPagerAdapter {

    private final int PAGE_COUNT = 2;
    private final String[] titles = {"My List","Inspiration"};

    public TabsViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0) {
            return MyListFragment.newInstance();
        } else {
            return InspirationListFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
