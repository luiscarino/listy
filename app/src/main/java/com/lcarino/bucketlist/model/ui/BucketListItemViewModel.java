package com.lcarino.bucketlist.model.ui;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * @author Luis Carino.
 */
@IgnoreExtraProperties
public class BucketListItemViewModel {

    public String id;
    public boolean completed;
    public String title;
    public String timestamp;

    public BucketListItemViewModel() {
    }

    public BucketListItemViewModel(String id, boolean completed, String title, String timestamp) {
        this.id = id;
        this.completed = completed;
        this.title = title;
        this.timestamp = timestamp;
    }

}
