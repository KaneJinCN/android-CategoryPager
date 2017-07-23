package cn.kanejin.view.categorypager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.kanejin.view.CategoryPager;
import cn.kanejin.view.CategoryPagerAdapter;
import cn.kanejin.view.categorypager.adapter.DemoCategoryPagerAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        buildCategoryPager();
    }

    private CategoryPager mCategoryPager;
    private CategoryPagerAdapter mCategoryPagerAdapter;

    private void buildCategoryPager() {
        mCategoryPager = (CategoryPager) findViewById(R.id.category_pager);

        mCategoryPagerAdapter = new DemoCategoryPagerAdapter(this, this.getSupportFragmentManager());

        mCategoryPager.setAdapter(mCategoryPagerAdapter);
    }
}
