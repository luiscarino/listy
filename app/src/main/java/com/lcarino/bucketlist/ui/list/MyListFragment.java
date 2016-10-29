package com.lcarino.bucketlist.ui.list;

import com.lcarino.bucketlist.R;
import com.lcarino.bucketlist.common.BaseFragment;

/**
 * @author Luis Carino.
 */

public class MyListFragment extends BaseFragment<ListView, ListPresenter> {


    public static MyListFragment newInstance() {
        return new MyListFragment();
    }

    @Override
    public ListPresenter createPresenter() {
        return null;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_bucket_list;
    }
}
