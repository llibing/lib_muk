package com.lib_muk;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.fax.utils.bitmap.BitmapManager;
import com.fax.utils.http.HttpUtils;
import com.fax.utils.task.ResultAsyncTask;
import com.fax.utils.view.list.ObjectXAdapter;
import com.fax.utils.view.list.ObjectXListView;
import com.google.gson.Gson;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lib_muk.fragment.main.DownloadCourseFragment;
import com.lib_muk.fragment.main.HomeFragment;
import com.lib_muk.fragment.main.HomeWorkFragment;
import com.lib_muk.fragment.main.MyCourseFragment;
import com.lib_muk.fragment.main.MyInfoFragment;
import com.lib_muk.fragment.main.SettingFragment;
import com.lib_muk.model.LoginEntity;
import com.lib_muk.model.SlidingItem;
import com.lib_muk.videoview.utils.CustomDialog;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
		
public class MainActivity extends FragmentActivity {

	private static MainActivity instance;
	public static MainActivity getInstance(){
		return instance;
	}
	//一种比较新的设置界面或配置界面效果，在主界面左滑或者右滑出现设置界面
	public static SlidingMenu menu;
	private CustomDialog dialog;
	SharedPreferences sp;
	String name="";
	String person_headerurl="";
	TextView person_name;
	ImageView person_header;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		instance=this;
		setContentView(R.layout.activity_home);
		sp=MyApp.getDefaultSp();
		// configure the SlidingMenu
        menu = new SlidingMenu(this);
       //设置左滑菜单        
        menu.setMode(SlidingMenu.LEFT);
      //设置阴影图片的宽度
        menu.setShadowWidthRes(R.dimen.shadow_width);
      //设置阴影图片
        menu.setShadowDrawable(R.drawable.shadow);
      //SlidingMenu划出时主页面显示的剩余宽度
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
      //SlidingMenu滑动时的渐变程度
        menu.setFadeDegree(0.35f);
      //使SlidingMenu附加在Activity上
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        menu.setMenu(createMenu());
        switchFragment(getFragment(1));
        
        
        
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		instance= null;
	}

	public void showMenu(){
		menu.showMenu();
	}
	public void switchFragment(int position){
//		setTitle(menus[position]);
		switchFragment(getFragment(position));
	}
	public void switchFragment(Fragment fragment){
//		if(fragment instanceof HomeFragment)// 仅在主页面允许滑出，其他页面有菜单键
//			menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);//设置滑动的屏幕范围，该设置为全屏区域都可以滑动
//		else menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);//不允许滑动
		 menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		//右侧SlidingMenu的Fragment
		getSupportFragmentManager().beginTransaction().replace(R.id.content, fragment).commit();
		menu.showContent();
	}

	@Override
	public void onBackPressed() {
		//点击返回键关闭滑动菜单
		if (menu.isMenuShowing()) {
			menu.showContent();
		}else if(getSupportFragmentManager().popBackStackImmediate()){//
		} else if(nowShowingFragmentPosition!=1){
			switchFragment(getFragment(1));
		}else{
			exitBy2Click();//调用双击退出函数
		}
	}

	String[] menus;
	private View createMenu(){
		View menuView=View.inflate(this, R.layout.activity_home_menu, null);
		LinearLayout login=(LinearLayout) menuView.findViewById(R.id.login);
		person_name=(TextView) menuView.findViewById(R.id.person_name);
		person_header=(ImageView) menuView.findViewById(R.id.person_header);
		//登录
//		Login(person_name,person_header,sp.getString(MyApp.Key_LoginName, null),sp.getString(MyApp.Key_LoginPasswd, null));
		if(!sp.getString(MyApp.Key_Person_headerurl, "").equals("")){
			BitmapManager.bindView(person_header,sp.getString(MyApp.Key_Person_headerurl, null));
			person_name.setText(sp.getString(MyApp.Key_LoginName, null));
		}
	    login.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				LoginDialog(person_name,person_header);
			}
		});
		
		ObjectXListView listView = (ObjectXListView) menuView.findViewById(android.R.id.list);
		listView.setPullRefreshEnable(false);
		listView.setVerticalScrollBarEnabled(false);
		listView.setAdapter(new ObjectXAdapter.SingleLocalPageAdapter<SlidingItem>() {
			@Override
			public View bindView(SlidingItem s, int position, View view) {
				if(view == null){
					view = View.inflate(getApplication(), R.layout.activity_home_menu_item, null);
				}
				((ImageView)view.findViewById(R.id.activity_home_menu_item_img)).setImageResource(s.getImgId());
				((TextView)view.findViewById(R.id.activity_home_menu_item_name)).setText(s.getSlidingName());
				return view;
			}
			@Override
			public void onItemClick(SlidingItem t, View view, int position, long id) {
				super.onItemClick(t, view, position, id);
				if(!sp.getString(MyApp.Key_LoginName, "").equals("")){
					switchFragment(getFragment(position));
				}else{
					Toast.makeText(MainActivity.this, "对不起！请先登录！", Toast.LENGTH_SHORT).show();
				}
				 
			}
			@Override
			public List<SlidingItem> instanceNewList() throws Exception {
				return new ArrayList<SlidingItem>(Arrays.asList(SlidingItem.sliding_ITEMS));
			}
		});
		
		return menuView;
	}
	
	/** 
	 * 双击退出函数 
	 */  
	private static Boolean isExit = false;  
	  
	private void exitBy2Click() {  
	    Timer tExit = null;  
	    if (isExit == false) {  
	        isExit = true; // 准备退出  
	        switchFragment(getFragment(1));
	        tExit = new Timer();  
	        tExit.schedule(new TimerTask() {  
	            @Override  
	            public void run() {  
	                isExit = false; // 取消退出  
	            }  
	        }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务  
	  
	    } else { 
//	    	new AlertDialog.Builder(this).setTitle("             友情提示！")
	    	CustomDialog.Builder customBuilder = new CustomDialog.Builder(MainActivity.this);
			customBuilder.setTitle("友情提示！").setMessage("您是想注销当前账号，还是退出！")
//			.setMessage("您是想注销当前账号，还是退出！")
			.setNegativeButton("注销", new DialogInterface.OnClickListener() {
				public void onClick(final DialogInterface dialog, int which) {
					sp.edit().putString(MyApp.Key_LoginPasswd, "").putString(MyApp.Key_LoginName, "").putString(MyApp.USER_ID, "").putString(MyApp.Key_Person_headerurl, "").commit();
					finish();  
			        System.exit(0);
			        dialog.dismiss();
				}
			})
			.setPositiveButton("退出", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					finish();  
			        System.exit(0); 
			        dialog.dismiss();
				}
			}).create().show();
	    	
	    } 
	}
	
	private void LoginDialog(final TextView name,final ImageView header){
		LayoutInflater lay = (LayoutInflater)getSystemService(this.LAYOUT_INFLATER_SERVICE);
		final View v1 = lay.inflate(R.layout.confrimation_login_dialog, null);
		final EditText person_name=(EditText) v1.findViewById(R.id.person_name);
		final EditText person_password=(EditText) v1.findViewById(R.id.person_password);
		CustomDialog.Builder customBuilder = new CustomDialog.Builder(this);
		customBuilder.setTitle("用户登录").setContentView(v1)
		.setPositiveButton("确认", new DialogInterface.OnClickListener() {
			public void onClick(final DialogInterface dialog, int which) {
				if(TextUtils.isEmpty(person_name.getText().toString())){
					Toast.makeText(MainActivity.this, "请输入用户名！", Toast.LENGTH_SHORT).show();
					return;
				}
				if(TextUtils.isEmpty(person_password.getText().toString())){
					Toast.makeText(MainActivity.this, "请输入密码！", Toast.LENGTH_SHORT).show();
					return;
				}
				Login(name,header,person_name.getText().toString(),person_password.getText().toString());
			}
		})
		.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		dialog = customBuilder.create();
		dialog.show();
					
	}
	
	private void Login(final TextView name,final ImageView header,final String username,final String password){
		new ResultAsyncTask<LoginEntity>(MainActivity.this) {
			@Override
			protected LoginEntity doInBackground(Void... params) {
				ArrayList<NameValuePair> pairslist=new ArrayList<NameValuePair>();
				pairslist.add(new BasicNameValuePair("username", username));
				pairslist.add(new BasicNameValuePair("password",password));
				String json=HttpUtils.reqForPost(MyApp.Host+"studentController.do?checkstudent", pairslist);
				try {
					return new Gson().fromJson(json, LoginEntity.class);
				} catch (Exception e) {
				}
				return null;
			}
			@Override
			protected void onPostExecuteSuc(LoginEntity result) {
				if(result.getSuccess().equals("true")){
					dialog.dismiss();
					name.setText(result.getObj().getStudentname());
					if(!result.getObj().getImgsrc().equals("")){
					 BitmapManager.bindView(header, result.getObj().getImgsrc());
					}
					sp.edit().putString(MyApp.Key_LoginPasswd, password).putString(MyApp.Key_LoginName, username).putString(MyApp.USER_ID, result.getObj().getId()).putString(MyApp.Key_Person_headerurl, result.getObj().getImgsrc()).commit();
					Toast.makeText(context, "用户登录成功！", Toast.LENGTH_SHORT).show();
				}else{
					sp.edit().putString(MyApp.Key_LoginPasswd, "").putString(MyApp.Key_LoginName, "").putString(MyApp.USER_ID, "").putString(MyApp.Key_Person_headerurl, "").commit();
					Toast.makeText(context, "对不起！用户名密码出错！", Toast.LENGTH_SHORT).show();
				}
			}
			@Override
			protected void onPostExecuteFail(LoginEntity result) {
				sp.edit().putString(MyApp.Key_LoginPasswd, "").putString(MyApp.Key_LoginName, "").putString(MyApp.USER_ID, "").putString(MyApp.Key_Person_headerurl, "").commit();
				Toast.makeText(context, "对不起！用户名密码出错！", Toast.LENGTH_SHORT).show();
			}
		}.setProgressDialog().execute();
	}
	
	private int nowShowingFragmentPosition=-1;
	private Fragment getFragment(int position){
		nowShowingFragmentPosition=position;
		switch(position){
		case 1: return new HomeFragment();
		case 2: return new MyCourseFragment();
		case 3: return new MyInfoFragment();
		case 4: return new HomeWorkFragment();
		case 5: return new DownloadCourseFragment();
		case 6: return new SettingFragment();
		}
		return new HomeFragment();
	}
	
	
}
