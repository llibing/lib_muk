package com.lib_muk;

import java.util.List;

import com.fax.utils.view.pager.PointIndicator;
import com.fax.utils.view.pager.SamePagerAdapter;
import com.lib_muk.utils.frameAnim.Frame;
import com.lib_muk.utils.frameAnim.FrameFactory;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class StartActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.intro);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				if(!MyApp.hasKeyOnce("intro")){
					setContentView(R.layout.intro_first);
			        final View startBtn = findViewById(R.id.intro_btn_start);
			        startBtn.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							startActivity(new Intent(StartActivity.this, MainActivity.class));
						}
					});
			        
					ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
			        final List<Frame> frames = FrameFactory.createFramesFromAsset(StartActivity.this, "intro", -1);
			        viewPager.setAdapter(new SamePagerAdapter<Frame>(frames) {
						@Override
						public View getView(Frame t, int position, View convertView) {
							if(convertView == null){
								convertView = new ImageView(StartActivity.this);
								((ImageView)convertView).setScaleType(ScaleType.CENTER_CROP);
							}
							((ImageView)convertView).setImageDrawable(t.decodeDrawable(StartActivity.this));
							return convertView;
						}
						@Override
						protected void onItemDestroyed(View view, Frame t) {
							super.onItemDestroyed(view, t);
							t.recycle();
						}
					});
			        
			        PointIndicator pointIndicator = (PointIndicator) findViewById(R.id.point_indicator);
			        pointIndicator.setColorNormal(Color.parseColor("#5a5a5a"));
			        pointIndicator.bindViewPager(viewPager, new ViewPager.OnPageChangeListener() {
						@Override
						public void onPageSelected(int position) {
							if(position==frames.size()-1){
								startBtn.setVisibility(View.VISIBLE);
								startBtn.startAnimation(AnimationUtils.loadAnimation(StartActivity.this, R.anim.fade_in));
							}
							else startBtn.setVisibility(View.INVISIBLE);
						}
						@Override
						public void onPageScrolled(int arg0, float arg1, int arg2) {
						}
						@Override
						public void onPageScrollStateChanged(int arg0) {
						}
					});
					
				}else startActivity(new Intent(StartActivity.this, MainActivity.class));
			}
		}, 3000);//3s进入主页面
	}

}
