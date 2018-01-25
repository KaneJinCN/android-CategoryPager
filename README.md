# Android分类分页

## Preview 效果预览

![CategoryPager Preview 1 Gif](https://user-images.githubusercontent.com/7828293/28529849-3f91c050-70c4-11e7-8923-eaf1888b47dc.gif)
![CategoryPager Preview 2 Gif](https://user-images.githubusercontent.com/7828293/28529929-8c7b4d6e-70c4-11e7-985e-276f66e5c0db.gif)

## Usage 使用方法
1. 引用CategoryPager

    在build.gradle中添加依赖
    ```gradle
    compile 'cn.kanejin.view:category-pager:1.0.2@aar'
    ```

2. 在layout里定义CategoryPager
    ```xml
    <cn.kanejin.view.CategoryPager
        android:id="@+id/category_pager"
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:orientation="vertical">

            <RelativeLayout
                android:layout_width="match_parent"
                android:layout_height="48dp"
                android:background="@color/colorPrimary">

                <cn.kanejin.view.CategoryPagerNav
                    android:id="@+id/category_pager_nav"
                    android:layout_width="match_parent"
                    android:layout_height="match_parent"
                    android:overScrollMode="never"
                    android:layout_marginLeft="8dp"
                    android:layout_marginRight="8dp"
                    android:fadingEdge="none"
                    android:scrollbars="none" >
                </cn.kanejin.view.CategoryPagerNav>
            </RelativeLayout>

            <cn.kanejin.view.CategoryPagerContent
                android:id="@+id/category_pager_content"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:layout_alignParentBottom="true"
                android:layout_gravity="center"
                android:flipInterval="30"
                android:persistentDrawingCache="animation" />

        </LinearLayout>
    </cn.kanejin.view.CategoryPager>
    ```
    [查看完整的示例代码](https://github.com/KaneJinCN/android-CategoryPager/blob/master/demo/src/main/res/layout/activity_viewpager.xml)

3. 在Activity里设置Adapter和Listener
    ```java
    mCategoryPager = (CategoryPager) findViewById(R.id.category_pager);

    mCategoryPagerAdapter = new DemoCategoryPagerAdapter(this, this.getSupportFragmentManager());

    mCategoryPager.setAdapter(mCategoryPagerAdapter);
    ```

    [查看完整的示例代码](https://github.com/KaneJinCN/android-CategoryPager/blob/master/demo/src/main/java/cn/kanejin/view/categorypager/CategoryPagerActivity.java)


## License 许可
[MIT](https://github.com/KaneJinCN/android-CategoryPager/blob/master/LICENSE)
