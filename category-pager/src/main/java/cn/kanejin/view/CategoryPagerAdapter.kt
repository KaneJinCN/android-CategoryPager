package cn.kanejin.view

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import cn.kanejin.commons.util.StringUtils

/**
 * Created by Kane on 8/31/14.
 */
abstract class CategoryPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    val randomId = StringUtils.random(6, true).toInt()

    abstract fun getCursor(parent: ViewGroup): View?

    abstract fun getNavigatorButton(position: Int, parent: ViewGroup): RadioButton

    final override fun getItemId(position: Int): Long {
        return randomId * 1000 + super.getItemId(position)
    }
}
