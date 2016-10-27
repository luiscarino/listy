package com.lcarino.bucketlist.di;

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
@Singleton
@Module
public class ApplicationModule {

    private Context context;

    public ApplicationModule(Context context) {
        this.context = context;
    }

    @Provides
    Context provideContext() {
        return this.context;
    }


    @Provides
    GoogleSignInOptions providesGoogleSignInOptions() {
        return new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
    }

    @Provides
    GoogleApiClient providesGoogleApiClient(GoogleSignInOptions googleSignInOptions) {
        return new GoogleApiClient.Builder(context)
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                .build();
    }


    @Provides
    FirebaseAuth providesFireBaseAuth() {
        return FirebaseAuth.getInstance();
    }

    @Provides
    FireBaseAccountManager providesFireBaseAccountManager(GoogleSignInOptions googleSignInOptions, FirebaseAuth firebaseAuth, GoogleApiClient googleApiClient ) {
        return new FireBaseAccountManager(googleSignInOptions, firebaseAuth, googleApiClient);
    }



}
