package com.lcarino.bucketlist.manager;


import com.lcarino.bucketlist.model.ListEntry;
import com.lcarino.bucketlist.ui.list.model.Entry;

import io.realm.RealmResults;

/**
 * @author Luis Carino.
 */

public interface DataBaseManager {


    RealmResults<ListEntry> getListEntries();

    void insertEntry(Entry entry);

    void add(com.lcarino.bucketlist.model.ListEntry listEntry);

    void delete(String id);

    void update(String id, String newValue);

}
