package com.lcarino.bucketlist.mvp.kotlin

import android.os.Bundle

/**
 * Created by luiscarino on 3/8/17.
 */
abstract class MvpBasePresenter<V : View> : MvpPresenter<V> {

    protected var view: View? = null

    override fun onCreateView(view: V, bundle: Bundle) {
        this.view = view
    }

    override fun onDetachView() {
        view = null
    }

    fun isViewAttached(): Boolean = view != null
}