package com.lcarino.bucketlist.manager;

import com.lcarino.bucketlist.model.Account;

import rx.Subscriber;

/**
 * @author Luis Carino.
 */

public interface AccountManager {

    Subscriber<Account> doLogin();

    boolean isUserAuthenticated();
}
