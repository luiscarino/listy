package com.lcarino.bucketlist.application;

import android.app.Application;

import com.lcarino.bucketlist.R;
import com.lcarino.bucketlist.di.ApplicationComponent;
import com.lcarino.bucketlist.di.ApplicationModule;
import com.lcarino.bucketlist.di.DaggerApplicationComponent;

import io.realm.Realm;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * @author Luis Carino.
 */

public class BucketListApplication extends Application {

    private ApplicationComponent applicationComponent;


    @Override
    public void onCreate() {
        super.onCreate();
        injectDependencies();
        initCalligraphy();
        initReal();
    }

    private void injectDependencies() {

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    public void initCalligraphy() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/GeosansLight.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());
    }

    private void initReal(){
        Realm.init(this);
    }
}
