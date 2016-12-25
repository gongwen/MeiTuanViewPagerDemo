package com.gw.meituanturnpagelibrary;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GongWen on 16/12/25.
 * ViewPager + RecyclerView  实现类似美团首页翻页功能
 */

public abstract class RVAdapter<TH> extends PagerAdapter {
    private Context mContext;
    private List<RecyclerView> mRecyclerViews;
    private List<TH> dataList;
    private int pageSize;
    private int pageCount;
    private OnItemClickListener<TH> mOnItemClickListener;

    public RVAdapter(Context mContext, List<TH> dataList, int pageSize, int spanCount) {
        this.mContext = mContext;
        this.dataList = dataList;
        this.pageSize = pageSize;

        mRecyclerViews = new ArrayList<>();
        if (dataList == null || dataList.size() == 0) {
            pageCount = 0;
        } else {
            pageCount = ((dataList.size() - 1) / pageSize + 1);
        }
        for (int i = 0; i < pageCount; i++) {
            RecyclerView mRecyclerView = new RecyclerView(mContext);
            mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, spanCount));
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setAdapter(new RecyclerViewListAdapter(i));
            mRecyclerViews.add(mRecyclerView);
        }
    }

    @Override
    public int getCount() {
        return pageCount;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mRecyclerViews.get(position));
        return mRecyclerViews.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mRecyclerViews.get(position));
    }

    public interface OnItemClickListener<P> {
        void onItemClick(RecyclerViewHolder holder, int position, P data);
    }

    public void setOnItemClickListener(OnItemClickListener<TH> mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    protected abstract int getLayoutId();

    protected abstract void onBindView(RecyclerViewHolder holder, int position, TH data);

    class RecyclerViewListAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
        private List<TH> mList;
        private int pagePosition;
        private LayoutInflater inflater;

        public RecyclerViewListAdapter(int pagePosition) {
            this.pagePosition = pagePosition;
            inflater = LayoutInflater.from(mContext);
            mList = new ArrayList<>();
            int endPageSize;
            if (pagePosition == (pageCount - 1)) {
                endPageSize = (dataList.size() - 1) % pageSize + 1;
            } else {
                endPageSize = pageSize;
            }
            for (int i = pagePosition * pageSize; i < (pagePosition * pageSize + endPageSize); i++) {
                mList.add(dataList.get(i));
            }
        }

        @Override
        public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View mView = inflater.inflate(getLayoutId(), parent, false);
            return new RecyclerViewHolder(mView);
        }

        @Override
        public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {
            holder.getItemView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(holder, pageSize * pagePosition + position, mList.get(position));
                    }
                }
            });
            onBindView(holder, pageSize * pagePosition + position, mList.get(position));
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }
    }
}
