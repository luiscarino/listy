package com.lcarino.bucketlist.model;

import java.io.Serializable;

/**
 * @author Luis Carino.
 */

public class Account implements Serializable {

    private String username;
    private String email;
    private String name;

    public Account(String username, String email, String name) {
        this.username = username;
        this.email = email;
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
