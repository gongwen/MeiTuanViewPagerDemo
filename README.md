# MeiTuanViewPagerLibrary
通过MarqueeFactory来提供各种样式的跑马灯View，
支持自定义跑马灯ItemView

### 美团效果图
<img src="/screenshot/meituan.gif"/>

### 我的效果图
<img src="/screenshot/me.gif"/>

### 使用

#### 通过继承RVAdapter来实现ViewPager的Adapter，重写其两个方法:
###### 1.实现getLayoutId 提供item的布局
###### 2.实现onBindView，对itemView设置数据
###### 例如
```
RVAdapter<MenuItemEntity> adapter = new RVAdapter<MenuItemEntity>(getContext(),
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
 ```

#### 设置Item事件监听
```
adapter.setOnItemClickListener(new RVAdapter.OnItemClickListener<MenuItemEntity>() {
            @Override
            public void onItemClick(RecyclerViewHolder holder, int position, MenuItemEntity data) {
                Toast.makeText(MainActivity.this, position + "-->" + data.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }
```

License
--
    Copyright (C) 2016 1798550470@qq.com

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.