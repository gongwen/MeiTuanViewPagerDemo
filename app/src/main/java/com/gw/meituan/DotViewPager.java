package com.gw.meituan;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gw.meituanturnpagelibrary.RVAdapter;
import com.gw.meituanturnpagelibrary.RecyclerViewHolder;

import java.util.List;

/**
 * Created by GongWen on 16/12/25.
 */
public class DotViewPager extends FrameLayout implements ViewPager.OnPageChangeListener {
    private float density;
    private ViewPager mViewPager;
    private LinearLayout llDot;
    private RVAdapter<MenuItemEntity> adapter;

    public DotViewPager(Context context) {
        this(context, null, 0);
    }

    public DotViewPager(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DotViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        density = getResources().getDisplayMetrics().density;
        View mView = LayoutInflater.from(context).inflate(R.layout.dot_view_pager, this, true);
        mViewPager = (ViewPager) mView.findViewById(R.id.dotViewPager);
        mViewPager.addOnPageChangeListener(this);
        llDot = (LinearLayout) mView.findViewById(R.id.llDot);
    }

    public void setData(List<MenuItemEntity> mList, int pageSize, int spanCount) {
        if (mList == null || mList.size() == 0) {
            return;
        }
        adapter = new RVAdapter<MenuItemEntity>(getContext(),
                mList, pageSize, spanCount) {
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

        };
        mViewPager.setAdapter(adapter);

        llDot.removeAllViews();
        int pageCount = ((mList.size() - 1) / pageSize + 1);
        FrameLayout.LayoutParams lp1 = (FrameLayout.LayoutParams) llDot.getLayoutParams();
        if (pageCount > 1) {
            lp1.setMargins(0, 0, 0, 0);
            for (int i = 0; i < pageCount; i++) {
                ImageView ivDot = new ImageView(getContext());
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins((int) (2.5 * density), (int) (10 * density), (int) (2.5 * density), (int) (10 * density));
                ivDot.setImageResource(R.mipmap.dot_blur);
                llDot.addView(ivDot, params);
            }
            ((ImageView) llDot.getChildAt(0)).setImageResource(R.mipmap.dot_focus);
        } else {
            lp1.setMargins(0, (int) (15 * density), 0, 0);

        }
    }

    public RVAdapter<MenuItemEntity> getAdapter() {
        return adapter;
    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < llDot.getChildCount(); i++) {
            ImageView iv = ((ImageView) llDot.getChildAt(i));
            if (i == position) {
                iv.setImageResource(R.mipmap.dot_focus);
            } else {
                iv.setImageResource(R.mipmap.dot_blur);
            }
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}