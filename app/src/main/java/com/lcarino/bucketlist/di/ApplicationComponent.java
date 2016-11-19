package com.lcarino.bucketlist.di;

import com.lcarino.bucketlist.ui.add.di.AddComponent;
import com.lcarino.bucketlist.ui.add.di.AddModule;
import com.lcarino.bucketlist.ui.list.di.ListComponent;
import com.lcarino.bucketlist.ui.list.di.ListModule;
import com.lcarino.bucketlist.ui.login.LoginComponent;
import com.lcarino.bucketlist.ui.login.LoginModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Luis Carino.
 */
@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    LoginComponent plus(LoginModule loginModule);

    ListComponent plus(ListModule listModule);

    AddComponent plus(AddModule addComponent);

}
