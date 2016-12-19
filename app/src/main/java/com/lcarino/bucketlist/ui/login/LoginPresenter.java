package com.lcarino.bucketlist.ui.login;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.lcarino.bucketlist.manager.FireBaseAccountManager;
import com.lcarino.bucketlist.mvp.MvpBasePresenter;

import javax.inject.Inject;

import static org.greenrobot.eventbus.EventBus.TAG;

/**
 * @author Luis Carino.
 */

public class LoginPresenter extends MvpBasePresenter<LoginView> {
    private FireBaseAccountManager fireBaseAccountManager;

    @Inject
    LoginPresenter(FireBaseAccountManager fireBaseAccountManager) {
        this.fireBaseAccountManager = fireBaseAccountManager;
    }

    @Override
    public void attachView(LoginView view) {
        super.attachView(view);
    }

    @Override
    public void detachView(boolean retainState) {
        super.detachView(retainState);
    }

    void handleSignInResult(int requestCode, Intent data) {

        if (!isViewAttached()) {
            return;
        }


        view.showProgress();
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == LoginActivity.RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();
                fireBaseAccountManager.firebaseAuthWithGoogle(account, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());

                        if (!isViewAttached()) {
                            return;
                        }


                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithCredential", task.getException());
                            view.showError("Authentication failed. Please, try again.");
                        } else {
                            getView().loginSuccessful();
                        }
                    }
                });
            } else {
                // Google Sign In failed
                Log.e(TAG, "Google Sign In failed.");
                view.showError("Authentication failed. Please, try again.");
            }
        }
    }

    boolean isAuthenticated() {
        return fireBaseAccountManager.isUserAuthenticated();
    }

}
