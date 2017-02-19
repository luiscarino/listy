package com.lcarino.bucketlist.ui.list

import com.lcarino.bucketlist.model.ui.BucketListItemViewModel
import com.lcarino.bucketlist.mvp.MvpView
import com.lcarino.bucketlist.ui.list.model.Inspiration

/**
 * @author Luis Carino.
 */

interface ListView : MvpView {

    fun showProgress()

    fun hideProgress()

    fun displayListItems(items: List<Inspiration>)

    fun clearInputField()

    fun scrollToBottom()

    fun displayList(items: List<BucketListItemViewModel>)

}
