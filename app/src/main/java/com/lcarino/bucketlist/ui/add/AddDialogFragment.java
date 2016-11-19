package com.lcarino.bucketlist.ui.add;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;

import com.lcarino.bucketlist.R;
import com.lcarino.bucketlist.application.BucketListApplication;
import com.lcarino.bucketlist.di.ApplicationComponent;
import com.lcarino.bucketlist.ui.add.di.AddComponent;
import com.lcarino.bucketlist.ui.add.di.AddModule;
import com.lcarino.bucketlist.ui.list.model.Entry;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.lcarino.bucketlist.R.id.editText;

/**
 * @author Luis Carino.
 */

public class AddDialogFragment extends android.support.v4.app.DialogFragment implements AddView {

    private AddPresenter presenter;

    @BindView(editText)
    EditText inputText;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ApplicationComponent applicationComponent = ((BucketListApplication) getActivity().getApplication()).getApplicationComponent();
        final AddComponent addComponent = applicationComponent.plus(new AddModule());
        presenter = addComponent.getAddPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_dialog, container);
        ButterKnife.bind(this, view);
        getDialog().setCanceledOnTouchOutside(false);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        inputText.requestFocus();
        showSoftInput();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hideSoftInput();
    }

    @OnClick(R.id.buttonAdd)
    public void onClickAdd() {
        Entry.Builder builder = new Entry.Builder()
                .setTitle(inputText.getText().toString())
                .setTimestamp(getTimestamp());
        presenter.addEntry(builder.build());
    }

    @OnClick(R.id.buttonCancel)
    public void onClickCancel() {
        dismiss();
    }

    private String getTimestamp() {
        Long tsLong = System.currentTimeMillis()/1000;
        return tsLong.toString();
    }

    @Override
    public void showSoftInput() {
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }

    @Override
    public void hideSoftInput() {

    }
}
