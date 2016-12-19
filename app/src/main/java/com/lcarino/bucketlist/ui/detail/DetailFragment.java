package com.lcarino.bucketlist.ui.detail;

import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.lcarino.bucketlist.R;
import com.lcarino.bucketlist.common.BaseFragment;
import com.lcarino.bucketlist.ui.list.model.ListItemModel;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Luis Carino.
 */

public class DetailFragment extends BaseFragment<DetailView, DetailPresenter>  implements DetailView {

    public static final String ITEM_ID = "item_id";
    @BindView(R.id.edit_title)
    EditText editTextTitle;
    @BindView(R.id.edit_description)
    EditText editTextDescription;
    @BindView(R.id.button_save)
    Button buttonSave;
    @BindView(R.id.imageView2)
    ImageView imageView;

    public static DetailFragment newInstance(String itemId) {
        Bundle args = new Bundle();
        args.putString(ITEM_ID, itemId);
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_detail;
    }

    @Override
    public DetailPresenter createPresenter() {
        return activityActions.getListComponent().getDetailPresenter();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.attachView(this);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Animatable) imageView.getDrawable()).start();
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detachView(false);
    }

    @Override
    public void displayImageSourceSelector() {

    }

    @Override
    public void enableSaveButton() {
        buttonSave.setEnabled(true);
    }

    @Override
    public void disableSaveButton() {
        buttonSave.setEnabled(false);
    }

    @Override
    public void create() {
        editTextTitle.getText();

    }



    @Override
    public void edit() {

    }

    @Override
    public void delete() {

    }

    @Override
    public void displayProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @OnClick(R.id.button_save)
    public void onSaveClicked() {
        save();
    }

    @Override
    public void save() {
        ListItemModel item = new ListItemModel.Builder()
                .setId(1)
                .setTitle(editTextTitle.getText().toString())
                .setDescription(editTextDescription.getText().toString())
                .build();
        presenter.save(item);
    }
}
