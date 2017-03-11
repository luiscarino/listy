package com.lcarino.bucketlist.mvp.kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Base Android MVP Activity.
 * Created by luiscarino on 3/8/17.
 */
abstract class MvpActivity<V : View, P : MvpPresenter<V>> : AppCompatActivity(), MvpPresenter<V> {

    protected var presenter: P? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = createPresenter()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDetachView() {
        presenter?.onDetachView()
    }

    abstract fun createPresenter(): P
}
