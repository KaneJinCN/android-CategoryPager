package cn.kanejin.view

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.database.DataSetObserver
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.*
import android.widget.RadioGroup.OnCheckedChangeListener


/**
 * Created by Kane on 20/07/2017.
 */

class CategoryPagerNav : HorizontalScrollView {

    constructor(context: Context) : super(context) {
        buildNav()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        buildNav()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        buildNav()
    }

    private var navContainer: RelativeLayout? = null
    private var navButtonGroup: RadioGroup? = null
    private var navCursor: View? = null

    private var isCursorWidthInited = false

    private fun buildNav() {
        navContainer = RelativeLayout(context)
        navContainer!!.layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)

        addView(navContainer)

        buildButtonGroup()

        // 初始设定游标的宽度
        addOnLayoutChangeListener {
            v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom ->

            if (!isCursorWidthInited && navCursor != null) {

                val checkedButtonWidth = navButtonGroup!!.getChildAt(navButtonGroup!!.checkedRadioButtonId)?.measuredWidth

                val cursorWidth = navCursor!!.measuredWidth

                if (checkedButtonWidth != null && cursorWidth != checkedButtonWidth) {
                    navCursor!!.layoutParams.width = checkedButtonWidth

                    navCursor!!.requestLayout()

                    isCursorWidthInited = true
                }
            }

        }
    }

    var onCheckedChangeListener: OnCheckedChangeListener? = null

    private fun buildButtonGroup() {
        navButtonGroup = RadioGroup(context)
        navButtonGroup!!.layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
        navButtonGroup!!.orientation = LinearLayout.HORIZONTAL

        navButtonGroup!!.setOnCheckedChangeListener({ group, checkedId ->

            val btn = group.getChildAt(checkedId) as RadioButton? ?: return@setOnCheckedChangeListener

            //MyLog.d(TAG, "Button " + checkedId + " has checked! Button size : " + btn.measuredWidth + "x" + btn.measuredHeight)

            // 游标划动
            if (navCursor != null) {
                val moveAnim = ObjectAnimator.ofFloat(navCursor!!, "x", navCursor!!.x, btn.x)

                val resizeWidthAnim = ValueAnimator.ofInt(navCursor!!.measuredWidth, btn.measuredWidth)
                resizeWidthAnim.addUpdateListener {
                    navCursor!!.layoutParams.width = it.animatedValue as Int

                    navCursor!!.requestLayout()
                }

                val set = AnimatorSet()
                set.playTogether(moveAnim, resizeWidthAnim)
                set.duration = 200
                set.start()
            }

            if (group.getChildAt(2) != null)
                smoothScrollTo(
                        (if (checkedId > 1) btn.left else 0) - group.getChildAt(2).left, 0)


            onCheckedChangeListener?.onCheckedChanged(group, checkedId)
        })

        navContainer!!.addView(navButtonGroup)
    }


    var adapter: CategoryPagerAdapter? = null
        set(value) {
            field = value

            field?.registerDataSetObserver(object : DataSetObserver() {
                override fun onChanged() {
                    dataSetChanged()
                }
            })

            buildNavViews()
        }

    fun buildNavViews() {
        if (adapter == null)
            return

        clearNavItems()
        isCursorWidthInited = false

        for (i in 0..adapter!!.count - 1) {
            val item = adapter!!.getNavigatorButton(i, navButtonGroup!!)

            item.id = i
            item.isChecked = i == 0

            navButtonGroup!!.addView(item, i)
        }

        navCursor = adapter!!.getCursor(navContainer!!)

        if (navCursor != null) {
            navContainer?.addView(navCursor, 0)
        }
    }

    fun setCurrentPosition(position: Int) {
        navButtonGroup!!.getChildAt(position)?.performClick()
    }

    private fun clearNavItems() {
        navButtonGroup!!.removeAllViews()
        if (navCursor != null) {
            navContainer!!.removeView(navCursor)
        }
    }

    private fun dataSetChanged() {
        buildNavViews()
    }
}