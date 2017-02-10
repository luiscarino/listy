package com.lcarino.bucketlist.ui.list.di;

import com.lcarino.bucketlist.di.FragmentScope;
import com.lcarino.bucketlist.ui.detail.DetailPresenter;
import com.lcarino.bucketlist.ui.inspirations.ListFragmentPresenter;
import com.lcarino.bucketlist.ui.list.BucketListActivity;
import com.lcarino.bucketlist.ui.list.MyListFragment;

import dagger.Subcomponent;

/**
 * @author Luis Carino.
 */

@FragmentScope
@Subcomponent(modules = {ListModule.class})
public interface ListComponent {

    void inject(BucketListActivity activity);

    void inject(MyListFragment myListFragment);

    ListFragmentPresenter getListPresenter();

    DetailPresenter getDetailPresenter();
}
