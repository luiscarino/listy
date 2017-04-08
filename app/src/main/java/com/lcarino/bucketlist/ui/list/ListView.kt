package com.lcarino.bucketlist.ui.list

import com.lcarino.bucketlist.model.ListEntry
import com.lcarino.bucketlist.model.ui.BucketListItemViewModel
import com.lcarino.bucketlist.mvp.MvpView
import io.realm.RealmResults

/**
 * Defines list view actions.
 * @author Luis Carino.
 */

interface ListView : MvpView {

    fun clearInputField()

    fun scrollToBottom()

    fun displayList(items: List<BucketListItemViewModel>)

    fun showArchivedITems(results: RealmResults<ListEntry>)

    fun displayAlertDialog()

}
