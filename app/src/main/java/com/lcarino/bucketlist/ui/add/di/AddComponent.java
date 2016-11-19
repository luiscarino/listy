package com.lcarino.bucketlist.ui.add.di;

import com.lcarino.bucketlist.di.FragmentScope;
import com.lcarino.bucketlist.ui.add.AddPresenter;
import com.lcarino.bucketlist.ui.list.di.ListModule;

import dagger.Subcomponent;

/**
 * Created by luiscarino on 11/17/16.
 */
@FragmentScope
@Subcomponent(modules = {AddModule.class, ListModule.class})
public interface AddComponent {

    AddPresenter getAddPresenter();
}
