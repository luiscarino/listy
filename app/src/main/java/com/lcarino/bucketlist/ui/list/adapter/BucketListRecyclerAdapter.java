package com.lcarino.bucketlist.ui.list.adapter;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;
import com.lcarino.bucketlist.R;
import com.lcarino.bucketlist.model.ui.BucketListItemViewModel;

/**
 * Recycler View Adapter to bind data in {@link com.lcarino.bucketlist.ui.add.AddDialogFragment}
 *
 * @author luiscarino.
 */

public class BucketListRecyclerAdapter extends FirebaseRecyclerAdapter<BucketListItemViewModel, BucketListRecyclerAdapter.ListViewHolder> implements ItemTouchHelperAdapter {

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

        void bind(BucketListItemViewModel model) {
            title.setText(model.title);
            checkBox.setTag(model.id);
            checkBox.setChecked(model.completed);
        }
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {

    }

    @Override
    public void onItemDismiss(int position) {
        getRef(position).removeValue();
    }
}
