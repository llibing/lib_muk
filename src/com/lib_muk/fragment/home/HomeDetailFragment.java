package com.lib_muk.fragment.home;



import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lib_muk.MainActivity;
import com.lib_muk.MyFragment;
import com.lib_muk.R;
import com.lib_muk.fragment.custom_radiogroup.FragmentRadioGroup;
import com.lib_muk.fragment.custom_radiogroup.FragmentTab;
import com.lib_muk.model.HomeAllCourse;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * TODO
 * 全部课程内页 */

public class HomeDetailFragment extends MyFragment{
	public static final int Request_Home = 1;
	public static final String TAG ="HomeItemFragment";
	ViewPager viewPager = null;
	FragmentRadioGroup mFragmentRadioGroup = null;
	VideoView videoView;
	MediaController mController;
	FragmentTab mCouponTab1 = new FragmentTab();
	FragmentTab mCouponTab2 = new FragmentTab();
	private MainActivity m;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.home_mk_list_detail, container, false);
		final HomeAllCourse h = getSerializableExtra(HomeAllCourse.class);
		m.menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
		contain(view);
		videoView=(VideoView) view.findViewById(R.id.video);
		mController=new MediaController(context);
		Log.e(TAG+"---------------", h.getVideoSrc());
		 videoView.setVideoURI(Uri.parse(h.getVideoSrc()));
		 videoView.setMediaController(mController);
		 mController.setMediaPlayer(videoView);
		 videoView.requestFocus();
		 videoView.start();
		return view;
		
	}
	
	private void init(View view){
		
	}
    private void contain(View view){
    	viewPager = (ViewPager) view.findViewById(R.id.viewPager);
		mFragmentRadioGroup = (FragmentRadioGroup)view.findViewById(R.id.tabGroup);
		mFragmentRadioGroup.init(getActivity(),viewPager);
		mCouponTab1.msg = "详细信息";
		mCouponTab2.msg = "笔记";
		mFragmentRadioGroup.addFragment(mCouponTab1, "详细信息");
		mFragmentRadioGroup.addFragment(mCouponTab2, "笔记");
		viewPager.setAdapter(mFragmentRadioGroup.getAdapter());
    }
}
