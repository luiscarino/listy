package com.lcarino.bucketlist.ui.login;

import com.google.android.gms.common.api.GoogleApiClient;

import javax.inject.Singleton;

import dagger.Subcomponent;

/**
 * Sub-component used to provided dependencies for login.
 *
 * @author Luis Carino.
 */
@Singleton
@Subcomponent(modules = LoginModule.class)
public interface LoginComponent {

    GoogleApiClient googleApiClient();

    LoginPresenter presenter();

}
