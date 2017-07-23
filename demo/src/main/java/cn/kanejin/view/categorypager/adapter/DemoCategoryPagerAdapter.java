package cn.kanejin.view.categorypager.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import cn.kanejin.view.CategoryPagerAdapter;
import cn.kanejin.view.categorypager.R;
import cn.kanejin.view.categorypager.fragment.DemoCategoryFragment;

/**
 * Created by Kane on 23/07/2017.
 */

public class DemoCategoryPagerAdapter extends CategoryPagerAdapter {

    private Context context;

    public DemoCategoryPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    private String[] titles = new String[]{"分类1","分类2分类2","分类3分类3分类3","分类4","分类5分类5","分类6","分类7","分类8"};

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public String getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public Fragment getItem(int position) {

        Fragment ft = new DemoCategoryFragment();
        Bundle b = new Bundle();
        b.putString("title", titles[position]);
        ft.setArguments(b);

        return ft;
    }

    @Nullable
    @Override
    public View getCursor(ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.cursor_nav, parent, false);
        return view;
    }

    @NotNull
    @Override
    public RadioButton getNavigatorButton(int position, ViewGroup parent) {
        RadioButton rb = (RadioButton) LayoutInflater.from(context).inflate(R.layout.radio_btn_nav, parent, false);

        rb.setText(getPageTitle(position));

        return rb;

    }
}
