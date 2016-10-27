package com.lcarino.bucketlist.model.ui;

import java.util.Date;

/**
 * @author Luis Carino.
 */

public class LitsItemViewModel {

    private String title;
    private String description;
    private Date date;

    public LitsItemViewModel(Date date, String description, String title) {
        this.date = date;
        this.description = description;
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }
}
