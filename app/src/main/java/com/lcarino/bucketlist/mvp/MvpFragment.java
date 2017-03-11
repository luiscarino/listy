package com.lcarino.bucketlist.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * @author Luis Carino.
 */

public abstract class MvpFragment <V extends MvpView, P extends MvpPresenter<V>> extends Fragment {

    protected P presenter;

    /**
     * Instantiate a getListPresenter instance
     *
     * @return The {@link com.lcarino.bucketlist.mvp.kotlin.MvpPresenter} for this view
     */
    public  abstract P createPresenter();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
    }

}
