package com.lcarino.bucketlist.ui.list.model;

import java.util.Date;

/**
 * @author Luis Carino.
 */

public class ListItemModel {

    public String id;
    public String categoryId;
    public String imageUrl;
    public String title;
    public String description;
    public Date targetDate;
    public boolean completed;

    public ListItemModel() {
    }

    public String getId() {
        return id;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(Date targetDate) {
        this.targetDate = targetDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public ListItemModel(String categoryId, String imageUrl, String title, String description, Date targetDate, boolean completed) {
        this.categoryId = categoryId;
        this.imageUrl = imageUrl;
        this.title = title;
        this.description = description;
        this.targetDate = targetDate;
        this.completed = completed;
    }

    public static class Builder {
        private int id;
        private String categoryId;
        private String imageUrl;
        private String title;
        private String description;
        private Date targetDate;
        private boolean completed;

        public Builder setCategoryId(String categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        public Builder setCompleted(boolean completed) {
            this.completed = completed;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public Builder setTargetDate(Date targetDate) {
            this.targetDate = targetDate;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public ListItemModel build() {
            return new ListItemModel(this.categoryId, this.imageUrl, this.title, this.description, this.targetDate, this.completed);
        }
    }
}
