package com.lcarino.bucketlist.model;

import io.realm.RealmObject;

/**
 * Created by luiscarino on 2/11/17.
 */
public class Category extends RealmObject {

    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
