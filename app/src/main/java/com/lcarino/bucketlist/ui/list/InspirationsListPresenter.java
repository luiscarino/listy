package com.lcarino.bucketlist.ui.list;

import com.lcarino.bucketlist.model.FireBaseDataBaseManager;
import com.lcarino.bucketlist.mvp.MvpBasePresenter;
import com.lcarino.bucketlist.ui.list.model.Inspiration;
import com.lcarino.bucketlist.ui.list.model.ListItemModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * @author Luis Carino.
 */

public class InspirationsListPresenter extends MvpBasePresenter<ListView> {

    private final FireBaseDataBaseManager fireBaseDataBaseManager;
    private final EventBus eventBus;

    @Inject
    public InspirationsListPresenter(EventBus eventBus, FireBaseDataBaseManager fireBaseDataBaseManager) {
        this.eventBus = eventBus;
        this.fireBaseDataBaseManager = fireBaseDataBaseManager;
        this.eventBus.register(this);
    }

    @Override
    public void attachView(ListView view) {
        super.attachView(view);
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
            ArrayList<Inspiration> inspirations = event.getInspirations();
            view.displayListItems(inspirations);
        }
    }

    @Override
    public void detachView(boolean retainState) {
        super.detachView(retainState);
    }


}
