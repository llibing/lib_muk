package com.lib_muk.utils.view.pager;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**ViewPager的指示器，体现形式为点 */
public class PointIndicator extends LinearLayout {
	OnPageChangeListener onPageChangeListener;

	public PointIndicator(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public PointIndicator(Context context) {
		super(context);
		init();
	}
	private void init(){
		setOrientation(HORIZONTAL);
		paint = new Paint();
		paint.setAntiAlias(true);
	}
	public void setSize(int size){
		removeAllViews();
		if(size<=1) return;
		for(int i=0;i<size;i++){
			View rb = createPointView(i);
			rb.setId(i);
			addView(rb);
		}
		check(0);
	}
	private void check(int position) {
		checkedPosition = position;
		for(int i=0,count=getChildCount();i<count;i++){
			getChildAt(i).invalidate();
		}
	}
	private int checkedPosition;
	private int colorChecked = 0xff004898;
	private int colorNormal = Color.WHITE;
	private Paint paint;
	private View createPointView(final int position){
		View pointView = new View(getContext()){
			@Override
			protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
				int size = (int) (10 * getResources().getDisplayMetrics().density);
				int measureSpec = MeasureSpec.makeMeasureSpec(size, MeasureSpec.EXACTLY);
				super.onMeasure(measureSpec, measureSpec);
			}
			@Override
			protected void onDraw(Canvas canvas) {
				if(position == checkedPosition) paint.setColor(colorChecked);
				else paint.setColor(colorNormal);
				canvas.drawCircle(canvas.getWidth()/2, canvas.getHeight()/2, canvas.getWidth()/4, paint);
			}
		};
		return pointView;
	}

    public int getCheckedPosition() {
		return checkedPosition;
	}

	public void setColorChecked(int colorChecked) {
		this.colorChecked = colorChecked;
	}

	public void setColorNormal(int colorNormal) {
		this.colorNormal = colorNormal;
	}

	/**
     * 绑定这个ViewPager，会覆盖清空原有的OnPageChangeListener
     */
    public void bindViewPager(ViewPager viewPager){
        bindViewPager(viewPager, null);
    }
    /**
     * 绑定这个ViewPager，覆盖OnPageChangeListener
     * @param viewPager 需要绑定的ViewPager
     * @param onPageChangeListener viewpager需要设置的OnPageListener
     */
	public void bindViewPager(ViewPager viewPager, OnPageChangeListener pageChangeListener){
		setSize(viewPager.getAdapter().getCount());
		this.onPageChangeListener = pageChangeListener;
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {
			public void onPageSelected(int position) {
				check(position);
                if(onPageChangeListener!=null) onPageChangeListener.onPageSelected(position);
			}
			public void onPageScrolled(int arg0, float arg1, int arg2) {
                if(onPageChangeListener!=null) onPageChangeListener.onPageScrolled(arg0, arg1, arg2);
			}
			public void onPageScrollStateChanged(int arg0) {
                if(onPageChangeListener!=null) onPageChangeListener.onPageScrollStateChanged(arg0);
			}
		});
	}
	public OnPageChangeListener getOnPageChangeListener() {
		return onPageChangeListener;
	}

	public void setOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
		this.onPageChangeListener = onPageChangeListener;
	}
}
