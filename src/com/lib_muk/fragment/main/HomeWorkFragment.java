package com.lib_muk.fragment.main;


import com.lib_muk.MyApp;
import com.lib_muk.MyFragment;
import com.lib_muk.R;
import com.lib_muk.fragment.homework.HomeWorkDetailFragment;
import com.lib_muk.views.MyTopBar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;

/**
 * TODO
 * 家庭作业 页卡 */

public class HomeWorkFragment extends MyFragment{
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.homework_fragment_main, container, false);
		init(view);
    	
		view.findViewById(R.id.homework).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				addFragment(MyApp.createFragment(HomeWorkDetailFragment.class));
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
