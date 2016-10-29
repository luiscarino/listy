package com.lcarino.bucketlist.ui.list;

import com.lcarino.bucketlist.ui.list.model.ListItemModel;
import com.lcarino.bucketlist.ui.list.model.manager.ListManager;
import com.lcarino.bucketlist.mvp.MvpBasePresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * @author Luis Carino.
 */

public class ListPresenter extends MvpBasePresenter<ListView> {

    private ListManager listManager;

    @Inject
    public ListPresenter(ListManager listManager) {
        this.listManager = listManager;
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
