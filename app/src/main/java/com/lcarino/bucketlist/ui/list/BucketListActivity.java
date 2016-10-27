package com.lcarino.bucketlist.ui.list;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.lcarino.bucketlist.R;
import com.lcarino.bucketlist.common.BaseActivity;
import com.lcarino.bucketlist.mvp.MvpPresenter;
import com.lcarino.bucketlist.ui.detail.DetailFragment;

import butterknife.BindView;

public class BucketListActivity extends BaseActivity implements
        ListFragment.Listener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new ListFragment())
                    .commit();
        }

    }

    @Override
    public Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_bucket_list;
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
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, DetailFragment.newInstance(itemId))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void setToolbarTitle(String title) {

    }

}
