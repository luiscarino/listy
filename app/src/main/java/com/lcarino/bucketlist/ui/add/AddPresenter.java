package com.lcarino.bucketlist.ui.add;

import com.lcarino.bucketlist.model.FireBaseDataBaseManager;
import com.lcarino.bucketlist.mvp.MvpBasePresenter;
import com.lcarino.bucketlist.ui.list.model.Entry;

import javax.inject.Inject;

/**
 * Created by luiscarino on 11/17/16.
 */

public class AddPresenter extends MvpBasePresenter<AddView>{

    private final FireBaseDataBaseManager dataBaseManager;

    @Inject
    public AddPresenter(FireBaseDataBaseManager dataBaseManager) {
       this.dataBaseManager  = dataBaseManager;
    }

    public void addEntry(Entry.Builder entry) {
        dataBaseManager.insertEntry(entry.build());
    }

}
