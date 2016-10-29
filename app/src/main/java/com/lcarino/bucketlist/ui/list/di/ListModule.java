package com.lcarino.bucketlist.ui.list.di;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lcarino.bucketlist.di.FragmentScope;
import com.lcarino.bucketlist.ui.list.model.manager.ListManager;
import com.lcarino.bucketlist.ui.list.model.manager.ListManagerImpl;

import dagger.Module;
import dagger.Provides;

/**
 * @author Luis Carino.
 */

@Module
public class ListModule {

    @Provides
    @FragmentScope
    ListManager provideListManager(ListManagerImpl impl) {
        return impl;
    }

    @Provides
    @FragmentScope
    DatabaseReference provideDatabaseReference() {
        return FirebaseDatabase.getInstance().getReference();
    }
}
