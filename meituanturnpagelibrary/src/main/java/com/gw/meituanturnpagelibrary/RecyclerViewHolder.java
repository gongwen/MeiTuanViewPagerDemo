package com.gw.meituanturnpagelibrary;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by GongWen on 16/12/25.
 */
public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    private final SparseArray<View> mViews;
    private View mItemView;

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        this.mViews = new SparseArray<>();
        this.mItemView = itemView;
    }

    public View getItemView() {
        return mItemView;
    }

    public <T extends View> T getView(int viewId) {
        View mView = mViews.get(viewId);
        if (mView == null) {
            mView = mItemView.findViewById(viewId);
            mViews.put(viewId, mView);
        }
        return (T) mView;
    }

    public void setText(int viewId, String text) {
        ((TextView) getView(viewId)).setText(text);
    }

    public void setTextColor(int viewId, int color) {
        ((TextView) getView(viewId)).setTextColor(color);
    }

    public void setImageResource(int viewId, int resId) {
        ((ImageView) getView(viewId)).setImageResource(resId);
    }
}