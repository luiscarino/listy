package com.lcarino.bucketlist.ui.list.di;

import com.lcarino.bucketlist.di.FragmentScope;
import com.lcarino.bucketlist.ui.detail.DetailPresenter;
import com.lcarino.bucketlist.ui.list.ListPresenter;

import dagger.Subcomponent;

/**
 * @author Luis Carino.
 */

@FragmentScope
@Subcomponent(modules = {ListModule.class})
public interface ListComponent {

    ListPresenter getListPresenter();

    DetailPresenter getDetailPresenter();
}
