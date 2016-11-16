package com.lcarino.bucketlist.ui.list.adapter;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;
import com.lcarino.bucketlist.R;
import com.lcarino.bucketlist.ui.list.model.Inspiration;

/**
 * @author Luis Carino.
 */

public class InspirationsListRecyclerAdapter extends FirebaseRecyclerAdapter<Inspiration, InspirationsListRecyclerAdapter.MessageViewHolder> {

    MessageViewHolder.ListActions listActions;

    public InspirationsListRecyclerAdapter(Class<Inspiration> modelClass, int modelLayout, Class<MessageViewHolder> viewHolderClass, Query ref) {
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
    protected void populateViewHolder(MessageViewHolder viewHolder, Inspiration model, int position) {
        viewHolder.bind(model, position);
    }

    public static class MessageViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView imageView;
        TextView textView;
        ListActions actions;
        View frameAdded;
        View buttonAdded;

        public MessageViewHolder(View v) {
            super(v);
            cardView = (CardView) v.findViewById(R.id.item_card);
            imageView = (ImageView) v.findViewById(R.id.imageView);
            textView = (TextView) v.findViewById(R.id.itemTitle);
            frameAdded = v.findViewById(R.id.frameAdded);
            buttonAdded = v.findViewById(R.id.buttonAdded);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {
                    final int centerX = imageView.getRight();
                    final int centerY = imageView.getBottom();
                    final int startRadius = 0;
                    final int endRadius = Math.max(imageView.getWidth(), imageView.getHeight());

                    Animator circularReveal = ViewAnimationUtils.createCircularReveal(frameAdded, centerX, centerY, startRadius, endRadius);
                    frameAdded.setVisibility(View.VISIBLE);

                    circularReveal.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animator) {
                        }

                        @Override
                        public void onAnimationEnd(Animator animator) {
                            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(buttonAdded, "alpha", 0.0f, 1.0f);

                            objectAnimator.addListener(new Animator.AnimatorListener() {
                                @Override
                                public void onAnimationStart(Animator animator) {

                                }

                                @Override
                                public void onAnimationEnd(Animator animator) {
                                    actions.onAddClicked(view, (Integer)cardView.getTag());
                                }

                                @Override
                                public void onAnimationCancel(Animator animator) {

                                }

                                @Override
                                public void onAnimationRepeat(Animator animator) {

                                }
                            });

                            objectAnimator.start();
                        }

                        @Override
                        public void onAnimationCancel(Animator animator) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animator) {

                        }
                    });

                    circularReveal.start();





                }
            });
        }

        public void setClickListener(ListActions listener) {
            actions = listener;
        }

        public void bind(Inspiration listItemModel, int postion) {

            if(frameAdded.getVisibility() == View.VISIBLE) {
                frameAdded.setVisibility(View.GONE);
                buttonAdded.setAlpha(0);
            }

            cardView.setTag(postion);
            textView.setText(listItemModel.title);
            Glide.with(imageView.getContext()).load(listItemModel.imageURL).into(imageView);
        }

        public interface ListActions {
            void onAddClicked(View view, int id);
        }
    }


}
