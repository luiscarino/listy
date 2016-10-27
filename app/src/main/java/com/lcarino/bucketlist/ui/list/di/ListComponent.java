package com.lcarino.bucketlist.ui.list.di;

import com.lcarino.bucketlist.common.BaseActivity;
import com.lcarino.bucketlist.di.ApplicationModule;
import com.lcarino.bucketlist.ui.detail.DetailPresenter;
import com.lcarino.bucketlist.ui.list.ListPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Luis Carino.
 */

@Singleton
@Component(modules = {ApplicationModule.class, ListModule.class})
public interface ListComponent {

    void inject(BaseActivity activity);

    ListPresenter getListPresenter();

    DetailPresenter getDetailPresenter();
}
