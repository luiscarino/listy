package com.lcarino.bucketlist.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Represents a list entry in Realm
 * Created by luiscarino on 2/11/17.
 */
public class ListEntry extends RealmObject {

    @PrimaryKey
    private String id;
    private boolean checked;
    private String title;
    private String description;
    private String timestamp;
    private Category category;
    private boolean deleted;

    public ListEntry() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
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

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
