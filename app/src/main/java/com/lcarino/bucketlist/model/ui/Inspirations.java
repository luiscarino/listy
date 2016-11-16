package com.lcarino.bucketlist.model.ui;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * @author Luis Carino.
 */

@IgnoreExtraProperties
public class Inspirations {

    public String id;
    public String title;
    public String imageUrl;
    public boolean inList;

    public Inspirations() {
       //required by  DataSnapshot.getValue(Inspirations.class)
    }


}
