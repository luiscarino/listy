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
import com.lcarino.bucketlist.ui.list.adapter.MyRealItemTouchHelperCallback
import com.lcarino.bucketlist.ui.list.adapter.RealmListRecyclerViewAdapter
import com.lcarino.bucketlist.ui.list.di.ListModule
import com.lcarino.bucketlist.ui.list.dialog.ConfirmDialog
import io.realm.RealmResults
import kotlinx.android.synthetic.main.fragment_layout_bucket_list.*
import kotlinx.android.synthetic.main.list_empty_state.*


/**
 * Simple {@link Fragment} to display a list of items.
 */
class MyListFragment : BaseFragment<ListView, ListFragmentPresenter>(),
        ListView,
        RealmListRecyclerViewAdapter.ListItemActions,
        ConfirmDialog.ConfirmDialogActions {

    var adapter: RealmListRecyclerViewAdapter? = null
    val LAUNCH_TYPE_KEY = "launch.type"

    override fun createPresenter(): ListFragmentPresenter {
        return activityActions.listComponent.listPresenter
    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_layout_bucket_list
    }

    var launchType = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val listComponent = (activity.application as BucketListApplication).applicationComponent.plus(ListModule())
        listComponent.inject(this)
        setHasOptionsMenu(true)
        if (arguments == null) return
        if (arguments.containsKey(LAUNCH_TYPE_KEY)) {
            launchType = arguments.getInt(LAUNCH_TYPE_KEY)
        }
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(getMenuIdFOrLaunchType(launchType), menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_archive_completed -> {
                presenter.archiveCompletedItems()
            }
            R.id.action_trash_delete_all -> {
                presenter.emptyTrash()
            }
            R.id.action_archive_unarchive_all -> {
                presenter.unarchiveAll()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setViewForLaunchType()
        handleKeyboardEnter()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        presenter.attachView(this)
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        var refWatcher = BucketListApplication.getRefWatcher()
        refWatcher.watch(this)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun setViewForLaunchType() {

        when (launchType) {
            LAUNCH_MODE_LISTY -> {
                val listItems = presenter.listItems
                if (listItems.size > 0) {
                    adapter = RealmListRecyclerViewAdapter(context, listItems, this)
                } else {
                    empty_list_icon.setImageDrawable(context.getDrawable(R.drawable.ic_list))
                    empty_list_title.text = getString(R.string.empty_list_title)
                    view_switcher.showNext()
                }

            }
            LAUNCH_MODE_ARCHIVE -> {
                val archived = presenter.archived
                if (archived.size > 0) {
                    adapter = RealmListRecyclerViewAdapter(context, archived, this)

                } else {
                    empty_list_icon.setImageDrawable(context.getDrawable(R.drawable.ic_archive_black_48px))
                    empty_list_title.text = getString(R.string.empty_archive_title)
                    view_switcher.showNext()
                }
                newListItemEditText.visibility = View.GONE

            }
            LAUNCH_MODE_TRASH -> {
                val trash = presenter.trash
                if (trash.size > 0) {
                    adapter = RealmListRecyclerViewAdapter(context, trash, this)
                } else {
                    empty_list_icon.setImageDrawable(context.getDrawable(R.drawable.ic_delete_black_48px))
                    empty_list_title.text = getString(R.string.empty_trash_title)
                    view_switcher.showNext()
                }
                newListItemEditText.visibility = View.GONE
            }
        }

        recyclerView.setAdapter(adapter)
        // custom item touch helper callback.
        val touchHelper: MyRealItemTouchHelperCallback = MyRealItemTouchHelperCallback(context, adapter)
        ItemTouchHelper(touchHelper).attachToRecyclerView(recyclerView.recycleView)
    }

    private fun handleKeyboardEnter() {
        newListItemEditText.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                presenter.addItem(newListItemEditText.text.toString())
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
//        if (adapter!!.itemCount > 1)
//            recyclerView.smoothScrollToPosition(adapter!!.itemCount - 1)
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

    override fun displayAlertDialog() {
        val addDialogFragment = ConfirmDialog()
        addDialogFragment.actions = this
        addDialogFragment.show(fragmentManager, "MyListFragmentDeleteDialog")
    }

    override fun onItemChecked(id: String, checked: Boolean) {
        presenter.markAsCompleted(id, checked)
    }

    override fun onConfirm() {
        presenter.confirmDeleteTrash()
    }

    override fun onCancel() {

    }

    fun getMenuIdFOrLaunchType(launchType: Int): Int {
        when (launchType) {
            LAUNCH_MODE_LISTY -> return R.menu.menu_bucket_list
            LAUNCH_MODE_TRASH -> return R.menu.menu_trash
            LAUNCH_MODE_ARCHIVE -> return R.menu.menu_archive
            else -> throw IllegalStateException("ID not valid. Should be declared on menu file.")
        }
    }

    companion object {
        val LAUNCH_TYPE_KEY = "launch.type"
        val LAUNCH_MODE_LISTY: Int = 0
        val LAUNCH_MODE_TRASH: Int = 2
        val LAUNCH_MODE_ARCHIVE: Int = 1
        fun newInstance(): MyListFragment {
            return MyListFragment()
        }

        fun newInstanceForListy(): MyListFragment {
            return createFragmentForLaunchMode(LAUNCH_MODE_LISTY)
        }

        fun newInstanceForArchive(): MyListFragment {
            return createFragmentForLaunchMode(LAUNCH_MODE_ARCHIVE)
        }

        fun newInstanceForTrash(): MyListFragment {
            return createFragmentForLaunchMode(LAUNCH_MODE_TRASH)
        }

        private fun createFragmentForLaunchMode(launchMode: Int): MyListFragment {
            val myListFragment = MyListFragment()
            val bundle = Bundle()
            bundle.putInt(LAUNCH_TYPE_KEY, launchMode)
            myListFragment.arguments = bundle
            return myListFragment
        }
    }


}
