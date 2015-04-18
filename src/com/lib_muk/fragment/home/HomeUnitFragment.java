package com.lib_muk.fragment.home;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.fax.utils.bitmap.BitmapManager;
import com.fax.utils.view.list.ObjectXAdapter;
import com.fax.utils.view.list.ObjectXAdapter.SingleLocalPageAdapter;
import com.fax.utils.view.list.ObjectXListView;
import com.google.gson.Gson;
import com.lib_muk.MyApp;
import com.lib_muk.MyFragment;
import com.lib_muk.R;
import com.lib_muk.fragment.home.HomeDetailFragment;
import com.lib_muk.model.UnitEntityList;
import com.lib_muk.model.CourseCatlogList;
import com.lib_muk.model.CourseCatlogList.CourseCatlog;
import com.lib_muk.model.HomeAllCourse;
import com.lib_muk.model.UnitEntity;
import com.lib_muk.model.WorkPageList;
import com.lib_muk.model.WorkPageList.WorkPage;
import com.lib_muk.views.MyTopBar;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 
 * 全部课程--内页--章节 */

public class HomeUnitFragment extends MyFragment{
	
	ObjectXListView listView;
	WorkPage wp;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.common_list, container, false);
		MyTopBar tobar=new MyTopBar(context);
		
		wp=(WorkPage) getArguments().getSerializable(WorkPage.class.getName());
		
    	listView = (ObjectXListView) view.findViewById(android.R.id.list);
		listView.setPullRefreshEnable(false);
		listView.setPullLoadEnable(false);
		listView.setAdapter(new ObjectXAdapter.SinglePageAdapter<UnitEntity>() {
					@Override
					 public String getUrl() {
						 return MyApp.Host+"unitController.do?datagrid&field=id,coursesEntity_id,unitname,unitabout&coursesEntity.id="+wp.getId();
					 }
					@Override
					public View bindView(UnitEntity u, int position, View view) {
						if(view==null){
							view=View.inflate(context, R.layout.common_list_item, null);
						}
						((TextView)view.findViewById(R.id.name)).setText(u.getUnitname());
						((TextView)view.findViewById(R.id.description)).setText(u.getUnitabout());
						return view;
					}
					@Override
					public void onItemClick(UnitEntity u, View view, int position, long id) {
						super.onItemClick(u, view, position, id);
						addFragment(MyApp.createFragment(HomeVideoListFragment.class, u));
					}
					@Override
					public List<UnitEntity> instanceNewList(String json) throws Exception {
						ArrayList<UnitEntity> allList=new Gson().fromJson(json, UnitEntityList.class).getRows();
						return allList;
					}
				});
				
		return tobar.setLeftBack().setTitle("章节").setContentView(view);
	}
	
}
