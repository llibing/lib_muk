package com.lib_muk.fragment.main;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.fax.utils.bitmap.BitmapManager;
import com.fax.utils.view.list.ObjectXAdapter;
import com.fax.utils.view.list.ObjectXAdapter.SingleLocalPageAdapter;
import com.fax.utils.view.list.ObjectXListView;
import com.google.gson.Gson;
import com.lib_muk.MainActivity;
import com.lib_muk.MyApp;
import com.lib_muk.MyFragment;
import com.lib_muk.R;
import com.lib_muk.fragment.home.HomeDetailFragment;
import com.lib_muk.fragment.home.HomeUnitFragment;
import com.lib_muk.model.CourseCatlogList;
import com.lib_muk.model.CourseCatlogList.CourseCatlog;
import com.lib_muk.model.HomeAllCourse;
import com.lib_muk.model.WorkPageList;
import com.lib_muk.model.WorkPageList.WorkPage;

import android.content.SharedPreferences;
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
import android.widget.Toast;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 
 * 全部课程 页卡 */

public class HomeFragment extends MyFragment{
	public static final int Request_Home = 1;
	public static final String TAG ="HomeFragment";
	
	   // 导航条LinearLayout
		private LinearLayout linearLayoutTopic = null;
		// 界面布局
//		private RelativeLayout layoutHeader = null;
		private LinearLayout layoutBottom = null;
		private TextView tvTopic = null;
		private ImageView ivTopic,imageViewWeather= null;
		
		private PopupWindow popupWindow;
		ObjectXListView listView;
		String courseCatlogEntityId=null;
		String url;
		SharedPreferences sp;
		
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.home_mk_list, container, false);
		init(view);
    	listView = (ObjectXListView) view.findViewById(android.R.id.list);
		listView.setPullRefreshEnable(false);
		sp=MyApp.getDefaultSp();
		
		if(courseCatlogEntityId==null){
			url=MyApp.Host+"coursesController.do?listdatagrid&field=id,planstageEntity_Id,courseCatlogEntity_Id,teacherEntity_Id,teacherEntity_realName,coursename,imgsrc,createtime,courseabout,coursediffcult,coursenote,whatlearn";
		}
		getWorkPageList(listView,url);
    	
		LayoutInflater lay = (LayoutInflater)getActivity().getSystemService(context.LAYOUT_INFLATER_SERVICE);
		final View v1 = lay.inflate(R.layout.listview, null);
		popupWindow=new PopupWindow(v1,RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT);
		linearLayoutTopic.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//弹出popupWindow，背景变暗
//				ColorDrawable cd = new ColorDrawable(0x000000);
//				popupWindow.setBackgroundDrawable(cd);
//				WindowManager.LayoutParams lp=getActivity().getWindow().getAttributes();
//				lp.alpha = 0.4f;
//				getActivity().getWindow().setAttributes(lp);
				//点击popupWindow外部，popupWindow也会dismiss
				popupWindow.setOutsideTouchable(true); 
				// 使其聚集
				popupWindow.setFocusable(true);
				//刷新状态
				popupWindow.update();
				ivTopic.setImageResource(R.drawable.ic_menu_trangle_down);
				ObjectXListView listView1 = (ObjectXListView) v1.findViewById(android.R.id.list);
				listView1.setPullRefreshEnable(false);
				listView1.setPullLoadEnable(false);
				listView1.setAdapter(new ObjectXAdapter.SinglePageAdapter<CourseCatlog>() {
					@Override
					 public String getUrl() {
						 return MyApp.Host+"courseCatlogController.do?datagrid&field=id,catalogname,courseClassEntity_id";
					 }
					@Override
					public View bindView(CourseCatlog c, int position, View view) {
						if(view==null){
							view=View.inflate(context, R.layout.listview_item, null);
						}
						((TextView)view.findViewById(R.id.textView1)).setText(c.getCatalogname());
						return view;
					}
					@Override
					public void onItemClick(CourseCatlog c, View view, int position, long id) {
						super.onItemClick(c, view, position, id);
						courseCatlogEntityId=c.getId();
						url=MyApp.Host+"coursesController.do?listdatagrid&field=id,planstageEntity_Id,courseCatlogEntity_Id,teacherEntity_Id,teacherEntity_realName,teacherEntity_teacherabout,coursename,imgsrc,createtime,courseabout,coursediffcult,coursenote,whatlearn&courseCatlogEntity.Id="+courseCatlogEntityId;
						getWorkPageList(listView,url);
						tvTopic.setText(c.getCatalogname());
						ivTopic.setImageResource(R.drawable.ic_menu_trangle_up);
						 popupWindow.dismiss();
					}
					@Override
					public List<CourseCatlog> instanceNewList(String json) throws Exception {
						ArrayList<CourseCatlog> Datalist=new Gson().fromJson(json, CourseCatlogList.class).getRows();
						return Datalist;
					}
				});
				//PopupWindow的显示及位置设置
//				popupWindow.showAtLocation(linearLayoutTopic, Gravity.BOTTOM|Gravity.CENTER_VERTICAL, 0, 20);
				//将PopupWindow作为anchor的下拉窗口显示。即在classes的左下角显示
				popupWindow.showAsDropDown(linearLayoutTopic);
				//popupWindow消失之后，背景恢复
				popupWindow.setOnDismissListener(new OnDismissListener(){
				    //在dismiss中恢复透明度
				    public void onDismiss(){
				        WindowManager.LayoutParams lp=getActivity().getWindow().getAttributes();
				        lp.alpha = 1f;
				        getActivity().getWindow().setAttributes(lp);	
				      }			
				     });
				
				popupWindow.getContentView().setOnTouchListener(new OnTouchListener() {
					@Override
					public boolean onTouch(View arg0, MotionEvent arg1) {
						ivTopic.setImageResource(R.drawable.ic_menu_trangle_up);
						popupWindow.dismiss();
						return true;
					}

				});
				
			}
		});
		
		
		return view;
	}
	
	private void getWorkPageList(ObjectXListView listView,final String url){
		listView.setAdapter(new ObjectXAdapter.SinglePageAdapter<WorkPage>() {
			@Override
			 public String getUrl() {
				 return url;
			 }
			@Override
			public View bindView(WorkPage workPage, int position, View view) {
				if(view == null){
					view = View.inflate(context, R.layout.homeworkpage_muk_list_item, null);
				}
				BitmapManager.bindView(view.findViewById(R.id.allcouerse_imgId),workPage.getImgsrc());
				((TextView)view.findViewById(R.id.teacher_name)).setText(workPage.getTeacherEntity_realName());
				((TextView)view.findViewById(R.id.createtime)).setText(workPage.getCreatetime());
				((TextView)view.findViewById(R.id.coursename)).setText(workPage.getCoursename());
				return view;
			}
			@Override
			public void onItemClick(WorkPage workPage, View view, int position, long id) {
				super.onItemClick(workPage, view, position, id);
				if(!sp.getString(MyApp.USER_ID, null).equals("")){
					addFragment(MyApp.createFragment(HomeUnitFragment.class, workPage));
				}else{
					Toast.makeText(context, "对不起！请先登录！", Toast.LENGTH_SHORT).show();
				}
				
			}
			@Override
			public List<WorkPage> instanceNewList(String json) throws Exception {
				ArrayList<WorkPage> Datalist=new Gson().fromJson(json, WorkPageList.class).getRows();
				return Datalist;
			}
		});
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
	}
	
	 private OnClickListener imageOnClickListener = new OnClickListener() {
			@Override
			public void onClick(View v) {
				showMenu();
			}
		};
}
