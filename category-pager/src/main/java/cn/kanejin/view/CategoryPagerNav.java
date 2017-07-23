package cn.kanejin.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;


/**
 * Created by Kane on 20/07/2017.
 */

public class CategoryPagerNav extends HorizontalScrollView {

    public CategoryPagerNav(Context context) {
        super(context);
        buildNav();
    }

    public CategoryPagerNav(Context context, AttributeSet attrs) {
        super(context, attrs);
        buildNav();
    }

    public CategoryPagerNav(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        buildNav();
    }

    private RelativeLayout navContainer;
    private RadioGroup navButtonGroup;
    private View navCursor;

    private boolean isCursorWidthInited = false;

    private void buildNav() {
        navContainer = new RelativeLayout(getContext());
        navContainer.setLayoutParams(new ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT));

        addView(navContainer);

        buildButtonGroup();

        // 初始设定游标的宽度
        addOnLayoutChangeListener(new OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (!isCursorWidthInited && navCursor != null) {

                    int checkedButtonWidth = navButtonGroup.getChildAt(navButtonGroup.getCheckedRadioButtonId()).getMeasuredWidth();

                    int cursorWidth = navCursor.getMeasuredWidth();

                    if (cursorWidth != checkedButtonWidth) {
                        navCursor.getLayoutParams().width = checkedButtonWidth;

                        navCursor.requestLayout();

                        isCursorWidthInited = true;
                    }
                }

            }
        });

    }

    private OnCheckedChangeListener mOnCheckedChangeListener;

    public OnCheckedChangeListener getOnCheckedChangeListener() {
        return mOnCheckedChangeListener;
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener onCheckedChangeListener) {
        this.mOnCheckedChangeListener = onCheckedChangeListener;
    }

    private void buildButtonGroup() {
        navButtonGroup = new RadioGroup(getContext());
        navButtonGroup.setLayoutParams(new ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT));
        navButtonGroup.setOrientation(LinearLayout.HORIZONTAL);

        navButtonGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton btn = (RadioButton) group.getChildAt(checkedId);

                if (btn == null)
                    return;

                // 游标划动
                if (navCursor != null) {
                    ObjectAnimator moveAnim = ObjectAnimator.ofFloat(navCursor, "x", navCursor.getX(), btn.getX());

                    ValueAnimator resizeWidthAnim = ValueAnimator.ofInt(navCursor.getMeasuredWidth(), btn.getMeasuredWidth());

                    resizeWidthAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            navCursor.getLayoutParams().width = (Integer) animation.getAnimatedValue();

                            navCursor.requestLayout();
                        }
                    });


                    AnimatorSet set = new AnimatorSet();
                    set.playTogether(moveAnim, resizeWidthAnim);
                    set.setDuration(200);
                    set.start();
                }

                if (group.getChildAt(2) != null)
                    smoothScrollTo(
                            ((checkedId > 1) ? btn.getLeft() : 0) - group.getChildAt(2).getLeft(), 0);

                if (mOnCheckedChangeListener != null) {
                    mOnCheckedChangeListener.onCheckedChanged(group, checkedId);
                }
            }
        });

        navContainer.addView(navButtonGroup);
    }


    private CategoryPagerAdapter mAdapter;

    public CategoryPagerAdapter getAdapter() {
        return mAdapter;
    }

    public void setAdapter(CategoryPagerAdapter adapter) {
        this.mAdapter = adapter;

        if (mAdapter != null) {
            mAdapter.registerDataSetObserver(
                    new DataSetObserver() {
                        @Override
                        public void onChanged() {
                            dataSetChanged();
                        }
                    }
            );
        }

        buildNavViews();
    }


    private void buildNavViews() {
        if (mAdapter == null)
            return;

        clearNavItems();
        isCursorWidthInited = false;

        for (int i = 0; i < mAdapter.getCount(); i++) {
            RadioButton item = mAdapter.getNavigatorButton(i, navButtonGroup);

            item.setId(i);
            item.setChecked(i == 0);

            navButtonGroup.addView(item, i);
        }

        navCursor = mAdapter.getCursor(navContainer);

        if (navCursor != null) {
            navContainer.addView(navCursor, 0);
        }
    }

    public void setCurrentPosition(int position) {
        navButtonGroup.getChildAt(position).performClick();
    }

    private void clearNavItems() {
        navButtonGroup.removeAllViews();

        if (navCursor != null) {
            navContainer.removeView(navCursor);
        }
    }

    private void dataSetChanged() {
        buildNavViews();
    }
}