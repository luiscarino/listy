package com.lcarino.bucketlist.ui.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lcarino.bucketlist.R;
import com.lcarino.bucketlist.application.BucketListApplication;
import com.lcarino.bucketlist.common.BaseFragment;
import com.lcarino.bucketlist.manager.FireBaseDataBaseManager;
import com.lcarino.bucketlist.model.ui.BucketListItemViewModel;
import com.lcarino.bucketlist.ui.inspirations.InspirationsListPresenter;
import com.lcarino.bucketlist.ui.list.adapter.BucketListRecyclerAdapter;
import com.lcarino.bucketlist.ui.list.adapter.ItemTouchHelperCallback;
import com.lcarino.bucketlist.ui.list.di.ListComponent;
import com.lcarino.bucketlist.ui.list.di.ListModule;

import butterknife.BindView;

/**
 * @author Luis Carino.
 */

public class MyListFragment extends BaseFragment<ListView, InspirationsListPresenter> {

    @BindView(R.id.recyclerViewBucketList)
    RecyclerView recyclerView;
    BucketListRecyclerAdapter adapter;
    ConstraintLayout constraintLayout;

    public static MyListFragment newInstance() {
        return new MyListFragment();
    }

    @Override
    public InspirationsListPresenter createPresenter() {
        return null;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_layout_bucket_list;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListComponent listComponent = ((BucketListApplication) getActivity().getApplication()).getApplicationComponent().plus(new ListModule());
        listComponent.inject(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setUpRecyclerView();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        constraintLayout = (ConstraintLayout) view.findViewById(R.id.myConstraintLayout);
        return view;
    }

    private void setUpRecyclerView() {
        FirebaseDatabase instance = FirebaseDatabase.getInstance();
        DatabaseReference reference = instance.getReference(FireBaseDataBaseManager.LIST_ENTRIES);
        reference.keepSynced(true);
        adapter = new BucketListRecyclerAdapter(BucketListItemViewModel.class, R.layout.bucket_list_item, BucketListRecyclerAdapter.ListViewHolder.class, reference);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        ItemTouchHelperCallback itemTouchHelperCallback = new ItemTouchHelperCallback(adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemTouchHelperCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }



}
