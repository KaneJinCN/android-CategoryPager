package cn.kanejin.view.categorypager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import cn.kanejin.view.CategoryPager;
import cn.kanejin.view.CategoryPagerAdapter;
import cn.kanejin.view.categorypager.adapter.DemoCategoryPagerAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

    }

    public void gotoSegmentedControl(View view) {
        Intent intent = new Intent(this, SegmentedControlActivity.class);

        startActivity(intent);
    }

    public void gotoCategoryPager(View view) {
        Intent intent = new Intent(this, CategoryPagerActivity.class);

        startActivity(intent);
    }
}
