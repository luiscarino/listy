package com.lcarino.bucketlist.ui.detail;

import com.lcarino.bucketlist.mvp.MvpView;

/**
 * @author Luis Carino.
 */

interface DetailView extends MvpView {

    void displayImageSourceSelector();

    void enableSaveButton();

    void disableSaveButton();

    void create();

    void save();

    void edit();

    void delete();

    void displayProgress();

    void hideProgress();

}
