package com.lcarino.bucketlist.ui.list;

import com.lcarino.bucketlist.ui.list.model.ListItemModel;
import com.lcarino.bucketlist.ui.list.model.manager.ListManager;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.LinkedList;

import static org.mockito.Mockito.verify;

/**
 * @author Luis Carino.
 */

public class InspirationsListPresenterTest {

    @Mock
    private ListView listView;
    @Mock
    private ListManager listManager;
    private InspirationsListPresenter inspirationsListPresenter;

    private final String title = "Title";


    @Captor
    private ArgumentCaptor<ListManager.loadBucketListCallback> loadBucketListCaptor;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        inspirationsListPresenter = new InspirationsListPresenter(listManager);
        inspirationsListPresenter.attachView(listView);
    }


    @Test
    public void clickAddNewListItemTest() {
        inspirationsListPresenter.addItem(new ListItemModel());
        verify(listView).showAddItem();
    }

    @Test
    public void fetchListItemsTest() {
        inspirationsListPresenter.getList(true);

        // verify the view show progress
        verify(listView).showProgress();

        verify(listManager).getItems(loadBucketListCaptor.capture());
        loadBucketListCaptor.getValue().onListLoaded(new LinkedList<ListItemModel>());

        verify(listView).hideProgress();
        verify(listView).displayListItems(new LinkedList<ListItemModel>());

    }
}
