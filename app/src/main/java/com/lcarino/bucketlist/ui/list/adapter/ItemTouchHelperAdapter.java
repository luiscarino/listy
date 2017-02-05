package com.lcarino.bucketlist.ui.list.adapter;

/**
 * Created by luiscarino on 1/11/17.
 */

public interface ItemTouchHelperAdapter {

    void onItemMove(int fromPosition, int toPosition);

    void onItemDismiss(int position);
}
