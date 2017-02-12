package com.lcarino.bucketlist.ui.list.adapter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.lcarino.bucketlist.R;

/**
 * Simple item touch helper callback to use with {@link RecyclerView}
 * Created by luiscarino on 1/11/17.
 */

public class ItemTouchHelperCallback extends ItemTouchHelper.Callback {

    private final ItemTouchHelperAdapter adapter;
    private Drawable background;
    private Drawable icon;


    public ItemTouchHelperCallback(ItemTouchHelperAdapter adapter, Context context) {
        this.adapter = adapter;
        background = new ColorDrawable(context.getResources().getColor(R.color.colorPrimaryLight));
        icon = context.getDrawable(R.drawable.ic_delete_sweep_white_24px);
    }


    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipeFlags = ItemTouchHelper.START;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        adapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        adapter.onItemDismiss(viewHolder.getAdapterPosition());
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

        View itemView = viewHolder.itemView;

        // draw background
        background.setBounds(itemView.getRight() + (int) dX, itemView.getTop(), itemView.getRight(), itemView.getBottom());
        background.draw(c);

        // draw icon
        int itemHeight = itemView.getBottom() - itemView.getTop();
        int intrinsicWidth = icon.getIntrinsicWidth();
        int intrinsicHeight = icon.getIntrinsicWidth();

        int iconLeft = itemView.getRight() - 48 - intrinsicWidth;
        int iconRight = itemView.getRight() - 48;
        int iconTop = itemView.getTop() + (itemHeight - intrinsicHeight)/2;
        int iconBottom = iconTop + intrinsicHeight;
        icon.setBounds(iconLeft, iconTop, iconRight, iconBottom);
        icon.draw(c);

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }
}
