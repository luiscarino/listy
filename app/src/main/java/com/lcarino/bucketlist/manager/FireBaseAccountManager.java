package com.lcarino.bucketlist.manager;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

import javax.inject.Inject;

/**
 * @author Luis Carino.
 */

public class FireBaseAccountManager implements AccountManager, GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = FireBaseAccountManager.class.getSimpleName();

    private GoogleApiClient googleApiClient;
    private FirebaseAuth firebaseAuth;

    @Inject
    public FireBaseAccountManager(GoogleSignInOptions gso, FirebaseAuth firebaseAuth, GoogleApiClient googleApiClient) {
        this.firebaseAuth = firebaseAuth;
        this.googleApiClient = googleApiClient;
    }


    public void firebaseAuthWithGoogle(GoogleSignInAccount acct, OnCompleteListener<AuthResult> listener) {
        Log.d(TAG, "firebaseAuthWithGooogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        firebaseAuth
                .signInWithCredential(credential)
                .addOnCompleteListener(listener);
    }

    @Override
    public boolean isUserAuthenticated() {
        return firebaseAuth.getCurrentUser() != null;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
