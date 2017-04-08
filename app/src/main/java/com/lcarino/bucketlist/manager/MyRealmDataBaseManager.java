package com.lcarino.bucketlist.manager;

import android.support.annotation.StringDef;
import android.util.Log;

import com.lcarino.bucketlist.model.ListEntry;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Locale;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmResults;

import static com.lcarino.bucketlist.manager.MyRealmDataBaseManager.COLUMNS.ARCHIVED;
import static com.lcarino.bucketlist.manager.MyRealmDataBaseManager.COLUMNS.CHECKED;
import static com.lcarino.bucketlist.manager.MyRealmDataBaseManager.COLUMNS.DELETED;
import static com.lcarino.bucketlist.manager.MyRealmDataBaseManager.COLUMNS.ID;

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
                .equalTo(DELETED, false)
                .equalTo(ARCHIVED, false)
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
                        .equalTo(ID, id)
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
                        .equalTo(ID, itemId)
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
                        .equalTo(ID, id)
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
                        .equalTo(CHECKED, true)
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
                        .equalTo(DELETED, true)
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
                        .equalTo(ARCHIVED, true)
                        .equalTo(DELETED, false)
                        .findAll();
                for (ListEntry entry : archived) {
                    entry.setArchived(false);
                }
            }
        });
    }

    public RealmResults<ListEntry> getArchived() {
        return realm.where(ListEntry.class)
                .equalTo(ARCHIVED, true)
                .equalTo(DELETED, false)
                .findAll();
    }

    public RealmResults<ListEntry> getTrash() {
        return realm.where(ListEntry.class)
                .equalTo(DELETED, true)
                .findAll();
    }

    @Retention(RetentionPolicy.RUNTIME)
    @StringDef({ID, CHECKED, ARCHIVED, DELETED})
    public @interface COLUMNS {
        String ID = "id";
        String CHECKED = "checked";
        String ARCHIVED = "archived";
        String DELETED = "deleted";
    }
}
