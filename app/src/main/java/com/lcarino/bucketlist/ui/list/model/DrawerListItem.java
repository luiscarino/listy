package com.lcarino.bucketlist.ui.list.model;

import android.support.annotation.DrawableRes;

/**
 * Represents an item on the menu drawer.
 * Created by luiscarino on 3/1/17.
 */
public class DrawerListItem {
    @DrawableRes
    int icon;
    private String text;
    private boolean checked;

    public DrawerListItem(int icon, String text, boolean checked) {
        this.icon = icon;
        this.text = text;
        this.checked = checked;
    }


    public int getIcon() {
        return icon;
    }

    public String getText() {
        return text;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
