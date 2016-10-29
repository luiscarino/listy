package com.lcarino.bucketlist.ui.list.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;
import com.lcarino.bucketlist.R;
import com.lcarino.bucketlist.ui.list.model.ListItemModel;

/**
 * @author Luis Carino.
 */

public class ListRecyclerAdapter extends FirebaseRecyclerAdapter<ListItemModel, ListRecyclerAdapter.MessageViewHolder> {

    MessageViewHolder.ListActions listActions;

    public ListRecyclerAdapter(Class<ListItemModel> modelClass, int modelLayout, Class<MessageViewHolder> viewHolderClass, Query ref) {
        super(modelClass, modelLayout, viewHolderClass, ref);
    }

    public void setListClickListener(MessageViewHolder.ListActions listener) {
        listActions = listener;
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MessageViewHolder messageViewHolder = super.onCreateViewHolder(parent, viewType);
        messageViewHolder.setClickListener(listActions);
        return messageViewHolder;
    }

    @Override
    protected void populateViewHolder(MessageViewHolder viewHolder, ListItemModel model, int position) {
       // viewHolder.textView.setText(model.getDescription());
        viewHolder.bind(model);
    }

    public static class MessageViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView imageView;
        ImageButton imageButton;
        ImageView loader;
        TextView textView;
        ListActions actions;

        public MessageViewHolder(View v) {
            super(v);
            cardView = (CardView) v.findViewById(R.id.item_card);
            imageView = (ImageView) v.findViewById(R.id.imageView);
            textView = (TextView) v.findViewById(R.id.itemTitle);
            imageButton = (ImageButton) v.findViewById(R.id.addButton);
            loader = (ImageView) v.findViewById(R.id.loader);
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    loader.setVisibility(View.VISIBLE);
                    Animation animation = AnimationUtils.loadAnimation(loader.getContext(), R.anim.rotate);
                    loader.startAnimation(animation);
                    actions.onAddClicked(view, (String)cardView.getTag());
                }
            });
        }

        public void setClickListener(ListActions listener) {
            actions = listener;
        }

        public void bind(ListItemModel listItemModel) {
            textView.setText(listItemModel.getTitle());
            Glide.with(imageView.getContext()).load(listItemModel.imageUrl).into(imageView);
        }

        public interface ListActions {
            void onAddClicked(View view, String id);
        }
    }


}
