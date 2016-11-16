package com.lcarino.bucketlist.ui.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lcarino.bucketlist.R;

/**
 * @author Luis Carino.
 */

public class AddDialogFragment extends android.support.v4.app.DialogFragment {




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_dialog, container);
        getDialog().setCanceledOnTouchOutside(true);

        return view;
    }
}
