package com.lib_muk.fragment.custom_radiogroup;

import android.R.color;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.RadioButton;

public class BaseRadioButton extends RadioButton {

	private Paint mPaint=new Paint();
	private int selectedColor = Color.RED;
	private int unselectedColor = Color.BLACK;
	public void setPainterColor(int color){
		mPaint.setColor(color);
	}
	
	public void setPainterWidth(float w){
		mPaint.setStrokeWidth(w);
	}
	
	public BaseRadioButton(Context context) {
		this(context,null);
	}
	public BaseRadioButton(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	
	@Override
	public void setChecked(boolean checked) {
		super.setChecked(checked);
		invalidate();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if(isChecked()){
			//画底线 
			canvas.drawLine(1, this.getHeight() - 1,
        				this.getWidth() - 2,this.getHeight() - 1, mPaint);
			
			setTextColor(selectedColor);
		}else{
			setTextColor(unselectedColor);
		}
	}
}


