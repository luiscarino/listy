package com.lcarino.bucketlist.ui.list.model.dao;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.lcarino.bucketlist.ui.list.model.Inspiration;

import javax.inject.Inject;

/**
 * @author Luis Carino.
 */

public class InspirationDAOImpl implements InspirationDAO {

    private static final String INSPIRATIONS = "inspirations";
    private  DatabaseReference databaseReference;

    @Inject
    public InspirationDAOImpl(DatabaseReference databaseReference) {
        this.databaseReference = databaseReference;

    }

    @Override
    public void findAll(ValueEventListener eventListener) {
        databaseReference.child(INSPIRATIONS).addListenerForSingleValueEvent(eventListener);
    }


    @Override
    public void insertInspiration(Inspiration inspiration, ValueEventListener eventListener) {
        databaseReference.child(INSPIRATIONS).push().setValue(inspiration);
        databaseReference.addValueEventListener(eventListener);
    }

    @Override
    public void findById(String id, ValueEventListener eventListener) {

    }

    @Override
    public void updateInspiration(String id, ValueEventListener eventListener) {

    }

    @Override
    public void deleteInspiration(String id, ValueEventListener eventListener) {

    }
}
