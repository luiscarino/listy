package com.lcarino.bucketlist.ui.list.di;

import com.lcarino.bucketlist.di.FragmentScope;
import com.lcarino.bucketlist.ui.detail.DetailPresenter;
import com.lcarino.bucketlist.ui.list.ListFragmentPresenter;
import com.lcarino.bucketlist.ui.list.MainActivity;
import com.lcarino.bucketlist.ui.list.MyListFragment;

import dagger.Subcomponent;

/**
 * @author Luis Carino.
 */

@FragmentScope
@Subcomponent(modules = {ListModule.class})
public interface ListComponent {

    void inject(MainActivity activity);

    void inject(MyListFragment myListFragment);

    ListFragmentPresenter getListPresenter();

    DetailPresenter getDetailPresenter();
}
