package com.lcarino.bucketlist.common;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.lcarino.bucketlist.application.BucketListApplication;
import com.lcarino.bucketlist.mvp.MvpActivity;
import com.lcarino.bucketlist.ui.list.di.ListComponent;
import com.lcarino.bucketlist.ui.list.di.ListModule;

import butterknife.ButterKnife;

/**
 * @author Luis Carino.
 */

public abstract class BaseActivity extends MvpActivity implements BaseFragment.ActivityActions {

    private ListComponent listComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(getLayoutResource());
        ButterKnife.bind(this);
        listComponent = ((BucketListApplication) getApplication()).getApplicationComponent().plus(new ListModule());
        super.onCreate(savedInstanceState);
    }

    public abstract Toolbar getToolbar();

    @LayoutRes
    public  abstract int getLayoutResource();

    @Override
    public void setToolbarTitle(String title) {
        //no-imp by default
    }

    @Override
    public ListComponent getListComponent() {
        return listComponent;
    }

}
