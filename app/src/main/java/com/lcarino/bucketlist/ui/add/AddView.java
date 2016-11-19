package com.lcarino.bucketlist.ui.add;

import com.lcarino.bucketlist.mvp.MvpView;

/**
 * Created by luiscarino on 11/17/16.
 */

public interface AddView extends MvpView {

    void showSoftInput();

    void hideSoftInput();
}
