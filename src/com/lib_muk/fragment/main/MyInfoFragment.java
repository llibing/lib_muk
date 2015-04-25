package com.lib_muk.fragment.main;


import java.util.ArrayList;
import java.util.List;

import com.fax.utils.view.list.ObjectXAdapter;
import com.fax.utils.view.list.ObjectXListView;
import com.google.gson.Gson;
import com.lib_muk.MyApp;
import com.lib_muk.MyFragment;
import com.lib_muk.R;
import com.lib_muk.fragment.home.HomeDetailFragment;
import com.lib_muk.fragment.myinfo.MyInfoDetailFragment;
import com.lib_muk.model.MessagesEntityList;
import com.lib_muk.model.MessagesEntityList.MessagesEntity;
import com.lib_muk.model.VideoEntityList;
import com.lib_muk.model.VideoEntityList.VideoEntity;
import com.lib_muk.views.MyTopBar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * TODO
 * 我的消息 页卡 */

public class MyInfoFragment extends MyFragment{
	public static final int Request_Home = 1;
	public static final String TAG ="MyCourseFragment";
	ObjectXListView listView;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.common_list, container, false);
		
		
		listView = (ObjectXListView) view.findViewById(android.R.id.list);
		listView.setPullRefreshEnable(false);
		listView.setPullLoadEnable(false);
		listView.setAdapter(new ObjectXAdapter.SinglePageAdapter<MessagesEntity>() {
					@Override
					 public String getUrl() {
						 return MyApp.Host+"messagesController.do?datagrid&field=id,teacherEntity_Id,teacherEntity_realName,messagename,messagecontent,createtime";
					 }
					@Override
					public View bindView(MessagesEntity m, int position, View view) {
						if(view==null){
							view=View.inflate(context, R.layout.common_list_item, null);
							((RelativeLayout)view.findViewById(R.id.studentInfo)).setVisibility(View.VISIBLE);
							((TextView)view.findViewById(R.id.person_name)).setText(m.getTeacherEntity_realName());
						}
						((TextView)view.findViewById(R.id.name)).setText(m.getMessagename());
						((TextView)view.findViewById(R.id.description)).setText(m.getMessagecontent());
						return view;
					}
					@Override
					public void onItemClick(MessagesEntity m, View view, int position, long id) {
						super.onItemClick(m, view, position, id);
						addFragment(MyApp.createFragment(MyInfoDetailFragment.class, m));
					}
					@Override
					public List<MessagesEntity> instanceNewList(String json) throws Exception {
						ArrayList<MessagesEntity> allList=new Gson().fromJson(json, MessagesEntityList.class).getRows();
						return allList;
					}
				});
		
		return new MyTopBar(getActivity()).setLeftBtn("", R.drawable.sliding_menu_icon, new OnClickListener() {
			@Override
			public void onClick(View v) {
				showMenu();
			}
		}).setTitle("我的消息").setContentView(view);
	}
}
