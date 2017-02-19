package com.lcarino.bucketlist.ui.inspirations;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.lcarino.bucketlist.manager.FireBaseDataBaseManager;
import com.lcarino.bucketlist.manager.RealmManagerDataBaseManager;
import com.lcarino.bucketlist.model.Category;
import com.lcarino.bucketlist.model.ListEntry;
import com.lcarino.bucketlist.model.ui.BucketListItemViewModel;
import com.lcarino.bucketlist.mvp.MvpBasePresenter;
import com.lcarino.bucketlist.ui.list.ListView;
import com.lcarino.bucketlist.ui.list.model.Entry;
import com.lcarino.bucketlist.ui.list.model.Inspiration;
import com.lcarino.bucketlist.ui.list.model.ListItemModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import javax.inject.Inject;

import io.realm.RealmResults;

/**
 * @author Luis Carino.
 */

public class ListFragmentPresenter extends MvpBasePresenter<ListView> {

    private final FireBaseDataBaseManager fireBaseDataBaseManager;
    private final EventBus eventBus;
    private final RealmManagerDataBaseManager realmManagerDataBaseManager;

    @Inject
    public ListFragmentPresenter(EventBus eventBus, FireBaseDataBaseManager fireBaseDataBaseManager, RealmManagerDataBaseManager realmManagerDataBaseManager) {
        this.eventBus = eventBus;
        this.fireBaseDataBaseManager = fireBaseDataBaseManager;
        this.eventBus.register(this);
        this.realmManagerDataBaseManager = realmManagerDataBaseManager;
    }


    public void addItem(ListItemModel item) {
        fireBaseDataBaseManager.insertEntry(new Entry(false, item.imageUrl, null, item.title));
        getView().clearInputField();
        getView().scrollToBottom();

        com.lcarino.bucketlist.model.ListEntry listEntry1= new com.lcarino.bucketlist.model.ListEntry();
        listEntry1.setId(UUID.randomUUID().toString());
        listEntry1.setTitle(item.getTitle());
        listEntry1.setDescription(item.getDescription());
        listEntry1.setChecked(item.isCompleted());
        listEntry1.setTimestamp(((Long) System.currentTimeMillis()).toString());
        listEntry1.setCategory(new Category());
        realmManagerDataBaseManager.add(listEntry1);
    }


    public void getList(boolean forceRefresh) {
        view.showProgress();
        fireBaseDataBaseManager.fetchInspirations();
    }

    @Subscribe
    public void onListFetched(FireBaseDataBaseManager.InspirationsResultEvent event) {
        if(!isViewAttached()) return;

        if(event.isSuccess()) {
            ArrayList<Inspiration> inspirations = event.getPayload();
            view.displayListItems(inspirations);
        }
    }

    @Override
    public void detachView(boolean retainState) {
        super.detachView(retainState);
    }

    public void fetchListItems() {
        RealmResults<ListEntry> listEntries = realmManagerDataBaseManager.getListEntries();

        Collection<BucketListItemViewModel> transform = Collections2.transform(listEntries, new Function<ListEntry, BucketListItemViewModel>() {
            @Nullable
            @Override
            public BucketListItemViewModel apply(ListEntry input) {
                return new BucketListItemViewModel(input.getId(), input.isChecked(), input.getTitle(), input.getTimestamp());

            }
        });

        if(view != null) {
            view.displayList(new ArrayList<>(transform));
        }
    }

    public RealmResults<ListEntry> getListItems() {
        return realmManagerDataBaseManager.getListEntries();
    }



}
