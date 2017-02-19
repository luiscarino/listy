package com.lcarino.bucketlist.ui.list.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.lcarino.bucketlist.R;
import com.lcarino.bucketlist.model.ListEntry;

import io.realm.RealmBasedRecyclerViewAdapter;
import io.realm.RealmResults;
import io.realm.RealmViewHolder;

/**
 * Recycler view adapter that uses handles interaction with Realm.
 * Created by luiscarino on 2/12/17.
 */

public class RealmListRecyclerViewAdapter extends RealmBasedRecyclerViewAdapter<ListEntry, RealmListRecyclerViewAdapter.ListViewHolder> {


    public RealmListRecyclerViewAdapter(
            Context context,
            RealmResults<ListEntry> realmResults) {
        super(context, realmResults, true, true);
    }

    @Override
    public ListViewHolder onCreateRealmViewHolder(ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.bucket_list_item, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindRealmViewHolder(ListViewHolder listViewHolder, int i) {
        listViewHolder.bind(realmResults.get(i));
    }

    public static class ListViewHolder extends RealmViewHolder {
        private CheckBox checkBox;
        private TextView title;

        public ListViewHolder(View v) {
            super(v);
            checkBox = (CheckBox) v.findViewById(R.id.bucketListCheckBox);
            title = (TextView) v.findViewById(R.id.bucketListItemDetail);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (compoundButton.isChecked()) {
                        Drawable drawable = compoundButton.getResources().getDrawable(R.drawable.ic_line);
                        title.setBackgroundDrawable(drawable);
                    } else {
                        title.setBackgroundDrawable(null);
                    }
                }
            });
        }

        void bind(ListEntry model) {
            title.setText(model.getTitle());
            //  checkBox.setTag(model.getId());
            checkBox.setChecked(model.isChecked());
        }
    }
}
