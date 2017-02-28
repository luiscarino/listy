package com.lcarino.bucketlist.ui.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.lcarino.bucketlist.R;
import com.lcarino.bucketlist.application.BucketListApplication;
import com.lcarino.bucketlist.common.BaseFragment;
import com.lcarino.bucketlist.ui.detail.DetailFragment;
import com.lcarino.bucketlist.ui.inspirations.InspirationListFragment;
import com.lcarino.bucketlist.ui.list.adapter.MyDrawerAdapter;
import com.lcarino.bucketlist.ui.list.di.ListComponent;
import com.lcarino.bucketlist.ui.list.di.ListModule;

public class BucketListActivity extends AppCompatActivity implements
        InspirationListFragment.Listener,
        BaseFragment.ActivityActions,
        MyDrawerAdapter.MenuActions {

    private ActionBarDrawerToggle actionBarDrawerToggle;
    private MyDrawerAdapter adapter;
    private DrawerLayout drawerLayout;
    private RecyclerView drawerList;
    private String[] drawerTitles;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                toolbar,
                R.string.menu_opened,  /* "open drawer" description for accessibility */
                R.string.menu_closed) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (getSupportActionBar() != null) {
                    getSupportActionBar().setTitle(drawerTitles[currentFragmentPosition]);
                }
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if (getSupportActionBar() != null) {
                    getSupportActionBar().setTitle(drawerTitles[currentFragmentPosition]);
                }
            }
        };

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        drawerList = (RecyclerView) findViewById(R.id.left_drawer);
        drawerList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        drawerList.setHasFixedSize(true);
        drawerTitles = getResources().getStringArray(R.array.drawer_list_items);
        adapter = new MyDrawerAdapter(drawerTitles, this);
        drawerList.setAdapter(adapter);

        showList();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
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


        return super.onOptionsItemSelected(item);
    }

    public void showList() {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, MyListFragment.Companion.newInstance())
                .commit();
    }

    @Override
    public void setToolbarTitle(String title) {
        // implement me
    }

    @Override
    public ListComponent getListComponent() {
        return ((BucketListApplication) getApplication()).getApplicationComponent().plus(new ListModule());
    }

    int currentFragmentPosition;

    @Override
    public void onItemClicked(int adapterPosition) {
        Fragment fragment = null;
        currentFragmentPosition = adapterPosition;
        switch (currentFragmentPosition) {
            case 0:
                fragment = MyListFragment.Companion.newInstance();
                break;
            case 1:
                fragment = DetailFragment.newInstance("1");
                break;
            case 2:
                fragment = DetailFragment.newInstance("1");
                break;
            default:
                fragment = DetailFragment.newInstance("1");
        }
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();

        drawerLayout.closeDrawer(drawerList);
    }
}
