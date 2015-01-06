package com.lib_muk.clickshow;


import com.lib_muk.R;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;


public class ClickShowTextView extends TextView{
    public ClickShowTextView(Context context) {
        super(context);
    }

    public ClickShowTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeface(ChangeFont(context));
    }

    public ClickShowTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setPressed(boolean pressed) {
		super.setPressed(pressed);
        invalidate();
	}

//    @Override
//    public void draw(Canvas canvas) {
//        super.draw(canvas);
//        if(isPressed()){
//            canvas.drawColor(getContext().getResources().getColor(R.color.alpha_black));
//        }
//    }
    
    //字体设置
    private  Typeface typeface;
    public  final Typeface ChangeFont(Context context) {
        if (typeface == null) {
            typeface = Typeface.createFromAsset(context.getAssets(),
                    "font/kaiti.ttf");
        }
        return typeface;
    }
}
