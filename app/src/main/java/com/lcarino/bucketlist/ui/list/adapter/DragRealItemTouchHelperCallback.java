package com.lcarino.bucketlist.ui.list.adapter;

import android.support.v7.widget.RecyclerView;

import co.moonmonkeylabs.realmrecyclerview.RealmSimpleItemTouchHelperCallback;

/**
 * Created by luiscarino on 2/18/17.
 */

public class DragRealItemTouchHelperCallback extends RealmSimpleItemTouchHelperCallback {

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return true;
    }
}
