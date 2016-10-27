package com.lcarino.bucketlist.ui.list.di;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lcarino.bucketlist.ui.list.model.manager.ListManager;
import com.lcarino.bucketlist.ui.list.model.manager.ListManagerImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Luis Carino.
 */
@Singleton
@Module
public class ListModule {

    @Provides
    ListManager provideListManager(ListManagerImpl impl) {
        return impl;
    }

    @Provides
    DatabaseReference provideDatabaseReference() {
        return FirebaseDatabase.getInstance().getReference();
    }
}
