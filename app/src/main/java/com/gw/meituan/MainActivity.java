package com.gw.meituan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.gw.meituanturnpagelibrary.RVAdapter;
import com.gw.meituanturnpagelibrary.RecyclerViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DotViewPager mDotViewPager = (DotViewPager) findViewById(R.id.viewPager);
        int resId = R.mipmap.king;
        List<String> list = Arrays.asList(new String[]{"王牌产品", "实时解盘", "研究院", "低佣开户", "大参考", "视听讲堂", "财经日历", "智能选股", "今日要闻", "自选股", "沪深", "擒龙大战"});

        List<MenuItemEntity> titles = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            titles.add(new MenuItemEntity(resId, list.get(i)));
        }
        mDotViewPager.setData(titles, 8, 4);
        mDotViewPager.getAdapter().setOnItemClickListener(new RVAdapter.OnItemClickListener<MenuItemEntity>() {
            @Override
            public void onItemClick(RecyclerViewHolder holder, int position, MenuItemEntity data) {
                Toast.makeText(MainActivity.this, "position:" + position + "   title:" + data.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
