package com.lcarino.bucketlist.ui.login;

import com.lcarino.bucketlist.mvp.MvpView;

/**
 * @author Luis Carino.
 */

public interface LoginView extends MvpView {

    void showLogin();

    void showProgress();

    void showError(String message);

    void loginSuccessful();
}
