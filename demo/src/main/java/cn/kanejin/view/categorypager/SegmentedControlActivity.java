package cn.kanejin.view.categorypager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cn.kanejin.view.CategoryPager;
import cn.kanejin.view.CategoryPagerAdapter;
import cn.kanejin.view.categorypager.adapter.DemoCategoryPagerAdapter;
import cn.kanejin.view.categorypager.adapter.SegmentedControlAdapter;

public class SegmentedControlActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_segmented);

        buildCategoryPager();
    }

    private CategoryPager mCategoryPager;
    private CategoryPagerAdapter mCategoryPagerAdapter;

    private void buildCategoryPager() {
        mCategoryPager = (CategoryPager) findViewById(R.id.segmented_control);

        mCategoryPagerAdapter = new SegmentedControlAdapter(this, this.getSupportFragmentManager());

        mCategoryPager.setAdapter(mCategoryPagerAdapter);
    }
}
