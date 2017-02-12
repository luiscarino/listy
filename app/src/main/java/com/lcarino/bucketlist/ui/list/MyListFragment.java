package com.lcarino.bucketlist.ui.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lcarino.bucketlist.R;
import com.lcarino.bucketlist.application.BucketListApplication;
import com.lcarino.bucketlist.common.BaseFragment;
import com.lcarino.bucketlist.manager.FireBaseDataBaseManager;
import com.lcarino.bucketlist.model.ui.BucketListItemViewModel;
import com.lcarino.bucketlist.ui.inspirations.ListFragmentPresenter;
import com.lcarino.bucketlist.ui.list.adapter.BucketListRecyclerAdapter;
import com.lcarino.bucketlist.ui.list.adapter.ItemTouchHelperCallback;
import com.lcarino.bucketlist.ui.list.di.ListComponent;
import com.lcarino.bucketlist.ui.list.di.ListModule;
import com.lcarino.bucketlist.ui.list.model.Inspiration;
import com.lcarino.bucketlist.ui.list.model.ListItemModel;

import java.util.List;

import butterknife.BindView;

/**
 * @author Luis Carino.
 */

public class MyListFragment extends BaseFragment<ListView, ListFragmentPresenter> implements ListView {

    @BindView(R.id.recyclerViewBucketList)
    RecyclerView recyclerView;
    BucketListRecyclerAdapter adapter;
    ConstraintLayout constraintLayout;
    LinearLayoutManager layoutManager;

    public static MyListFragment newInstance() {
        return new MyListFragment();
    }

    @Override
    public ListFragmentPresenter createPresenter() {
        return activityActions.getListComponent().getListPresenter();
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
    TextView newEntryInput;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        constraintLayout = (ConstraintLayout) view.findViewById(R.id.myConstraintLayout);
        newEntryInput = (TextView) view.findViewById(R.id.newListItemEditText);
        presenter.attachView(this);
        return view;
    }

    private void setUpRecyclerView() {
        FirebaseDatabase instance = FirebaseDatabase.getInstance();
        DatabaseReference reference = instance.getReference(FireBaseDataBaseManager.LIST_ENTRIES);
        reference.keepSynced(true);
        adapter = new BucketListRecyclerAdapter(BucketListItemViewModel.class, R.layout.bucket_list_item, BucketListRecyclerAdapter.ListViewHolder.class, reference);
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        newEntryInput.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    presenter.addItem(new ListItemModel("1","",newEntryInput.getText().toString(), "", null, false));
                    return true;
                }
                return false;
            }
        });

        ItemTouchHelperCallback itemTouchHelperCallback = new ItemTouchHelperCallback(adapter, getContext());
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemTouchHelperCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

    }


    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void displayListItems(List<Inspiration> items) {

    }

    @Override
    public void clearInputField() {
        newEntryInput.getEditableText().clear();
    }

    @Override
    public void scrollToBottom() {
        // TODO: 2/8/17 Scrolling to last item is overlapped by input field
        layoutManager.scrollToPosition(adapter.getItemCount()-1);
    }
}
