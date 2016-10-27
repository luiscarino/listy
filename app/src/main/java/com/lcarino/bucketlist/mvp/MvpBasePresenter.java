package com.lcarino.bucketlist.mvp;

import android.support.annotation.Nullable;
import android.support.annotation.UiThread;

/**
 * @author Luis Carino.
 */

public abstract class MvpBasePresenter<V extends MvpView> implements MvpPresenter<V> {

    protected V view;

    @Override
    public void attachView(V view) {
        this.view = view;
    }


    /**
     * Get the attached view. You should always call {@link #isViewAttached()} to check if the view
     * is
     * attached to avoid NullPointerExceptions.
     *
     * @return <code>null</code>, if view is not attached, otherwise the concrete view instance
     */
    @UiThread
    @Nullable
    public V getView() {
        return this.view;
    }

    /**
     * Checks if a view is attached to this getListPresenter. You should always call this method before
     * calling {@link #getView()} to get the view instance.
     */
    @UiThread
    public boolean isViewAttached() {
        return this.view != null;
    }


    /**
     * Get the attached view. You should always call {@link #isViewAttached()} to check if the view
     * is
     * attached to avoid NullPointerExceptions.
     *
     * @return <code>null</code>, if view is not attached, otherwise the concrete view instance
     */
    @Override
    public void detachView(boolean retainState) {
        this.view = null;
    }


}
