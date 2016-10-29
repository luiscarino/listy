package com.lcarino.bucketlist.ui.list;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.lcarino.bucketlist.R;
import com.lcarino.bucketlist.common.BaseActivity;
import com.lcarino.bucketlist.mvp.MvpPresenter;
import com.lcarino.bucketlist.ui.detail.DetailFragment;

public class BucketListActivity extends BaseActivity implements
        ListFragment.Listener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setText("My List"));
        tabLayout.addTab(tabLayout.newTab().setText("Inspiration"));

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        FragmentPagerAdapter pagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

            final int NUM_PAGES = 2;
            @Override
            public Fragment getItem(int position) {
                if(position == 0 ) {
                    return new ListFragment();
                } else {
                    return new DetailFragment();
                }

            }

            @Override
            public int getCount() {
                return NUM_PAGES;
            }
        };
        viewPager.setAdapter(pagerAdapter);

    }

    @Override
    public Toolbar getToolbar() {
        return null;
    }

    @Override
    public int getLayoutResource() {
        return R.layout.content_main;
    }

    @Override
    public MvpPresenter createPresenter() {
        return null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bucket_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void navigateToDetailScreen(String itemId) {
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.fragment_container, DetailFragment.newInstance(itemId))
//                .addToBackStack(null)
//                .commit();
    }

    @Override
    public void setToolbarTitle(String title) {

    }


}
