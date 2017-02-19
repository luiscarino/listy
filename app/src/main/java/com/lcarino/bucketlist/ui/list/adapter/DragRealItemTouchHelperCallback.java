package com.lcarino.bucketlist.ui.list.adapter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.lcarino.bucketlist.R;

import co.moonmonkeylabs.realmrecyclerview.RealmSimpleItemTouchHelperCallback;
import io.realm.RealmBasedRecyclerViewAdapter;

/**
 * Extension of {@link RealmSimpleItemTouchHelperCallback} that enables dragging item on RecyclerView.
 * As well as setting custom background on swipe to delete.
 * Created by luiscarino on 2/18/17.
 */

public class DragRealItemTouchHelperCallback extends RealmSimpleItemTouchHelperCallback {

    private Drawable background;
    private Drawable icon;

    public DragRealItemTouchHelperCallback(Context context, RealmBasedRecyclerViewAdapter adapter) {
        setAdapter(adapter);
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
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        super.onSwiped(viewHolder, direction);
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
        int iconTop = itemView.getTop() + (itemHeight - intrinsicHeight) / 2;
        int iconBottom = iconTop + intrinsicHeight;
        icon.setBounds(iconLeft, iconTop, iconRight, iconBottom);
        icon.draw(c);

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }
}
