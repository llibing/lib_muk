package com.lib_muk.fragment.main;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fax.utils.bitmap.BitmapManager;
import com.fax.utils.view.list.ObjectXAdapter;
import com.fax.utils.view.list.ObjectXListView;
import com.lib_muk.MyApp;
import com.lib_muk.MyFragment;
import com.lib_muk.R;
import com.lib_muk.fragment.home.HomeDetailFragment;
import com.lib_muk.model.HomeAllCourse;
import com.lib_muk.pulldownmenu.ConstantCategoryMenu;
import com.lib_muk.pulldownmenu.DeviceUtility;
import com.lib_muk.pulldownmenu.MenuUtility;
import com.lib_muk.pulldownmenu.PulldownMenuView;
import com.lib_muk.pulldownmenu.PulldownMenuView.OnMenuItemClickListener;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * TODO
 * 全部课程 页卡 */

public class HomeFragment extends MyFragment{
	public static final int Request_Home = 1;
	public static final String TAG ="HomeFragment";
	
	   // 导航条LinearLayout
		private LinearLayout linearLayoutTopic = null;
		// 界面布局
		private RelativeLayout layoutHeader = null;
		private LinearLayout layoutBottom = null;
		// PulldownMenuView基本操作类
		private MenuUtility menuUtility = null;
		// PulldownMenuView对象
		private PulldownMenuView pullDownMenu = null;
		private TextView tvTopic = null;
		private ImageView ivTopic,imageViewWeather= null;
		
		private int height = 0;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.home_mk_list, container, false);
		init(view);
    	ObjectXListView listView = (ObjectXListView) view.findViewById(android.R.id.list);
		listView.setPullRefreshEnable(false);
		listView.setAdapter(new ObjectXAdapter.SingleLocalPageAdapter<HomeAllCourse>() {
			@Override
			public View bindView(HomeAllCourse homeAllCourse, int position, View view) {
				if(view == null){
					view = View.inflate(context, R.layout.home_muk_list_item, null);
				}
//				BitmapManager.bindView(view.findViewById(R.id.allcouerse_imgId),homeAllCourse.getImgId());
				((TextView)view.findViewById(R.id.allcouerse_name)).setText(homeAllCourse.getAllCourseName());
				((TextView)view.findViewById(R.id.allCourse_description)).setText(homeAllCourse.getAllCourseDescription());
				return view;
			}
			@Override
			public void onItemClick(HomeAllCourse h, View view, int position, long id) {
				super.onItemClick(h, view, position, id);
				addFragment(MyApp.createFragment(HomeDetailFragment.class, h));
			}
			@Override
			public List<HomeAllCourse> instanceNewList() throws Exception {
				return new ArrayList<HomeAllCourse>(Arrays.asList(HomeAllCourse.homeAllCourse_ITEM));
			}
		});
    	
		return view;
	}
	/**
	 * 初始化
	 */
	private void init(View view){
		ivTopic = (ImageView) view.findViewById(R.id.imageViewTopic);
		imageViewWeather = (ImageView) view.findViewById(R.id.imageViewWeather);
		imageViewWeather.setOnClickListener(imageOnClickListener);
    	tvTopic = (TextView)view. findViewById(R.id.textViewTopic);
    	
    	linearLayoutTopic = (LinearLayout)view.findViewById(R.id.linearLayoutTopic);
    	linearLayoutTopic.setOnClickListener(TopicOnClickListener);
    	layoutHeader = (RelativeLayout)view. findViewById(R.id.layout_top_header);
    	
    	height = DeviceUtility.getScreenSize(context)[1] - 
    			 layoutHeader.getLayoutParams().height - 
    		  	 DeviceUtility.getStatusBarHeight(context);
    	
    	menuUtility = new MenuUtility(
    			context, 
    			ConstantCategoryMenu.newsImageRes,
    			ConstantCategoryMenu.newsMenuTexts,
    			height,layoutHeader);
	}
	
	 /**
     * 显示PulldownMenuView
     */
    protected void showPulldownMenu(){
    	pullDownMenu = menuUtility.getPulldownMenuView((String)tvTopic.getText());
    	ivTopic.setImageResource(R.drawable.ic_menu_trangle_up);
    }
    
    /**
     * 隐藏PulldownMenuView
     */
    protected void hidePulldownMenu(){
    	pullDownMenu.releasePopupMenuView();
    	ivTopic.setImageResource(R.drawable.ic_menu_trangle_down);
    }
    
    // 顶部头条事件监听器
    private OnClickListener TopicOnClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			// 开始显示下拉菜单
//			showPulldownMenu();
//			
//			// TODO Auto-generated method stub
//			pullDownMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {
//				@Override
//				public void onMenuItemClick(AdapterView<?> parent, View view, int position) {
//					// TODO Auto-generated method stub
//					tvTopic.setText(ConstantCategoryMenu.newsMenuTexts[position]);
//					getFragment(ConstantCategoryMenu.newsBodyRes[position]);
//				}
//				
//				@Override
//				public void hideMenu() {
//					hidePulldownMenu();
//				}
//			});
//			
//			pullDownMenu.show();
		}
	};
	 private OnClickListener imageOnClickListener = new OnClickListener() {
			@Override
			public void onClick(View v) {
				showMenu();
			}
		};
		private Fragment getFragment(int position){
			switch(position){
			case 1: return new HomeFragment();
			case 2: return new MyCourseFragment();
			}
			return new HomeFragment();
		}
}
