package com.lcarino.bucketlist.ui.inspirations;

import com.lcarino.bucketlist.manager.MyRealmDataBaseManager;
import com.lcarino.bucketlist.model.Category;
import com.lcarino.bucketlist.model.ListEntry;
import com.lcarino.bucketlist.mvp.MvpBasePresenter;
import com.lcarino.bucketlist.ui.list.ListView;
import com.lcarino.bucketlist.ui.list.model.ListItemModel;

import org.greenrobot.eventbus.EventBus;

import java.util.Date;
import java.util.UUID;

import javax.inject.Inject;

import io.realm.RealmResults;

/**
 * @author Luis Carino.
 */

public class ListFragmentPresenter extends MvpBasePresenter<ListView> {

    private final EventBus eventBus;
    private final MyRealmDataBaseManager myRealmDataBaseManager;

    @Inject
    public ListFragmentPresenter(EventBus eventBus, MyRealmDataBaseManager myRealmDataBaseManager) {
        this.eventBus = eventBus;
        this.myRealmDataBaseManager = myRealmDataBaseManager;
    }


    public void addItem(String title) {
        com.lcarino.bucketlist.model.ListEntry listEntry1 = new com.lcarino.bucketlist.model.ListEntry();
        listEntry1.setId(UUID.randomUUID().toString());
        listEntry1.setTitle(title);
        listEntry1.setDescription(null);
        listEntry1.setChecked(false);
        listEntry1.setTimestamp(new Date(System.currentTimeMillis()).toString());
        listEntry1.setCategory(new Category());
        myRealmDataBaseManager.add(listEntry1);
    }

    public void addItem(ListItemModel item) {
        getView().clearInputField();
        getView().scrollToBottom();
        // FIXME: 2/21/17

    }

    @Override
    public void detachView(boolean retainState) {
        super.detachView(retainState);
    }


    public RealmResults<ListEntry> getListItems() {
        return myRealmDataBaseManager.getListEntries();
    }

    public void updateListItem(String id, String newValue) {
        myRealmDataBaseManager.update(id, newValue);
    }

    public void undoDelete(String itemId) {
        myRealmDataBaseManager.undoDelete(itemId);
    }

    public void markAsCompleted(String id, boolean checked) {
        myRealmDataBaseManager.markAsCompleted(id, checked);
    }

    public void archiveCompletedItems() {
        myRealmDataBaseManager.archiveCompleted();
    }

    public void showArchivedItems() {
        view.showArchivedITems(myRealmDataBaseManager.getArchived());
    }


}
