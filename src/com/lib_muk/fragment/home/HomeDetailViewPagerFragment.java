package com.lib_muk.fragment.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lib_muk.MyFragment;
import com.lib_muk.R;




/**
 * TODO
 * 全部课程内页 --viewpager*/

public class HomeDetailViewPagerFragment extends MyFragment{
	String item; 
	TextView detail;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.home_mk_list_detail_viewpager, container, false);
		//获取Fragment传递过来的参数
		Bundle mBundle = getArguments();
		item =  mBundle.getString("item");
		detail=(TextView) view.findViewById(R.id.detail);
		detail.setText(item);
		return view;
		
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}
}
