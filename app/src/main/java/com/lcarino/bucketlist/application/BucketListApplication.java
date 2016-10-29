package com.lcarino.bucketlist.application;

import android.app.Application;

import com.lcarino.bucketlist.di.ApplicationComponent;
import com.lcarino.bucketlist.di.ApplicationModule;
import com.lcarino.bucketlist.di.DaggerApplicationComponent;

/**
 * @author Luis Carino.
 */

public class BucketListApplication extends Application {

    private ApplicationComponent applicationComponent;


    @Override
    public void onCreate() {
        super.onCreate();
        injectDependencies();
    }

    private void injectDependencies() {

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }


}
