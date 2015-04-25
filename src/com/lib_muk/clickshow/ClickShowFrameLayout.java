package com.lib_muk.clickshow;


import com.lib_muk.R;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class ClickShowFrameLayout extends RelativeLayout{
    public ClickShowFrameLayout(Context context) {
        super(context);
        setClickable(true);
        init();
    }

    public ClickShowFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setClickable(true);
        init();
    }

    public ClickShowFrameLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setClickable(true);
        init();
    }
    private void init(){
    	if(getBackground()==null){
    		setBackgroundResource(android.R.color.transparent);
    	}
    	setWillNotDraw(false);
    }

    @Override
    public void setPressed(boolean pressed) {
		super.setPressed(pressed);
        invalidate();
	}

	@Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if(isPressed()){
            canvas.drawColor(getContext().getResources().getColor(R.color.alpha_black));
        }
    }
    @Override
    public ViewParent invalidateChildInParent(int[] location, Rect dirty) {
        invalidate();
        return super.invalidateChildInParent(location, dirty);
    }
    @Override
    public void childDrawableStateChanged(View child) {
        invalidate();
        super.childDrawableStateChanged(child);
    }
    @Override
    public void refreshDrawableState() {
        super.refreshDrawableState();
        invalidate();
    }

}
