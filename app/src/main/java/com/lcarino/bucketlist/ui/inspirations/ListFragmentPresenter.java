package com.lcarino.bucketlist.ui.inspirations;

import com.lcarino.bucketlist.manager.RealmManagerDataBaseManager;
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
    private final RealmManagerDataBaseManager realmManagerDataBaseManager;

    @Inject
    public ListFragmentPresenter(EventBus eventBus, RealmManagerDataBaseManager realmManagerDataBaseManager) {
        this.eventBus = eventBus;
        this.realmManagerDataBaseManager = realmManagerDataBaseManager;
    }


    public void addItem(ListItemModel item) {
        getView().clearInputField();
        getView().scrollToBottom();
        // FIXME: 2/21/17
        com.lcarino.bucketlist.model.ListEntry listEntry1= new com.lcarino.bucketlist.model.ListEntry();
        listEntry1.setId(UUID.randomUUID().toString());
        listEntry1.setTitle(item.getTitle());
        listEntry1.setDescription(item.getDescription());
        listEntry1.setChecked(item.isCompleted());
        listEntry1.setTimestamp(new Date(System.currentTimeMillis()).toString());
        listEntry1.setCategory(new Category());
        realmManagerDataBaseManager.add(listEntry1);
    }
    
    @Override
    public void detachView(boolean retainState) {
        super.detachView(retainState);
    }



    public RealmResults<ListEntry> getListItems() {
        return realmManagerDataBaseManager.getListEntries();
    }

    public void updateListItem(String id, String newValue) {
        realmManagerDataBaseManager.update(id, newValue);
    }

    public void undoDelete(String itemId) {
        realmManagerDataBaseManager.undoDelete(itemId);
    }

}
