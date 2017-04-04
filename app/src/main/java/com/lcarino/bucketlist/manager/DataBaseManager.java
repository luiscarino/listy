package com.lcarino.bucketlist.manager;


import com.lcarino.bucketlist.model.ListEntry;

import io.realm.RealmResults;

/**
 * Defines actions for the data base manager.
 *
 * @author Luis Carino.
 */

public interface DataBaseManager {

    RealmResults<ListEntry> getListEntries();

    void add(ListEntry listEntry);

    void delete(String id);

    void update(String id, String newValue);

    void undoDelete(final String itemId);

    void markAsCompleted(final String id, final boolean checked);

    void archiveCompleted();

    void emptyTrash();

    void unarchiveAll();

}
