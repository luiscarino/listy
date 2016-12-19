package com.lcarino.bucketlist.ui.add;

import com.lcarino.bucketlist.manager.FireBaseDataBaseManager;
import com.lcarino.bucketlist.mvp.MvpBasePresenter;
import com.lcarino.bucketlist.ui.list.model.Entry;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

/**
 * Created by luiscarino on 11/17/16.
 */

public class AddPresenter extends MvpBasePresenter<AddView> {

    private final FireBaseDataBaseManager dataBaseManager;
    private final EventBus eventBus;

    @Inject
    public AddPresenter(FireBaseDataBaseManager dataBaseManager, EventBus eventBus) {
        this.dataBaseManager = dataBaseManager;
        this.eventBus = eventBus;
        eventBus.register(this);
    }

    public void addEntry(Entry entry) {
        dataBaseManager.insertEntry(entry);
    }

    @Subscribe
    public void onAddResult(FireBaseDataBaseManager.AddResultEvent event) {
        if (!isViewAttached()) return;

        if (event != null && event.getPayload()) {
            getView().dismissDialog();
        }
    }

}
