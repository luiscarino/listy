package com.lcarino.bucketlist.manager;

import com.lcarino.bucketlist.ui.list.model.Entry;

/**
 * @author Luis Carino.
 */

public interface DataBaseManager {

    void fetchInspirations();

    void fetchLists();

    void fetchListEntries();

    void insertEntry(Entry entry);

}
