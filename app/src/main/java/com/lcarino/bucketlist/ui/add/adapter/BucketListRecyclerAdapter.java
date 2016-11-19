package com.lcarino.bucketlist.ui.add.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;
import com.lcarino.bucketlist.R;
import com.lcarino.bucketlist.model.ui.BucketListItemViewModel;

/**
 * Created by luiscarino on 11/18/16.
 */

public class BucketListRecyclerAdapter extends FirebaseRecyclerAdapter<BucketListItemViewModel, BucketListRecyclerAdapter.ListViewHolder> {

    public BucketListRecyclerAdapter(Class<BucketListItemViewModel> modelClass, int modelLayout, Class<ListViewHolder> viewHolderClass, Query ref) {
        super(modelClass, modelLayout, viewHolderClass, ref);
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    protected void populateViewHolder(ListViewHolder viewHolder, BucketListItemViewModel model, int position) {
        viewHolder.bind(model);
    }

    public static class ListViewHolder extends RecyclerView.ViewHolder {
        private CheckBox checkBox;
        public ListViewHolder(View v) {
            super(v);
            checkBox = (CheckBox) v.findViewById(R.id.bucketListCheckBox);
        }

        void bind(BucketListItemViewModel model) {
            checkBox.setText(model.title);
            checkBox.setTag(model.id);
            checkBox.setChecked(model.completed);
        }
    }

}
