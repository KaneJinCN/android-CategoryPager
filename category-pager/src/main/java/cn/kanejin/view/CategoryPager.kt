package cn.kanejin.view

import android.content.Context
import android.database.DataSetObserver
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.RadioGroup.OnCheckedChangeListener

/**
 * Created by Kane on 20/07/2017.
 */

class CategoryPager : FrameLayout {
    val navigator: CategoryPagerNav?
        get() {
            return findViewById(R.id.category_pager_nav) as CategoryPagerNav?
        }

    val pager: CategoryPagerContent?
        get() {
            return findViewById(R.id.category_pager_content) as CategoryPagerContent?
        }

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    fun setAdapter(adapter: CategoryPagerAdapter) {

        buildNav(adapter)

        buildPager(adapter)
    }

    private fun buildPager(adapter: CategoryPagerAdapter) {

        pager?.adapter = adapter

        adapter.registerDataSetObserver(object : DataSetObserver() {
            override fun onChanged() {
                pager?.adapter = null
                pager?.adapter = adapter
            }
        })

        pager?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageSelected(position: Int) {
                navigator?.setCurrentPosition(position)
            }

            override fun onPageScrolled(arg0: Int, arg1: Float, arg2: Int) {

            }

            override fun onPageScrollStateChanged(arg0: Int) {
            }
        })
    }

    private fun buildNav(adapter: CategoryPagerAdapter) {
        navigator?.adapter = adapter

        navigator?.onCheckedChangeListener = OnCheckedChangeListener {
            group, checkedId ->
            pager?.currentItem = checkedId
        }
    }

}
