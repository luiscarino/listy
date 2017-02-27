package com.lcarino.bucketlist.ui.list

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.*
import com.lcarino.bucketlist.R
import com.lcarino.bucketlist.application.BucketListApplication
import com.lcarino.bucketlist.common.BaseFragment
import com.lcarino.bucketlist.model.ListEntry
import com.lcarino.bucketlist.model.ui.BucketListItemViewModel
import com.lcarino.bucketlist.ui.inspirations.ListFragmentPresenter
import com.lcarino.bucketlist.ui.list.adapter.MyRealItemTouchHelperCallback
import com.lcarino.bucketlist.ui.list.adapter.RealmListRecyclerViewAdapter
import com.lcarino.bucketlist.ui.list.di.ListModule
import com.lcarino.bucketlist.ui.list.model.ListItemModel
import io.realm.RealmResults
import kotlinx.android.synthetic.main.fragment_layout_bucket_list.*


/**
 * Simple {@link Fragment} to display a list of items.
 */
class MyListFragment : BaseFragment<ListView, ListFragmentPresenter>(), ListView, RealmListRecyclerViewAdapter.ListItemActions {

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
        setHasOptionsMenu(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {
            R.id.action_archive_completed -> {
                presenter.archiveCompletedItems()
            }
            R.id.action_show_archived -> {
                presenter.showArchivedItems()
            }
        }

        return super.onOptionsItemSelected(item)
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
        recyclerView.setAdapter(adapter)
        // custom item touch helper callback.
        val touchHelper: MyRealItemTouchHelperCallback = MyRealItemTouchHelperCallback(context, adapter)
        ItemTouchHelper(touchHelper).attachToRecyclerView(recyclerView.recycleView)
    }

    private fun handleKeyboardEnter() {
        newListItemEditText.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                // FIXME : change this just pass the string presenter should be in charge of building the model.
                presenter.addItem(ListItemModel("1", "", newListItemEditText.text.toString(), "", null, false))
                return@OnKeyListener true
            }
            false
        })
    }

    override fun clearInputField() {
        newListItemEditText.editableText.clear()
    }

    override fun scrollToBottom() {
        // TODO: 2/8/17 Scrolling to last item is overlapped by input field
        if (adapter!!.itemCount > 1)
            recyclerView.smoothScrollToPosition(adapter!!.itemCount - 1)
    }

    override fun displayList(items: List<BucketListItemViewModel>) {

    }

    override fun showArchivedITems(results: RealmResults<ListEntry>) {
        adapter?.updateRealmResults(results)
    }

    override fun onEditItem(id: String, newValue: String) {
        presenter.updateListItem(id, newValue)
    }

    override fun onItemDeleted(itemId: String) {
        Snackbar.make(recyclerView, getString(R.string.snack_item_deleted_msg), Snackbar.LENGTH_LONG)
                .setAction(getString(R.string.snack_undo_delete), View.OnClickListener {
                    presenter.undoDelete(itemId)
                    adapter?.notifyDataSetChanged()
                })
                .show()
    }

    override fun onItemChecked(id: String, checked: Boolean) {
        presenter.markAsCompleted(id, checked)
    }

    companion object {
        fun newInstance(): MyListFragment {
            return MyListFragment()
        }
    }
}
