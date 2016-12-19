package com.lcarino.bucketlist.ui.inspirations;

import com.lcarino.bucketlist.manager.FireBaseDataBaseManager;
import com.lcarino.bucketlist.mvp.MvpBasePresenter;
import com.lcarino.bucketlist.ui.list.ListView;
import com.lcarino.bucketlist.ui.list.model.Inspiration;
import com.lcarino.bucketlist.ui.list.model.ListItemModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * @author Luis Carino.
 */

public class  InspirationsListPresenter extends MvpBasePresenter<ListView> {

    private final FireBaseDataBaseManager fireBaseDataBaseManager;
    private final EventBus eventBus;

    @Inject
    public InspirationsListPresenter(EventBus eventBus, FireBaseDataBaseManager fireBaseDataBaseManager) {
        this.eventBus = eventBus;
        this.fireBaseDataBaseManager = fireBaseDataBaseManager;
        this.eventBus.register(this);
    }


    public void addItem(ListItemModel item) {
        if (isViewAttached()) {
            view.showAddItem();
        }
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


}
