package com.lib_muk.fragment.main;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fax.utils.view.list.ObjectXAdapter;
import com.fax.utils.view.list.ObjectXListView;
import com.google.gson.Gson;
import com.lib_muk.MyApp;
import com.lib_muk.MyFragment;
import com.lib_muk.R;
import com.lib_muk.fragment.home.HomeDetailFragment;
import com.lib_muk.model.HomeAllCourse;
import com.lib_muk.model.StudentVideoEntityList;
import com.lib_muk.model.StudentVideoEntityList.StudentVideoEntity;
import com.lib_muk.model.VideoEntityList;
import com.lib_muk.views.MyTopBar;

import android.content.SharedPreferences;
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
	SharedPreferences sp;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.mycourse_mk_list, container, false);
		sp=MyApp.getDefaultSp();
		ObjectXListView listView = (ObjectXListView) view.findViewById(android.R.id.list);
		listView.setPullRefreshEnable(false);
		listView.setAdapter(new ObjectXAdapter.SinglePageAdapter<StudentVideoEntity>() {
			
			@Override
			public String getUrl() {
				
				return MyApp.Host+"studentvideoController.do?datagrid&field=id,videoentity_videosrc,videoentity_videoname,videoentity_videotime," +
						"videoentity_unitEntity_coursesEntity_teacherEntity_realName,videoentity_Id," +
						"videoentity_unitEntity_coursesEntity_teacherEntity_teacherabout," +
						"videoentity_unitEntity_coursesEntity_courseabout,learntime,converttime&studentEntity.id="+sp.getString(MyApp.USER_ID, null);
			}
			
			@Override
			public View bindView(StudentVideoEntity sv, int position, View view) {
				if(view == null){
					view = View.inflate(context, R.layout.common_list_item, null);
				}
				((TextView)view.findViewById(R.id.name)).setText(sv.getVideoentity_videoname());
				((TextView)view.findViewById(R.id.description)).setText(Integer.parseInt(sv.getVideoentity_videotime())/60+"分"+Integer.parseInt(sv.getVideoentity_videotime())%60+"秒");
					
				return view;
			}
			@Override
			public void onItemClick(StudentVideoEntity sv, View view, int position, long id) {
				super.onItemClick(sv, view, position, id);
				HomeDetailFragment hd=new HomeDetailFragment();
				hd.setTargetFragment(MyCourseFragment.this, 1);
				Bundle args = new Bundle();
				args.putSerializable("studentVideoEntity", sv);
				hd.setArguments(args);
				addFragment(hd);
			}
			@Override
			public List<StudentVideoEntity> instanceNewList(String json) throws Exception {
				ArrayList<StudentVideoEntity> allList=new Gson().fromJson(json, StudentVideoEntityList.class).getRows();
				return allList;
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
