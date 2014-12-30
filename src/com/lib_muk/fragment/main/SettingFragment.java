package com.lib_muk.fragment.main;


import com.lib_muk.MyApp;
import com.lib_muk.MyFragment;
import com.lib_muk.R;
import com.lib_muk.views.MyTopBar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Toast;

/**
 * TODO
 * 设置 页卡 */

public class SettingFragment extends MyFragment{
	public static final String TAG ="SettingFragment";
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		 View view = inflater.inflate(R.layout.setting_mk_list, container, false);
		
		view.findViewById(R.id.setting_mk_list_clear).setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View view) {
				
			}
			
		});
		view.findViewById(R.id.setting_mk_list_suggestion).setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View view) {
				Toast.makeText(context, "意见反馈", Toast.LENGTH_LONG).show();
				SettingFeedbackFragment feedback=new SettingFeedbackFragment();
				addFragment(feedback);
			}
		});
		
		return new MyTopBar(getActivity()).setLeftBtn("", R.drawable.sliding_menu_icon, new OnClickListener() {
			@Override
			public void onClick(View v) {
				showMenu();
			}
		}).setTitle("设置").setContentView(view);
	}
}
