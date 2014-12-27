package com.lib_muk.fragment.main;


import com.fax.utils.view.TopBarContain;
import com.fax.utils.view.list.ObjectXListView;
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
 * 全部课程 页卡 */

public class HomeFragment extends MenuFragment{
	public static final int Request_Home = 1;
	public static final String TAG ="HomeFragment";
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.home_mk_list, container, false);
		
		return new MyTopBar(getActivity()).setLeftBtn("", R.drawable.topbar_menu, new OnClickListener() {
			@Override
			public void onClick(View v) {
				showMenu();
			}
		}).setTitle("微电影").setContentView(view);
	}
}
