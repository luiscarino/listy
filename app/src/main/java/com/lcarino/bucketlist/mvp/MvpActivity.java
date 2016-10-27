package com.lcarino.bucketlist.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * @author Luis Carino.
 */

public abstract class MvpActivity<V extends MvpView, P extends MvpPresenter<V>> extends AppCompatActivity {

    protected P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
    }

    /**
     * Instantiate a getListPresenter instance
     *
     * @return The {@link MvpPresenter} for this view
     */
    public  abstract P createPresenter();
}
