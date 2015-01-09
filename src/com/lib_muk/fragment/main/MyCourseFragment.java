package com.lib_muk.fragment.main;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fax.utils.view.list.ObjectXAdapter;
import com.fax.utils.view.list.ObjectXListView;
import com.lib_muk.MyApp;
import com.lib_muk.MyFragment;
import com.lib_muk.R;
import com.lib_muk.fragment.home.HomeDetailFragment;
import com.lib_muk.model.HomeAllCourse;
import com.lib_muk.views.MyTopBar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.TextView;

/**
 * TODO
 * 我的课程 页卡 */

public class MyCourseFragment extends MyFragment{
	public static final int Request_Home = 1;
	public static final String TAG ="MyCourseFragment";
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.mycourse_mk_list, container, false);
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
				return new ArrayList<HomeAllCourse>(Arrays.asList(HomeAllCourse.homeAllCourse_ITEMmycourse));
			}
		});
		return new MyTopBar(getActivity()).setLeftBtn("", R.drawable.sliding_menu_icon, new OnClickListener() {
			@Override
			public void onClick(View v) {
				showMenu();
			}
		}).setTitle("我的课程").setContentView(view);
		
	}
}
