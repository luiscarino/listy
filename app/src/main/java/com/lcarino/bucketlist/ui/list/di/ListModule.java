package com.lcarino.bucketlist.ui.list.di;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lcarino.bucketlist.di.FragmentScope;


import dagger.Module;
import dagger.Provides;

/**
 * @author Luis Carino.
 */

@Module
public class ListModule {

    @Provides
    @FragmentScope
    DatabaseReference provideDatabaseReference() {
        return FirebaseDatabase.getInstance().getReference();
    }
}
