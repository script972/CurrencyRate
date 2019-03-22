package com.script972.currencyrate.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.script972.currencyrate.R;

import androidx.viewpager.widget.ViewPager;

public class CustomViewPager extends ViewPager {

    private boolean swipable = true;

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        parseAttributes(context, attrs);
    }

    private void parseAttributes(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ViewPagerValues, 0, 0);
        setPagingEnabled(a.getBoolean(R.styleable.ViewPagerValues_swipeable, true));
        a.recycle();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (this.swipable) {
            return super.onTouchEvent(event);
        }

        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (this.swipable) {
            return super.onInterceptTouchEvent(event);
        }

        return false;
    }

    public void setPagingEnabled(boolean enabled) {
        this.swipable = enabled;
    }
}
