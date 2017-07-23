package cn.kanejin.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

/**
 * Created by Kane on 20/07/2017.
 */

public class CategoryPager extends FrameLayout {

    public CategoryPager(Context context) {
        super(context);
    }

    public CategoryPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CategoryPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CategoryPagerNav getNavigator() {
        return (CategoryPagerNav) findViewById(R.id.category_pager_nav);
    }

    public CategoryPagerContent getPager() {
        return (CategoryPagerContent) findViewById(R.id.category_pager_content);
    }


    public void setAdapter(CategoryPagerAdapter adapter) {
        buildNav(adapter);
        buildPager(adapter);
    }

    private void buildNav(CategoryPagerAdapter adapter) {
        CategoryPagerNav nav = getNavigator();

        if (nav == null) {
            return;
        }

        nav.setAdapter(adapter);

        nav.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                CategoryPagerContent pager = getPager();

                if (pager != null)
                    pager.setCurrentItem(checkedId);
            }
        });
    }

    private void buildPager(CategoryPagerAdapter adapter) {
        CategoryPagerContent pager = getPager();

        if (pager == null)
            return;

        pager.setAdapter(adapter);

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                CategoryPagerNav nav = getNavigator();

                if (nav != null) {
                    nav.setCurrentPosition(position);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });
    }
}
