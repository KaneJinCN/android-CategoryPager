package cn.kanejin.view;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import cn.kanejin.commons.util.StringUtils;

/**
 * Created by Kane on 8/31/14.
 */
public abstract class CategoryPagerAdapter extends FragmentPagerAdapter {

    private Integer randomId = new Integer(StringUtils.random(6, true));

    public CategoryPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public abstract View getCursor(ViewGroup parent);

    public abstract RadioButton getNavigatorButton(int position, ViewGroup parent);


    @Override
    public final long getItemId(int position) {
        return randomId * 1000 + super.getItemId(position);
    }
}
