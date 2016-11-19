package com.lcarino.bucketlist.ui.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.lcarino.bucketlist.R;
import com.lcarino.bucketlist.common.BaseFragment;
import com.lcarino.bucketlist.ui.add.AddDialogFragment;

/**
 * @author Luis Carino.
 */

public class MyListFragment extends BaseFragment<ListView, InspirationsListPresenter> {


    public static MyListFragment newInstance() {
        return new MyListFragment();
    }

    @Override
    public InspirationsListPresenter createPresenter() {
        return null;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_layout_bucket_list;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.menu_list_fragment, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add :
                AddDialogFragment addDialogFragment = new AddDialogFragment();
                addDialogFragment.show(getFragmentManager(), AddDialogFragment.class.getSimpleName());
                return  true;
        }

        return false;
    }
}
