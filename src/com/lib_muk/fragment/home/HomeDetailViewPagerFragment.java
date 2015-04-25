package com.lib_muk.fragment.home;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.fax.utils.bitmap.BitmapManager;
import com.fax.utils.http.HttpUtils;
import com.fax.utils.task.ResultAsyncTask;
import com.fax.utils.view.list.ObjectXAdapter;
import com.fax.utils.view.list.ObjectXListView;
import com.google.gson.Gson;
import com.lib_muk.MyApp;
import com.lib_muk.MyFragment;
import com.lib_muk.R;
import com.lib_muk.model.NotesEntityList;
import com.lib_muk.model.NotesEntityList.NotesEntity;
import com.lib_muk.model.UnitAndVideoList;
import com.lib_muk.model.WorkPageList;
import com.lib_muk.model.VideoEntityList.VideoEntity;
import com.lib_muk.model.WorkPageList.WorkPage;




/**
 * TODO
 * 全部课程内页 --viewpager*/

public class HomeDetailViewPagerFragment extends MyFragment{
	String item; 
	
	TextView notescontent,teacher_name,teacher_detail,jianjie,course_detail;
	ImageView teacher_icon;
	VideoEntity v;
	LinearLayout NOExpandableListView;
	LinearLayout mylistView;
	ObjectXListView listView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.home_mk_list_detail_viewpager, container, false);
		//获取Fragment传递过来的参数
		init(view);
		Bundle mBundle = getArguments();
		item =  mBundle.getString("item");
		v=(VideoEntity) getArguments().getSerializable("videoEntity");
		listView = (ObjectXListView) view.findViewById(android.R.id.list);
		listView.setPullRefreshEnable(false);
		listView.setPullLoadEnable(false);
		if(item.equals("详细信息")){
			NOExpandableListView.setVisibility(View.VISIBLE);
			teacher_name.setText(v.getUnitEntity_coursesEntity_teacherEntity_realName());
			teacher_detail.setText(v.getUnitEntity_coursesEntity_teacherEntity_teacherabout());
			course_detail.setText(v.getUnitEntity_coursesEntity_courseabout());
		}else{
			mylistView.setVisibility(View.VISIBLE);
			getNoteList(listView);
		}
		
		return view;
		
	}
	
	private void getNoteList(ObjectXListView listView){
		listView.setAdapter(new ObjectXAdapter.SinglePageAdapter<NotesEntity>() {
			@Override
			 public String getUrl() {
				 return MyApp.Host+"notesController.do?datagrid&field=id,studentEntity_studentname,videoEntity_Id,notescontent,notesattach,createtime&videoEntity.Id="+v.getId();
			 }
			@Override
			public View bindView(NotesEntity n, int position, View view) {
				if(view == null){
					view = View.inflate(context, R.layout.common_list_item, null);
					((RelativeLayout)view.findViewById(R.id.studentInfo)).setVisibility(View.VISIBLE);
					((TextView)view.findViewById(R.id.person_name)).setText(n.getStudentEntity_studentname());
				}
				((TextView)view.findViewById(R.id.name)).setText(n.getNotescontent());
				return view;
			}
			@Override
			public void onItemClick(NotesEntity n, View view, int position, long id) {
				super.onItemClick(n, view, position, id);
			}
			@Override
			public List<NotesEntity> instanceNewList(String json) throws Exception {
				ArrayList<NotesEntity> allList=new Gson().fromJson(json, NotesEntityList.class).getRows();
				return allList;
			}
		});
	}
	
	private void init(View view){
		notescontent=(TextView) view.findViewById(R.id.notescontent);
		teacher_name=(TextView) view.findViewById(R.id.teacher_name);
		teacher_detail=(TextView) view.findViewById(R.id.teacher_detail);
		jianjie=(TextView) view.findViewById(R.id.jianjie);
		course_detail=(TextView) view.findViewById(R.id.course_detail);
		teacher_icon=(ImageView) view.findViewById(R.id.teacher_icon);
		NOExpandableListView=(LinearLayout) view.findViewById(R.id.NOExpandableListView);
		mylistView=(LinearLayout) view.findViewById(R.id.mylistView);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}
}
