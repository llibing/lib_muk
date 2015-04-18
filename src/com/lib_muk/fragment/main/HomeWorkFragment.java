package com.lib_muk.fragment.main;


import java.util.ArrayList;
import java.util.List;

import com.fax.utils.view.list.ObjectXAdapter;
import com.fax.utils.view.list.ObjectXListView;
import com.google.gson.Gson;
import com.lib_muk.MyApp;
import com.lib_muk.MyFragment;
import com.lib_muk.R;
import com.lib_muk.fragment.home.HomeVideoListFragment;
import com.lib_muk.fragment.homework.HomeWorkDetailFragment;
import com.lib_muk.model.HomeworkEntityList;
import com.lib_muk.model.HomeworkEntityList.HomeworkEntity;
import com.lib_muk.model.UnitEntity;
import com.lib_muk.model.UnitEntityList;
import com.lib_muk.views.MyTopBar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.TextView;

/**
 * TODO
 * 家庭作业 页卡 */

public class HomeWorkFragment extends MyFragment{
	ObjectXListView listView;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.homework_fragment_main, container, false);
		listView = (ObjectXListView) view.findViewById(android.R.id.list);
		listView.setPullRefreshEnable(false);
		listView.setPullLoadEnable(false);
		listView.setAdapter(new ObjectXAdapter.SinglePageAdapter<HomeworkEntity>() {
					@Override
					 public String getUrl() {
						 return MyApp.Host+"homeworkController.do?datagrid&field=id,unitEntity_Id,unitEntity_coursesEntity_coursename,homeworkname,homeworkcontent,createtime,homeworkattach";
					 }
					@Override
					public View bindView(HomeworkEntity h, int position, View view) {
						if(view==null){
							view=View.inflate(context, R.layout.common_list_item, null);
						}
						((TextView)view.findViewById(R.id.name)).setText(h.getUnitEntity_coursesEntity_coursename());
						((TextView)view.findViewById(R.id.description)).setText(h.getHomeworkname());
						return view;
					}
					@Override
					public void onItemClick(HomeworkEntity h, View view, int position, long id) {
						super.onItemClick(h, view, position, id);
						addFragment(MyApp.createFragment(HomeWorkDetailFragment.class, h));
					}
					@Override
					public List<HomeworkEntity> instanceNewList(String json) throws Exception {
						ArrayList<HomeworkEntity> allList=new Gson().fromJson(json, HomeworkEntityList.class).getRows();
						return allList;
					}
				});
		return new MyTopBar(getActivity()).setLeftBtn("", R.drawable.sliding_menu_icon, new OnClickListener() {
			@Override
			public void onClick(View v) {
				showMenu();
			}
		}).setTitle("作业动态").setContentView(view);
	}
	/**
	 * 初始化
	 */
	private void init(View view){
		
	}
	
	
}
