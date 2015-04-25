package com.lib_muk;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import com.fax.utils.bitmap.BitmapManager;
import com.fax.utils.http.HttpUtils;
import com.fax.utils.task.ResultAsyncTask;
import com.fax.utils.view.pager.PointIndicator;
import com.fax.utils.view.pager.SamePagerAdapter;
import com.google.gson.Gson;
import com.lib_muk.model.LoginEntity;
import com.lib_muk.utils.frameAnim.Frame;
import com.lib_muk.utils.frameAnim.FrameFactory;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ImageView.ScaleType;

public class StartActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.intro);
		final SharedPreferences sp = MyApp.getDefaultSp();
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				/*if(!MyApp.hasKeyOnce("intro")){
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
					
				}else startActivity(new Intent(StartActivity.this, MainActivity.class));*/
				new ResultAsyncTask<LoginEntity>(StartActivity.this) {
					@Override
					protected LoginEntity doInBackground(Void... params) {
						ArrayList<NameValuePair> pairslist=new ArrayList<NameValuePair>();
						pairslist.add(new BasicNameValuePair("username", sp.getString(MyApp.Key_LoginName, null)));
						pairslist.add(new BasicNameValuePair("password",sp.getString(MyApp.Key_LoginPasswd, null)));
						String json=HttpUtils.reqForPost(MyApp.Host+"studentController.do?checkstudent", pairslist);
						try {
							return new Gson().fromJson(json, LoginEntity.class);
						} catch (Exception e) {
						}
						return null;
					}
					@Override
					protected void onPostExecuteSuc(LoginEntity result) {
//						if(result.getSuccess().equals("true")){
//							Toast.makeText(context, "用户登录成功！", Toast.LENGTH_SHORT).show();
//						}else{
//							sp.edit().putString(MyApp.Key_LoginPasswd, "").putString(MyApp.Key_LoginName, "").putString(MyApp.USER_ID, "").putString(MyApp.Key_Person_headerurl, "").commit();
//							Toast.makeText(context, "对不起！用户名密码出错！", Toast.LENGTH_SHORT).show();
//						}
						startActivity(new Intent(StartActivity.this, MainActivity.class));
					}
					@Override
					protected void onPostExecuteFail(LoginEntity result) {
//						sp.edit().putString(MyApp.Key_LoginPasswd, "").putString(MyApp.Key_LoginName, "").putString(MyApp.USER_ID, "").putString(MyApp.Key_Person_headerurl, "").commit();
//						Toast.makeText(context, "对不起！用户名密码出错！", Toast.LENGTH_SHORT).show();
						startActivity(new Intent(StartActivity.this, MainActivity.class));
					}
				}.execute();
			}
		}, 3000);//3s进入主页面
	}

}
