package com.lcarino.bucketlist.di;

import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Luis Carino.
 */
@Module
public class ApplicationModule {

    private Context context;

    public ApplicationModule(Context context) {
        this.context = context;
    }

    @Singleton
    @Provides
    Context provideContext() {
        return this.context;
    }

    @Singleton
    @Provides
    FirebaseAuth providesFireBaseAuth() {
        return FirebaseAuth.getInstance();
    }

    @Singleton
    @Provides
    EventBus providesEventBus() {
        return EventBus.getDefault();
    }

}
