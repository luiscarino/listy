package com.lcarino.bucketlist.ui.list.model;

/**
 * @author Luis Carino.
 */

public class Entry {

    public boolean completed;
    public String date;
    public String imageUrl;
    public String timestamp;
    public String title;

    public Entry() {
    }

    public Entry(boolean completed, String date, String imageUrl, String timestamp, String title) {
        this.completed = completed;
        this.date = date;
        this.imageUrl = imageUrl;
        this.timestamp = timestamp;
        this.title = title;
    }
}
