package com.lcarino.bucketlist.model;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.lcarino.bucketlist.ui.list.model.ListItemModel;

import javax.inject.Inject;

import static android.content.ContentValues.TAG;

/**
 * A simple manager to handle transactions with Fire Base DB.
 * @author Luis Carino.
 */

public class FireBaseDataBaseManager {

    /// CRUD Create Read Update Delete

    private DatabaseReference databaseReference;
    public static final String ITEMS_CHILD = "items";

    @Inject
    public FireBaseDataBaseManager(DatabaseReference databaseReference) {
        this.databaseReference = databaseReference;
    }

    public void create(ListItemModel item) {
        databaseReference.child(ITEMS_CHILD).push().setValue(item);
        read();
    }

    public void read() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ListItemModel value = dataSnapshot.getValue(ListItemModel.class);
                Log.d(TAG, value.toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, databaseError.getMessage());
            }
        });
    }


    public void update(String id, Object object) {

    }


    public void delete(String id) {

    }
}
