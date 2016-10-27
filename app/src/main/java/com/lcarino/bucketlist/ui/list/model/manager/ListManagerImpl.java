package com.lcarino.bucketlist.ui.list.model.manager;

import com.lcarino.bucketlist.ui.list.model.ListItemModel;

import javax.inject.Inject;

import io.realm.Realm;

/**
 * Concrete implementation of the bucket list manager that uses realm.
 * @author Luis Carino.
 */

public class ListManagerImpl implements ListManager {

    private Realm realm;

    @Inject
    public ListManagerImpl() {
        realm = Realm.getDefaultInstance();
    }

    @Override
    public void getItem(final String itemId, getListItemCallback callback) {

    }

    @Override
    public void getItems(loadBucketListCallback callback) {

    }

    @Override
    public void saveItem(ListItemModel listItemModel) {

    }

    //    @Override
//    public void getItems(loadBucketListCallback callback) {
//        RealmResults<ListItemModel> itemModels = realm.where(ListItemModel.class).findAll();
//        callback.onListLoaded(itemModels);
//    }
//
//    @Override
//    public void saveItem(final ListItemModel listItemModel) {
//        realm.executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                ListItemModel itemModel = realm.createObject(ListItemModel.class);
//                itemModel.id = listItemModel.id;
//                itemModel.title = listItemModel.title;
//                itemModel.description = listItemModel.description;
//            }
//        });
//    }

    @Override
    public void removeItem(String itemId) {

    }

    @Override
    public void updateItem(String itemId, ListItemModel newItem) {

    }
}
