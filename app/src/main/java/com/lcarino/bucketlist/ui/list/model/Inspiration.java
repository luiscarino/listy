package com.lcarino.bucketlist.ui.list.model;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * @author Luis Carino.
 */

@IgnoreExtraProperties
public class Inspiration {

    public String id;
    public String title;
    public String imageURL;

    public Inspiration() {
    }

    public Inspiration(String id, String title, String imageURL) {
        this.id = id;
        this.title = title;
        this.imageURL = imageURL;
    }

}
