package com.lcarino.bucketlist.ui.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lcarino.bucketlist.R;
import com.lcarino.bucketlist.application.BucketListApplication;
import com.lcarino.bucketlist.common.BaseFragment;
import com.lcarino.bucketlist.model.FireBaseDataBaseManager;
import com.lcarino.bucketlist.model.ui.BucketListItemViewModel;
import com.lcarino.bucketlist.ui.add.AddDialogFragment;
import com.lcarino.bucketlist.ui.add.adapter.BucketListRecyclerAdapter;
import com.lcarino.bucketlist.ui.list.di.ListComponent;
import com.lcarino.bucketlist.ui.list.di.ListModule;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * @author Luis Carino.
 */

public class MyListFragment extends BaseFragment<ListView, InspirationsListPresenter> {

    @BindView(R.id.recyclerViewBucketList)
    RecyclerView recyclerView;
    BucketListRecyclerAdapter adapter;
    @Inject
    EventBus eventBus;

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

    @Override
    public void onResume() {
        super.onResume();
        eventBus.register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        eventBus.unregister(this);
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
    }

    @Subscribe
    public void onClickEventReceived(BucketListActivity.AddEvent event){
        displayAddDialog();
    }

    private void displayAddDialog() {
        AddDialogFragment addDialogFragment = new AddDialogFragment();
        addDialogFragment.show(getFragmentManager(), AddDialogFragment.class.getSimpleName());
    }
}
