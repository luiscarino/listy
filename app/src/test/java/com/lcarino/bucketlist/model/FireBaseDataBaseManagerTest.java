package com.lcarino.bucketlist.model;

import com.google.firebase.database.FirebaseDatabase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Luis Carino.
 */

@RunWith(MockitoJUnitRunner.class)
public class FireBaseDataBaseManagerTest {

    private FireBaseDataBaseManager fireBaseDataBaseManager;

    @Before
    public void init() throws Exception {
        fireBaseDataBaseManager = new FireBaseDataBaseManager(FirebaseDatabase.getInstance().getReference());
    }

    @Test
    public void testRead() {
        fireBaseDataBaseManager.read(FireBaseDataBaseManager.INSPIRATIONS);
    }



}
