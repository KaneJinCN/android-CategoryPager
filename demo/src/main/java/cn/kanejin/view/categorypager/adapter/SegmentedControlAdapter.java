package cn.kanejin.view.categorypager.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import cn.kanejin.view.CategoryPagerAdapter;
import cn.kanejin.view.categorypager.R;
import cn.kanejin.view.categorypager.fragment.DemoCategoryFragment;

/**
 * Created by Kane on 24/07/2017.
 */

public class SegmentedControlAdapter extends CategoryPagerAdapter {

    private Context mContext;

    public SegmentedControlAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.mContext = context;
    }

    private String[] titles = new String[]{"分段1","分段2"};

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

    @Override
    public View getCursor(ViewGroup parent) {
        return null;
    }

    @Override
    public RadioButton getNavigatorButton(int position, ViewGroup parent) {
        RadioButton rb = (RadioButton) LayoutInflater.from(mContext).inflate(R.layout.radio_btn_segmented, parent, false);

        if (position == 0) {
            rb.setBackgroundResource(R.drawable.segmented_control_first);
        } else if (position == getCount() - 1) {
            rb.setBackgroundResource(R.drawable.segmented_control_last);
        } else {
            rb.setBackgroundResource(R.drawable.segmented_control);
        }

        rb.setText(getPageTitle(position));

        return rb;

    }
}
