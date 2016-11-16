package com.lcarino.bucketlist.ui.list;

import com.lcarino.bucketlist.mvp.MvpView;
import com.lcarino.bucketlist.ui.list.model.Inspiration;

import java.util.List;

/**
 * @author Luis Carino.
 */

interface ListView extends MvpView {

    void showProgress();

    void hideProgress();

    void displayListItems(List<Inspiration> items);

    void showAddItem();

}
