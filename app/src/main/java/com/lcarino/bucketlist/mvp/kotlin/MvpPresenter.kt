package com.lcarino.bucketlist.mvp.kotlin

import android.os.Bundle
import android.support.annotation.Nullable

/**
 * Base interface for MVP presenters.
 * Created by luiscarino on 3/8/17.
 */
interface MvpPresenter<V : View> {
    fun onCreateView(view: V, @Nullable bundle: Bundle)
    fun onResume()
    fun onSaveInstanceState()
    fun onPause()
    fun onDetachView()
}