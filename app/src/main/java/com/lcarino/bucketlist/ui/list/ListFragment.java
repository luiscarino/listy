package com.lcarino.bucketlist.ui.list;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lcarino.bucketlist.R;
import com.lcarino.bucketlist.common.BaseFragment;
import com.lcarino.bucketlist.ui.list.adapter.ListRecyclerAdapter;
import com.lcarino.bucketlist.ui.list.model.ListItemModel;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A fragment that shows a list of bucket items.
 *
 * @author luis.carino
 */
public class ListFragment extends BaseFragment<ListView, ListPresenter> implements ListView {

    private Listener bucketActivityActions;
    private ListRecyclerAdapter adapter;
    @BindView(R.id.my_recycler_view)
    RecyclerView recyclerView;

    public ListFragment() {
    }

    public interface Listener {
        void navigateToDetailScreen(String itemId);

        void setToolbarTitle(String title);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_bucket_list;
    }

    @Override
    public ListPresenter createPresenter() {
        return activityActions.getListComponent().getListPresenter();
    }

    private String getTitle() {
        return getString(R.string.bucket_list_fragment_title);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Listener) {
            bucketActivityActions = (Listener) context;
        } else {
            throw new IllegalStateException("Must implement ListFragment.Listener");
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bucketActivityActions.setToolbarTitle(getTitle());
        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        FirebaseDatabase instance = FirebaseDatabase.getInstance();
        DatabaseReference reference = instance.getReference("items");
        reference.keepSynced(true);
        adapter = new ListRecyclerAdapter(ListItemModel.class, R.layout.list_item, ListRecyclerAdapter.MessageViewHolder.class, reference);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    @OnClick(R.id.fab)
    public void addNote() {
        bucketActivityActions.navigateToDetailScreen("id");
        //adapter.addItem(new ListItemModel());
    }


    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void loadListItems(List<ListItemModel> items) {
        //adapter.addAll(items);
    }

    @Override
    public void showAddItem() {

    }
}
