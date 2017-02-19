package com.lcarino.bucketlist.manager;


import com.lcarino.bucketlist.model.List;
import com.lcarino.bucketlist.model.ListEntry;
import com.lcarino.bucketlist.ui.list.model.Entry;

import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * @author Luis Carino.
 */

public interface DataBaseManager {

    void addList(List list);

    void removeList(List list);

    RealmList<List> fetchLists();

    RealmResults<ListEntry> getListEntries();

    void insertEntry(Entry entry);

    void add(com.lcarino.bucketlist.model.ListEntry listEntry);

    void remove(ListEntry listEntry);

    void update(String id, String newValue);

}
