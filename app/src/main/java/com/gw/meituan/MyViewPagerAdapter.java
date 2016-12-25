package com.gw.meituan;

import android.content.Context;
import android.widget.TextView;

import com.gw.meituanturnpagelibrary.RVAdapter;
import com.gw.meituanturnpagelibrary.RecyclerViewHolder;

import java.util.List;

/**
 * Created by GongWen on 16/12/25.
 */

public class MyViewPagerAdapter extends RVAdapter<MenuItemEntity> {
    public MyViewPagerAdapter(Context mContext, List<MenuItemEntity> dataList, int pageSize, int spanCount) {
        super(mContext, dataList, pageSize, spanCount);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dot_view_pager_item;
    }

    @Override
    protected void onBindView(RecyclerViewHolder holder, int position, MenuItemEntity data) {
        TextView tv = holder.getView(R.id.tv);
        tv.setText(data.getTitle());
        tv.setCompoundDrawablesWithIntrinsicBounds(0, data.getResId(), 0, 0);

    }
}
