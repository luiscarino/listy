package com.lcarino.bucketlist.ui.list

import android.os.Bundle
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.moonmonkeylabs.realmrecyclerview.RealmRecyclerView
import com.lcarino.bucketlist.R
import com.lcarino.bucketlist.application.BucketListApplication
import com.lcarino.bucketlist.common.BaseFragment
import com.lcarino.bucketlist.model.ui.BucketListItemViewModel
import com.lcarino.bucketlist.ui.inspirations.ListFragmentPresenter
import com.lcarino.bucketlist.ui.list.adapter.DragRealItemTouchHelperCallback
import com.lcarino.bucketlist.ui.list.adapter.RealmListRecyclerViewAdapter
import com.lcarino.bucketlist.ui.list.di.ListModule
import com.lcarino.bucketlist.ui.list.model.Inspiration
import com.lcarino.bucketlist.ui.list.model.ListItemModel
import kotlinx.android.synthetic.main.fragment_layout_bucket_list.*


/**
 * Simple {@link Fragment} to display a list of items.
 */
class MyListFragment : BaseFragment<ListView, ListFragmentPresenter>(), ListView, RealmListRecyclerViewAdapter.ListItemActions {

    var recyclerView: RealmRecyclerView? = null
    var adapter: RealmListRecyclerViewAdapter? = null

    override fun createPresenter(): ListFragmentPresenter {
        return activityActions.listComponent.listPresenter
    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_layout_bucket_list
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val listComponent = (activity.application as BucketListApplication).applicationComponent.plus(ListModule())
        listComponent.inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpRecyclerView()
        handleKeyboardEnter()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        presenter.attachView(this)
        return view
    }

    private fun setUpRecyclerView() {
        adapter = RealmListRecyclerViewAdapter(context, presenter.listItems, this)
        recyclerViewBucketList.setAdapter(adapter)
        val touchHelper: DragRealItemTouchHelperCallback = DragRealItemTouchHelperCallback(context, adapter)
        ItemTouchHelper(touchHelper).attachToRecyclerView(recyclerViewBucketList.recycleView)
    }

    private fun handleKeyboardEnter() {
        newListItemEditText.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                presenter.addItem(ListItemModel("1", "", newListItemEditText.text.toString(), "", null, false))
                return@OnKeyListener true
            }
            false
        })
    }

    override fun showProgress() {

    }

    override fun hideProgress() {

    }

    override fun displayListItems(items: List<Inspiration>) {

    }

    override fun clearInputField() {
        newListItemEditText.editableText.clear()
    }

    override fun scrollToBottom() {
        // TODO: 2/8/17 Scrolling to last item is overlapped by input field
        recyclerViewBucketList.smoothScrollToPosition(adapter!!.itemCount - 1)
    }

    override fun displayList(items: List<BucketListItemViewModel>) {

    }

    override fun onEditItem(id: String, newValue: String) {
        //  presenter.updateListItem(id, newValue)
    }

    companion object {

        fun newInstance(): MyListFragment {
            return MyListFragment()
        }
    }
}
