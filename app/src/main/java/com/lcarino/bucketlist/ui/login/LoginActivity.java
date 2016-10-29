package com.lcarino.bucketlist.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient;
import com.lcarino.bucketlist.R;
import com.lcarino.bucketlist.application.BucketListApplication;
import com.lcarino.bucketlist.mvp.MvpActivity;
import com.lcarino.bucketlist.ui.list.BucketListActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A login screen that offers login.
 */
public class LoginActivity extends MvpActivity<LoginView, LoginPresenter> implements LoginView {

    public static final int RC_SIGN_IN = 9001;
    private LoginComponent loginComponent;
    private GoogleApiClient googleApiClient;
    @BindView(R.id.login_progress)
    ProgressBar progressView;
    @BindView(R.id.email_sign_in_button)
    Button signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        loginComponent = ((BucketListApplication) getApplication())
                .getApplicationComponent().plus(new LoginModule());
        googleApiClient = loginComponent.googleApiClient();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        presenter.attachView(this);

        if (presenter.isAuthenticated()) {
            navigateToList();
        }
    }

    @OnClick(R.id.email_sign_in_button)
    void onLoginClick() {
        signIn();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView(false);
    }

    private void signIn() {
        startSigInWithGoogle();
    }

    private void startSigInWithGoogle() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.handleSignInResult(requestCode, data);
    }

    @Override
    public void showLogin() {
        progressView.setVisibility(View.GONE);
        signInButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProgress() {
        progressView.setVisibility(View.VISIBLE);
        signInButton.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginSuccessful() {
        Intent homeIntent = new Intent(this, BucketListActivity.class);
        startActivity(homeIntent);
        finish();
    }

    @Override
    public LoginPresenter createPresenter() {
        return loginComponent.presenter();
    }

    public void navigateToList() {
        Intent intent = new Intent(this, BucketListActivity.class);
        startActivity(intent);
        finish();
    }
}

