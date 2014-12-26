package com.lib_muk.fragment.main;


import com.fax.faw_vw.MyFragment;
import com.fax.faw_vw.views.MyTopBar;
import com.fax.utils.view.list.ObjectXListView;
import com.lib_muk.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * TODO
 * 首页 页卡 */

public class HomeFragment extends MyFragment{
	public static final int Request_Home = 1;
	public static final String TAG ="HomeFragment";
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.home_mk_list, container, false);
		ObjectXListView listView = new ObjectXListView(context);
		
		return new MyTopBar(getActivity()).setTitle("全部课程").setContentView(view);
	}
}
