package com.lib_muk.fragment.main;


import com.lib_muk.MyFragment;
import com.lib_muk.R;
import com.lib_muk.views.MyTopBar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;

/**
 * TODO
 * 下载 页卡 */

public class DownloadCourseFragment extends MyFragment{
	public static final int Request_Home = 1;
	public static final String TAG ="MyCourseFragment";
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.downloadcourse_mk_list, container, false);
		
		return new MyTopBar(getActivity()).setLeftBtn("", R.drawable.sliding_menu_icon, new OnClickListener() {
			@Override
			public void onClick(View v) {
				showMenu();
			}
		}).setTitle("下载").setContentView(view);
	}
}
