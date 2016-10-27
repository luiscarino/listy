package com.lcarino.bucketlist.application;

import android.app.Application;

import com.lcarino.bucketlist.di.ApplicationComponent;
import com.lcarino.bucketlist.di.ApplicationModule;
import com.lcarino.bucketlist.di.DaggerApplicationComponent;
import com.lcarino.bucketlist.ui.list.di.DaggerListComponent;
import com.lcarino.bucketlist.ui.list.di.ListComponent;
import com.lcarino.bucketlist.ui.list.di.ListModule;
import com.lcarino.bucketlist.ui.login.DaggerLoginComponent;
import com.lcarino.bucketlist.ui.login.LoginComponent;

import io.realm.Realm;

/**
 * @author Luis Carino.
 */

public class BucketListApplication extends Application {

    private ApplicationComponent applicationComponent;
    private LoginComponent loginComponent;
    private ListComponent listComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        injectDependencies();
    }

    private void injectDependencies() {
        ApplicationModule applicationModule = new ApplicationModule(this);
        applicationComponent = DaggerApplicationComponent.builder().applicationModule(
                applicationModule).build();

        loginComponent = DaggerLoginComponent.builder().applicationModule(applicationModule).build();

        listComponent = DaggerListComponent.builder().listModule(new ListModule()).build();

    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    public LoginComponent getLoginComponent() {
        return loginComponent;
    }

    public ListComponent getListComponent () {
        return listComponent;
    }

}
