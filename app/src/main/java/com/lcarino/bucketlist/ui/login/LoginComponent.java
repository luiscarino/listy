package com.lcarino.bucketlist.ui.login;

import com.lcarino.bucketlist.di.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Luis Carino.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface LoginComponent {

    void inject(LoginActivity loginActivity);

    LoginPresenter presenter();

}
