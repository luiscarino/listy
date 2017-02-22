package com.lcarino.bucketlist.ui.list

import com.lcarino.bucketlist.model.ui.BucketListItemViewModel
import com.lcarino.bucketlist.mvp.MvpView

/**
 * Defines list view actions.
 * @author Luis Carino.
 */

interface ListView : MvpView {

    fun clearInputField()

    fun scrollToBottom()

    fun displayList(items: List<BucketListItemViewModel>)

}
