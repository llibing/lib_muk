package com.lib_muk.fragment.home;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.fax.utils.bitmap.BitmapManager;
import com.fax.utils.http.HttpUtils;
import com.fax.utils.task.ResultAsyncTask;
import com.google.gson.Gson;
import com.lib_muk.MyApp;
import com.lib_muk.MyFragment;
import com.lib_muk.R;
import com.lib_muk.model.UnitAndVideoList;
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
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.home_mk_list_detail_viewpager, container, false);
		//获取Fragment传递过来的参数
		init(view);
		Bundle mBundle = getArguments();
		item =  mBundle.getString("item");
		v=(VideoEntity) getArguments().getSerializable("videoEntity");
		if(item.equals("详细信息")){
			NOExpandableListView.setVisibility(View.VISIBLE);
			teacher_name.setText(v.getUnitEntity_coursesEntity_teacherEntity_realName());
			teacher_detail.setText(v.getUnitEntity_coursesEntity_teacherEntity_teacherabout());
			course_detail.setText(v.getUnitEntity_coursesEntity_courseabout());
		}else{
			notescontent.setVisibility(View.VISIBLE);
			notescontent.setText(item);
		}
		
		return view;
		
	}
	
	private void unit(){
		new ResultAsyncTask<UnitAndVideoList>(context) {
			@Override
			protected UnitAndVideoList doInBackground(Void... arg0) {
				ArrayList<NameValuePair> pairs=new ArrayList<NameValuePair>();
				pairs.add(new BasicNameValuePair("id", "8ae5614d4cc1beeb014cc1c6b9da0012"));//studentid
				String json= HttpUtils.reqForPost(MyApp.Host+"unitController.do?listunit", pairs);
				String json1="{"+"'unitAndVideo':"+json+"}";
				Log.e("", "------------------"+json1);
				try {
					return new Gson().fromJson(json1, UnitAndVideoList.class);
				} catch (Exception e) {
				}
				return null;
			}
			
			@Override
			protected void onPostExecuteSuc(UnitAndVideoList result) {
				Log.e("", "--------------------------"+result.getUnitAndVideo().get(0).getUnitname());
			}
		}.setProgressDialog().execute();
	}
	
	private void init(View view){
		notescontent=(TextView) view.findViewById(R.id.notescontent);
		teacher_name=(TextView) view.findViewById(R.id.teacher_name);
		teacher_detail=(TextView) view.findViewById(R.id.teacher_detail);
		jianjie=(TextView) view.findViewById(R.id.jianjie);
		course_detail=(TextView) view.findViewById(R.id.course_detail);
		teacher_icon=(ImageView) view.findViewById(R.id.teacher_icon);
		NOExpandableListView=(LinearLayout) view.findViewById(R.id.NOExpandableListView);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}
}
