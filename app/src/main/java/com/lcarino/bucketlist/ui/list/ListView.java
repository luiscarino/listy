package com.lcarino.bucketlist.ui.list;

import com.lcarino.bucketlist.ui.list.model.ListItemModel;
import com.lcarino.bucketlist.mvp.MvpView;

import java.util.List;

/**
 * @author Luis Carino.
 */

interface ListView extends MvpView {

    void showProgress();

    void hideProgress();

    void loadListItems(List<ListItemModel> items);

    void showAddItem();

}
