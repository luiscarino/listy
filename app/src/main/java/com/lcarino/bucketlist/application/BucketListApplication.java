package com.lcarino.bucketlist.application;

import android.app.Application;

import com.lcarino.bucketlist.BuildConfig;
import com.lcarino.bucketlist.R;
import com.lcarino.bucketlist.di.ApplicationComponent;
import com.lcarino.bucketlist.di.ApplicationModule;
import com.lcarino.bucketlist.di.DaggerApplicationComponent;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import io.realm.Realm;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * @author Luis Carino.
 */

public class BucketListApplication extends Application {

    public static final String FONTS_GEOSANS_LIGHT_TTF_PATH = "fonts/GeosansLight.ttf";
    private ApplicationComponent applicationComponent;
    private static RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        injectDependencies();
        initCalligraphy();
        initReal();
        if (BuildConfig.INIT_LEAK_CANARY) initLeakCanary();
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
                .setDefaultFontPath(FONTS_GEOSANS_LIGHT_TTF_PATH)
                .setFontAttrId(R.attr.fontPath)
                .build());
    }

    private void initReal() {
        Realm.init(this);
    }

    private void initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        refWatcher = LeakCanary.install(this);
    }

    public static RefWatcher getRefWatcher() {
        return refWatcher;
    }
}
