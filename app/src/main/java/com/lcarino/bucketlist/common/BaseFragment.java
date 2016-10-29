package com.lcarino.bucketlist.common;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lcarino.bucketlist.mvp.MvpPresenter;
import com.lcarino.bucketlist.mvp.MvpView;
import com.lcarino.bucketlist.ui.list.di.ListComponent;

import butterknife.ButterKnife;

/**
 * Base fragment that uses MVP.
 *
 * @author Luis Carino.
 */

public abstract class BaseFragment<V extends MvpView, P extends MvpPresenter<V>> extends Fragment {

    protected P presenter;

    /**
     * Instantiate a getListPresenter instance
     *
     * @return The {@link MvpPresenter} for this view
     */
    public  abstract P createPresenter();

    protected ActivityActions activityActions;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof ActivityActions) {
            activityActions = (ActivityActions) context;
        } else {
            throw new IllegalStateException("Activity must implement activity actions");
       }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutRes(), container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @LayoutRes
    public abstract int getLayoutRes();

    public interface ActivityActions {

        void setToolbarTitle(String title);

        ListComponent getListComponent();
    }
}
