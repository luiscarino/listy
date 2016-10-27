package com.lcarino.bucketlist.model;

import rx.Subscriber;

/**
 * @author Luis Carino.
 */

public interface AccountManager {

    Subscriber<Account> doLogin();

    boolean isUserAuthenticated();
}
