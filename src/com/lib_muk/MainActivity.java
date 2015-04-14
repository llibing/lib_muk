package com.lib_muk;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fax.utils.view.list.ObjectXAdapter;
import com.fax.utils.view.list.ObjectXListView;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lib_muk.fragment.main.DownloadCourseFragment;
import com.lib_muk.fragment.main.HomeFragment;
import com.lib_muk.fragment.main.HomeWorkFragment;
import com.lib_muk.fragment.main.MyCourseFragment;
import com.lib_muk.fragment.main.MyInfoFragment;
import com.lib_muk.fragment.main.SettingFragment;
import com.lib_muk.model.SlidingItem;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {

	private static MainActivity instance;
	public static MainActivity getInstance(){
		return instance;
	}
	//一种比较新的设置界面或配置界面效果，在主界面左滑或者右滑出现设置界面
	public static SlidingMenu menu;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		instance=this;
		setContentView(R.layout.activity_home);
		// configure the SlidingMenu
        menu = new SlidingMenu(this);
       //设置左滑菜单        
        menu.setMode(SlidingMenu.LEFT);
      //设置阴影图片的宽度
        menu.setShadowWidthRes(R.dimen.shadow_width);
      //设置阴影图片
        menu.setShadowDrawable(R.drawable.shadow);
      //SlidingMenu划出时主页面显示的剩余宽度
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
      //SlidingMenu滑动时的渐变程度
        menu.setFadeDegree(0.35f);
      //使SlidingMenu附加在Activity上
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        menu.setMenu(createMenu());
        switchFragment(getFragment(1));
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		instance= null;
	}

	public void showMenu(){
		menu.showMenu();
	}
	public void switchFragment(int position){
//		setTitle(menus[position]);
		switchFragment(getFragment(position));
	}
	public void switchFragment(Fragment fragment){
//		if(fragment instanceof HomeFragment)// 仅在主页面允许滑出，其他页面有菜单键
//			menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);//设置滑动的屏幕范围，该设置为全屏区域都可以滑动
//		else menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);//不允许滑动
		 menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		//右侧SlidingMenu的Fragment
		getSupportFragmentManager().beginTransaction().replace(R.id.content, fragment).commit();
		menu.showContent();
	}

	@Override
	public void onBackPressed() {
		//点击返回键关闭滑动菜单
		if (menu.isMenuShowing()) {
			menu.showContent();
		}else if(getSupportFragmentManager().popBackStackImmediate()){//
		} else if(nowShowingFragmentPosition!=1){
			switchFragment(getFragment(1));
		}else{
			super.onBackPressed();
		}
	}

	String[] menus;
	private View createMenu(){
		View menuView=View.inflate(this, R.layout.activity_home_menu, null);
		ObjectXListView listView = (ObjectXListView) menuView.findViewById(android.R.id.list);
		listView.setPullRefreshEnable(false);
		listView.setVerticalScrollBarEnabled(false);
		listView.setAdapter(new ObjectXAdapter.SingleLocalPageAdapter<SlidingItem>() {
			@Override
			public View bindView(SlidingItem s, int position, View view) {
				if(view == null){
					view = View.inflate(getApplication(), R.layout.activity_home_menu_item, null);
				}
				((ImageView)view.findViewById(R.id.activity_home_menu_item_img)).setImageResource(s.getImgId());
				((TextView)view.findViewById(R.id.activity_home_menu_item_name)).setText(s.getSlidingName());
				return view;
			}
			@Override
			public void onItemClick(SlidingItem t, View view, int position, long id) {
				super.onItemClick(t, view, position, id);
				 switchFragment(getFragment(position));
			}
			@Override
			public List<SlidingItem> instanceNewList() throws Exception {
				return new ArrayList<SlidingItem>(Arrays.asList(SlidingItem.sliding_ITEMS));
			}
		});
		
		return menuView;
	}
	
	private int nowShowingFragmentPosition=-1;
	private Fragment getFragment(int position){
		nowShowingFragmentPosition=position;
		switch(position){
		case 1: return new HomeFragment();
		case 2: return new MyCourseFragment();
		case 3: return new MyInfoFragment();
		case 4: return new HomeWorkFragment();
		case 5: return new DownloadCourseFragment();
		case 6: return new SettingFragment();
		}
		return new HomeFragment();
	}
	
	
}
