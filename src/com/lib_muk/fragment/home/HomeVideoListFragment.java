package com.lib_muk.fragment.home;

import java.util.ArrayList;
import java.util.List;
import com.fax.utils.view.list.ObjectXAdapter;
import com.fax.utils.view.list.ObjectXListView;
import com.google.gson.Gson;
import com.lib_muk.MyApp;
import com.lib_muk.MyFragment;
import com.lib_muk.R;
import com.lib_muk.fragment.home.HomeDetailFragment;
import com.lib_muk.model.UnitEntity;
import com.lib_muk.model.VideoEntityList;
import com.lib_muk.model.VideoEntity;
import com.lib_muk.views.MyTopBar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 
 * 全部课程--内页--课程列表 */

public class HomeVideoListFragment extends MyFragment{
	
	ObjectXListView listView;
	UnitEntity u;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.common_list, container, false);
		MyTopBar tobar=new MyTopBar(context);
		
		u=(UnitEntity) getArguments().getSerializable(UnitEntity.class.getName());
		
    	listView = (ObjectXListView) view.findViewById(android.R.id.list);
		listView.setPullRefreshEnable(false);
		listView.setPullLoadEnable(false);
		listView.setAdapter(new ObjectXAdapter.SinglePageAdapter<VideoEntity>() {
					@Override
					 public String getUrl() {
						 return MyApp.Host+"videoController.do?datagrid&field=id,unitEntity_id,unitEntity_coursesEntity_courseabout,unitEntity_coursesEntity_teacherEntity_teacherabout" +
						 		",unitEntity_coursesEntity_teacherEntity_realName,videoname,videosrc,videotime&unitEntity.id="+u.getId();
					 }
					@Override
					public View bindView(VideoEntity v, int position, View view) {
						if(view==null){
							view=View.inflate(context, R.layout.common_list_item, null);
						}
						((TextView)view.findViewById(R.id.name)).setText(v.getVideoname());
						((TextView)view.findViewById(R.id.description)).setText(Integer.parseInt(v.getVideotime())/60+"分"+Integer.parseInt(v.getVideotime())%60+"秒");
						return view;
					}
					@Override
					public void onItemClick(VideoEntity v, View view, int position, long id) {
						super.onItemClick(v, view, position, id);
						HomeDetailFragment hd=new HomeDetailFragment();
						hd.setTargetFragment(HomeVideoListFragment.this, 1);
						Bundle args = new Bundle();
						args.putSerializable("videoEntity", v);
						hd.setArguments(args);
						addFragment(hd);
//						addFragment(MyApp.createFragment(HomeDetailFragment.class, v));
					}
					@Override
					public List<VideoEntity> instanceNewList(String json) throws Exception {
						ArrayList<VideoEntity> allList=new Gson().fromJson(json, VideoEntityList.class).getRows();
						return allList;
					}
				});
				
		return tobar.setLeftBack().setTitle("视频列表").setContentView(view);
	}
	
}
