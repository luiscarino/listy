package com.lcarino.bucketlist.ui.list.adapter

import android.content.Context
import android.text.method.KeyListener
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import com.lcarino.bucketlist.R
import com.lcarino.bucketlist.model.ListEntry
import io.realm.RealmBasedRecyclerViewAdapter
import io.realm.RealmResults
import io.realm.RealmViewHolder

/**
 * Recycler view adapter that handles interaction with Realm.
 * Created by luiscarino on 2/12/17.
 */

class RealmListRecyclerViewAdapter(
        context: Context,
        realmResults: RealmResults<ListEntry>,
        itemActions: ListItemActions) : RealmBasedRecyclerViewAdapter<ListEntry, RealmListRecyclerViewAdapter.ListViewHolder>(context, realmResults, true, true) {
    val actions: ListItemActions = itemActions

    override fun onCreateRealmViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view = inflater.inflate(R.layout.bucket_list_item, viewGroup, false)
        return ListViewHolder(view, actions)
    }

    override fun onBindRealmViewHolder(listViewHolder: ListViewHolder, i: Int) {
        listViewHolder.bind(realmResults[i])
    }

    class ListViewHolder(v: View, listener: ListItemActions) : RealmViewHolder(v) {
        val checkBox: CheckBox = v.findViewById(R.id.bucketListCheckBox) as CheckBox
        val title: EditText = v.findViewById(R.id.bucketListItemDetail) as EditText

        init {
            checkBox.setOnCheckedChangeListener { compoundButton, b ->
                if (checkBox.isChecked) {
                    val drawable = compoundButton.resources.getDrawable(R.drawable.ic_line)
                    title.setBackgroundDrawable(drawable)
                } else {
                    title.setBackgroundDrawable(null)
                }
            }

            title.tag = title.keyListener
            title.keyListener = null

            title.setOnClickListener {
                title.keyListener = title.tag as KeyListener
            }

            title.onFocusChangeListener = View.OnFocusChangeListener { view, b ->
                if (!b) {
                    listener.onEditItem(itemView.tag.toString(), title.text.toString())
                }
            }
        }

        internal fun bind(model: ListEntry) {
            itemView.tag = model.id
            title.setText(model.title)
            checkBox.isChecked = model.isChecked
        }
    }

    interface ListItemActions {
        fun onEditItem(id: String, newValue: String)
    }
}
