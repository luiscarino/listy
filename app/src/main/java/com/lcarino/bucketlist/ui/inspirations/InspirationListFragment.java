package com.lcarino.bucketlist.ui.inspirations;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.lcarino.bucketlist.R;
import com.lcarino.bucketlist.common.BaseFragment;
import com.lcarino.bucketlist.model.ListEntry;
import com.lcarino.bucketlist.model.ui.BucketListItemViewModel;
import com.lcarino.bucketlist.ui.inspirations.adapter.InspirationsListRecyclerAdapter;
import com.lcarino.bucketlist.ui.list.ListFragmentPresenter;
import com.lcarino.bucketlist.ui.list.ListView;
import com.lcarino.bucketlist.ui.list.model.Inspiration;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import butterknife.BindView;
import io.realm.RealmResults;

/**
 * A fragment that shows a list of bucket items.
 *
 * @author luis.carino
 */
public class InspirationListFragment extends BaseFragment<ListView, ListFragmentPresenter> implements ListView, InspirationsListRecyclerAdapter.MessageViewHolder.ListActions {

    private Listener bucketActivityActions;
    private InspirationsListRecyclerAdapter adapter;
    @BindView(R.id.myRecyclerView)
    RecyclerView recyclerView;

    public InspirationListFragment() {
    }

    public static InspirationListFragment newInstance() {
        return  new InspirationListFragment();
    }

    @Override
    public void displayList(@NotNull List<? extends BucketListItemViewModel> items) {

    }

    @Override
    public void displayAlertDialog() {
        
    }


    public interface Listener {
        void setToolbarTitle(String title);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fargment_inspirations_list;
    }

    @Override
    public ListFragmentPresenter createPresenter() {
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
            throw new IllegalStateException("Must implement InspirationListFragment.Listener");
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
        DatabaseReference reference = instance.getReference("inspirations");
        reference.keepSynced(true);
        adapter = new InspirationsListRecyclerAdapter(Inspiration.class, R.layout.list_item, InspirationsListRecyclerAdapter.MessageViewHolder.class, reference);
        adapter.setListClickListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }



    @Override
    public void clearInputField() {

    }

    @Override
    public void onAddClicked(View view, int id) {

        adapter.getRef(id).removeValue();
       // Animator animator = AnimatorInflater.loadAnimator(getContext(), R.anim.rotate);
    }

    @Override
    public void scrollToBottom() {

    }

    @Override
    public void showArchivedITems(@NotNull RealmResults<ListEntry> results) {

    }
}
