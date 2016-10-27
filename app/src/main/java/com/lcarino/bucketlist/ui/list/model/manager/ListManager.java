package com.lcarino.bucketlist.ui.list.model.manager;

import com.lcarino.bucketlist.ui.list.model.ListItemModel;

import java.util.List;

/**
 * @author Luis Carino.
 */

public interface ListManager {

    void getItems(loadBucketListCallback callback);

    void getItem(String itemId, getListItemCallback callback);

    void saveItem(ListItemModel listItemModel);

    void removeItem(String itemId);

    void updateItem(String itemId, ListItemModel newItem);

    interface loadBucketListCallback {
        void onListLoaded(List<ListItemModel> items);
    }

    interface getListItemCallback {
        void onListItemLoaded(ListItemModel item);
    }
}
