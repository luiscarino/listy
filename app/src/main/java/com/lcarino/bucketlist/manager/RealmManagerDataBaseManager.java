package com.lcarino.bucketlist.manager;

import android.util.Log;

import com.lcarino.bucketlist.model.List;
import com.lcarino.bucketlist.model.ListEntry;
import com.lcarino.bucketlist.ui.list.model.Entry;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by luiscarino on 2/11/17.
 */

public class RealmManagerDataBaseManager implements DataBaseManager {

    private final Realm realm;

    @Inject
    public RealmManagerDataBaseManager(Realm realm) {
        this.realm = realm;
    }


    @Override
    public void addList(List list) {

    }

    @Override
    public void removeList(List list) {

    }

    @Override
    public RealmList<List> fetchLists() {
        return null;
    }

    @Override
    public RealmResults<ListEntry> getListEntries() {
        return realm.where(com.lcarino.bucketlist.model.ListEntry.class).findAll();
    }

    @Override
    public void insertEntry(final Entry entry) {

    }

    @Override
    public void add(final com.lcarino.bucketlist.model.ListEntry listEntry) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(listEntry);
            }
        });

        realm.beginTransaction();
        RealmResults<com.lcarino.bucketlist.model.ListEntry> all = realm.where(com.lcarino.bucketlist.model.ListEntry.class).findAll();
        Log.d("TAF::::::", String.format("Size %d", all.size()));
        realm.commitTransaction();
    }

    @Override
    public void remove(ListEntry listEntry) {

    }

    @Override
    public void update(final String id, final String newValue) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                ListEntry listEntry = realm.where(ListEntry.class).equalTo("id", id).findFirst();
                listEntry.setTitle(newValue);
            }
        });
    }
}
