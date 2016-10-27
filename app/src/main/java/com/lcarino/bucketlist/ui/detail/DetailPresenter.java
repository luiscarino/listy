package com.lcarino.bucketlist.ui.detail;

import com.lcarino.bucketlist.model.FireBaseDataBaseManager;
import com.lcarino.bucketlist.mvp.MvpBasePresenter;
import com.lcarino.bucketlist.ui.list.model.ListItemModel;

import javax.inject.Inject;

/**
 * @author Luis Carino.
 */

public class DetailPresenter extends MvpBasePresenter<DetailView> {

    private FireBaseDataBaseManager dataBaseManager;

    @Inject
    public DetailPresenter(FireBaseDataBaseManager dataBaseManager) {
        this.dataBaseManager = dataBaseManager;
    }


    public void fetchItem(String itemId) {

    }


    public void save(ListItemModel listItemModel) {
       dataBaseManager.create(listItemModel);
    }

    public void delete () {

    }
}
