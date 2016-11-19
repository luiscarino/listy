package com.lcarino.bucketlist;

import com.lcarino.bucketlist.model.FireBaseDataBaseManager;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    private FireBaseDataBaseManager fireBaseDataBaseManager;

    @Before
    public void init() {
       // fireBaseDataBaseManager = new FireBaseDataBaseManager(new DatabaseReference());
    }

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testCreate() {
       // fireBaseDataBaseManager.create(new BucketListItemViewModel(new Date(Calendar.getInstance().getTimeInMillis()), "Nota", "Titulo"));

    }
}