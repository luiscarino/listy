package com.lcarino.bucketlist.model;

import com.lcarino.bucketlist.ui.list.model.Entry;

/**
 * @author Luis Carino.
 */

public interface DataBaseManager {

    void fetchInspirations();

    void fetchLists();

    void fetchListEntries();

    boolean insertEntry(Entry entry);

}
