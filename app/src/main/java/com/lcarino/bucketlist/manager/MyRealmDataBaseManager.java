package com.lcarino.bucketlist.manager;

import android.util.Log;

import com.lcarino.bucketlist.model.ListEntry;

import java.util.Locale;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Handles interaction with Realm.
 *
 * @author luis carino
 */

public class MyRealmDataBaseManager implements DataBaseManager {

    private final Realm realm;

    @Inject
    public MyRealmDataBaseManager(Realm realm) {
        this.realm = realm;
    }

    @Override
    public RealmResults<ListEntry> getListEntries() {
        return realm.where(com.lcarino.bucketlist.model.ListEntry.class)
                .equalTo("deleted", false)
                .equalTo("archived", false)
                .findAll();
    }
    
    @Override
    public void add(final com.lcarino.bucketlist.model.ListEntry listEntry) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Log.d(getClass().getSimpleName(), String.format(Locale.getDefault(), "Copying object %s to realm", listEntry.toString()));
                realm.copyToRealm(listEntry);
            }
        });
    }

    @Override
    public void delete(String id) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Log.d(getClass().getSimpleName(), "Deleting item from realm");

            }
        });
    }

    @Override
    public void update(final String id, final String newValue) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                ListEntry listEntry = realm.where(ListEntry.class)
                        .equalTo("id", id)
                        .findFirst();
                listEntry.setTitle(newValue);
            }
        });
    }

    @Override
    public void undoDelete(final String itemId) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                ListEntry listEntry = realm.where(ListEntry.class)
                        .equalTo("id", itemId)
                        .findFirst();
                listEntry.setDeleted(false);
            }
        });
    }

    @Override
    public void markAsCompleted(final String id, final boolean checked) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                ListEntry listEntry = realm.where(ListEntry.class)
                        .equalTo("id", id)
                        .findFirst();
                listEntry.setChecked(checked);
            }
        });
    }

    @Override
    public void archiveCompleted() {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<ListEntry> completed = realm.where(ListEntry.class)
                        .equalTo("checked", true)
                        .findAll();
                for (ListEntry entry : completed) {
                    entry.setArchived(true);
                }
            }
        });
    }

    @Override
    public void emptyTrash() {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<ListEntry> deleted = realm.where(ListEntry.class)
                        .equalTo("deleted", true)
                        .findAll();
                deleted.deleteAllFromRealm();
            }
        });
    }

    @Override
    public void unarchiveAll() {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<ListEntry> archived = realm.where(ListEntry.class)
                        .equalTo("archived", true)
                        .equalTo("deleted", false)
                        .findAll();
                for (ListEntry entry : archived) {
                    entry.setArchived(false);
                }
            }
        });
    }

    public RealmResults<ListEntry> getArchived() {
        return realm.where(ListEntry.class)
                .equalTo("archived", true)
                .equalTo("deleted", false)
                .findAll();
    }

    public RealmResults<ListEntry> getTrash() {
        return realm.where(ListEntry.class)
                .equalTo("deleted", true)
                .findAll();
    }
}
