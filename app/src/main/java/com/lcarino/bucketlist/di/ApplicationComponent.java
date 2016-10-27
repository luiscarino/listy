package com.lcarino.bucketlist.di;

import com.google.android.gms.common.api.GoogleApiClient;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Luis Carino.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    GoogleApiClient googleApiClient();

}
