package com.lcarino.bucketlist.ui.list.model;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * @author Luis Carino.
 */
@IgnoreExtraProperties
public class Entry {

    public boolean completed;
    public String imageUrl;
    public String timestamp;
    public String title;

    public Entry() {
    }

    public Entry(boolean completed, String imageUrl, String timestamp, String title) {
        this.completed = completed;
        this.imageUrl = imageUrl;
        this.timestamp = timestamp;
        this.title = title;
    }

    public static class Builder {
        private boolean completed;
        private String imageUrl;
        private String timestamp;
        private String title;

        public Builder setCompleted(boolean completed) {
            this.completed = completed;
            return this;
        }

        public Builder setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public Builder setTimestamp(String timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Entry build() {
            return new Entry(completed, imageUrl, timestamp, title);
        }
    }
}
