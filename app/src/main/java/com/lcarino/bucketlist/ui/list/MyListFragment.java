package com.lcarino.bucketlist.ui.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lcarino.bucketlist.R;
import com.lcarino.bucketlist.common.BaseFragment;
import com.lcarino.bucketlist.model.FireBaseDataBaseManager;
import com.lcarino.bucketlist.model.ui.BucketListItemViewModel;
import com.lcarino.bucketlist.ui.add.AddDialogFragment;
import com.lcarino.bucketlist.ui.add.adapter.BucketListRecyclerAdapter;

import butterknife.BindView;

/**
 * @author Luis Carino.
 */

public class MyListFragment extends BaseFragment<ListView, InspirationsListPresenter> {


    @BindView(R.id.recyclerViewBucketList)
    RecyclerView recyclerView;
    BucketListRecyclerAdapter adapter;

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
        setHasOptionsMenu(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setUpRecyclerView();
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.menu_list_fragment, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add :
                AddDialogFragment addDialogFragment = new AddDialogFragment();
                addDialogFragment.show(getFragmentManager(), AddDialogFragment.class.getSimpleName());
                return  true;
        }

        return false;
    }
}
