package com.lcarino.bucketlist.model;

import com.lcarino.bucketlist.manager.FireBaseDataBaseManager;

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
        //fireBaseDataBaseManager = new FireBaseDataBaseManager(FirebaseDatabase.getInstance().getReference());
    }

    @Test
    public void testRead() {
       // fireBaseDataBaseManager.read(FireBaseDataBaseManager.INSPIRATIONS);
    }



}
