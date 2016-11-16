package com.lcarino.bucketlist.ui.list;

import com.lcarino.bucketlist.model.FireBaseDataBaseManager;
import com.lcarino.bucketlist.mvp.MvpBasePresenter;
import com.lcarino.bucketlist.ui.list.model.ListItemModel;
import com.lcarino.bucketlist.ui.list.model.manager.ListManager;

import java.util.List;

import javax.inject.Inject;

/**
 * @author Luis Carino.
 */

public class InspirationsListPresenter extends MvpBasePresenter<ListView> {

    private FireBaseDataBaseManager fireBaseDataBaseManager;

    @Inject
    public InspirationsListPresenter(FireBaseDataBaseManager fireBaseDataBaseManager) {
        this.fireBaseDataBaseManager = fireBaseDataBaseManager;
    }

    @Override
    public void attachView(ListView view) {
        super.attachView(view);
    }



    public void addItem(ListItemModel item) {
        if(isViewAttached()) {
            view.showAddItem();
        }
    }


    public void getList(boolean forceRefresh) {
        if(isViewAttached()) {
            view.showProgress();
            listManager.getItems(new ListManager.loadBucketListCallback() {
                @Override
                public void onListLoaded(List<ListItemModel> items) {
                    view.hideProgress();
                    view.displayListItems(items);
                }
            });
            view.showAddItem();
        }



    }

    @Override
    public void detachView(boolean retainState) {
        super.detachView(retainState);
    }


}
