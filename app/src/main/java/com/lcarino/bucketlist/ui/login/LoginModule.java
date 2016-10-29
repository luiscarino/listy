package com.lcarino.bucketlist.ui.login;

import android.content.Context;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.lcarino.bucketlist.R;
import com.lcarino.bucketlist.model.FireBaseAccountManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Luis Carino.
 */

@Module
public class LoginModule {

    @Singleton
    @Provides
    GoogleSignInOptions providesGoogleSignInOptions(Context context) {
        return new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
    }

    @Singleton
    @Provides
    GoogleApiClient providesGoogleApiClient(Context context, GoogleSignInOptions googleSignInOptions) {
        return new GoogleApiClient.Builder(context)
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                .build();
    }

    @Singleton
    @Provides
    FireBaseAccountManager providesFireBaseAccountManager(GoogleSignInOptions googleSignInOptions, FirebaseAuth firebaseAuth, GoogleApiClient googleApiClient) {
        return new FireBaseAccountManager(googleSignInOptions, firebaseAuth, googleApiClient);
    }

}
