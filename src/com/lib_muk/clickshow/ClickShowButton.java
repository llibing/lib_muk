package com.lib_muk.clickshow;


import com.lib_muk.R;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * 
 * @author libing
 *
 */
 
public class ClickShowButton extends Button{
    public ClickShowButton(Context context) {
        super(context);
    }

    public ClickShowButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ClickShowButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
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

}
