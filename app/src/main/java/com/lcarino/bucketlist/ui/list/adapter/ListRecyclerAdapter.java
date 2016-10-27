package com.lcarino.bucketlist.ui.list.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;
import com.lcarino.bucketlist.R;
import com.lcarino.bucketlist.ui.list.model.ListItemModel;

/**
 * @author Luis Carino.
 */

public class ListRecyclerAdapter extends FirebaseRecyclerAdapter<ListItemModel, ListRecyclerAdapter.MessageViewHolder> {

    public ListRecyclerAdapter(Class<ListItemModel> modelClass, int modelLayout, Class<MessageViewHolder> viewHolderClass, Query ref) {
        super(modelClass, modelLayout, viewHolderClass, ref);
    }

    @Override
    protected void populateViewHolder(MessageViewHolder viewHolder, ListItemModel model, int position) {
        viewHolder.textView.setText(model.getDescription());
    }

    public static class MessageViewHolder extends RecyclerView.ViewHolder {

        CheckBox item;
        TextView textView;
        public MessageViewHolder(View v) {
            super(v);
            item = (CheckBox) v.findViewById(R.id.checkBoxItem);
            textView = (TextView) v.findViewById(R.id.textView);
        }
    }


}
