package com.lib_muk.fragment.setting;


import com.lib_muk.MyFragment;
import com.lib_muk.R;
import com.lib_muk.model.SlidingItem;
import com.lib_muk.views.MyTopBar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 
 * 关于我们
 * 
*/

public class SettingAboutFragment extends MyFragment{
	public static final String TAG ="SettingFragment";
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		 View view = inflater.inflate(R.layout.setting_mk_list_about, container, false);
		 final SlidingItem illegal = getSerializableExtra(SlidingItem.class);
		 MyTopBar topBar = (MyTopBar) new MyTopBar(context).setLeftBack().setTitle("关于我们").setContentView(view);
		return topBar;
	}
}
