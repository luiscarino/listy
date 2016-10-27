package com.lcarino.bucketlist.mvp;

import android.support.annotation.UiThread;

/**
 * The base interface for mvp presenters.
 *
 * @author Luis Carino
 */

public interface MvpPresenter<V extends MvpView> {

    /**
     * Set o attach the view to its getListPresenter.
     * @param view
     */
    @UiThread
    void attachView(V view);

    /**
     * Will be called if the view has been destroyed. Typically this method will be invoked from
     * <code>Activity.detachView()</code> or <code>Fragment.onDestroyView()</code>
     */
    @UiThread
    void detachView(boolean retainState);

}
