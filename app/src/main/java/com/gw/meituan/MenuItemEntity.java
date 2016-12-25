package com.gw.meituan;

/**
 * Created by GongWen on 16/12/25.
 */

public class MenuItemEntity {
    private int resId;
    private String title;

    public MenuItemEntity(int resId, String title) {
        this.resId = resId;
        this.title = title;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
