package com.lib_muk.views;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;

import com.fax.utils.view.TopBarContain;
import com.lib_muk.MyApp;
import com.lib_muk.R;
import com.lib_muk.fragment.main.MenuFragment;

public class MyTopBar extends TopBarContain {

	public MyTopBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public MyTopBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public MyTopBar(Context context) {
		super(context);
		init();
	}
	
	private void init(){
		setBackgroundRes(R.color.dark_red);
		findViewById(R.id.topbar_content).getLayoutParams().height = ((int) MyApp.convertToDp(50));
		titleTv.setTextColor(Color.WHITE);
		leftBtn.setTextColor(Color.WHITE);
		rightBtn.setTextColor(Color.WHITE);
		
	}
	

//	public MyTopBar setLeftFinish(){
//		return (MyTopBar) setLeftBtn("返回", R.drawable.topbar_ic_back, new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				Context context=getContext();
//				if(context instanceof Activity){
//					((Activity) context).finish();
//				}
//			}
//		});
//	}
	public MyTopBar setLeftBack(){
		return (MyTopBar) setLeftBtn("返回", R.drawable.topbar_ic_back, new OnClickListener() {
			@Override
			public void onClick(View v) {
				Context context=getContext();
				if(context instanceof FragmentActivity){
					if(!((FragmentActivity) context).getSupportFragmentManager().popBackStackImmediate()){
						((Activity) context).finish();
					}
				}else if(context instanceof Activity){
					((Activity) context).finish();
				}
			}
		});
	}
}
