package com.lcarino.bucketlist.ui.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.lcarino.bucketlist.R;
import com.lcarino.bucketlist.application.BucketListApplication;
import com.lcarino.bucketlist.common.BaseFragment;
import com.lcarino.bucketlist.ui.inspirations.InspirationListFragment;
import com.lcarino.bucketlist.ui.list.di.ListComponent;
import com.lcarino.bucketlist.ui.list.di.ListModule;

public class BucketListActivity extends AppCompatActivity implements
        InspirationListFragment.Listener,
        BaseFragment.ActivityActions {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        setSupportActionBar((Toolbar) findViewById(R.id.my_toolbar));
        showList();
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

    public void showList() {
    getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, MyListFragment.newInstance())
                .commit();
    }

    @Override
    public void navigateToDetailScreen(String itemId) {

    }

    @Override
    public void setToolbarTitle(String title) {

    }

    @Override
    public ListComponent getListComponent() {
        return ((BucketListApplication) getApplication()).getApplicationComponent().plus(new ListModule());
    }
}
