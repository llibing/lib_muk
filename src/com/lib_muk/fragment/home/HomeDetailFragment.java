package com.lib_muk.fragment.home;



import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lib_muk.MainActivity;
import com.lib_muk.MyApp;
import com.lib_muk.MyFragment;
import com.lib_muk.R;
import com.lib_muk.fragment.custom_radiogroup.FragmentRadioGroup;
import com.lib_muk.fragment.custom_radiogroup.FragmentTab;
import com.lib_muk.fragment.setting.SettingGradeFragment;
import com.lib_muk.model.HomeAllCourse;
import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;
import android.widget.SeekBar.OnSeekBarChangeListener;

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
	// 自定义VideoView
    private FullScreenVideoView mVideo;
    // 底部View
 	private View mBottomView,fullvideo;
    // 视频播放拖动条
 	private SeekBar mSeekBar;
 	private ImageView mPlay;
 	private TextView mPlayTime;
 	private TextView mDurationTime;
    // 屏幕宽高
 	private float width;
 	private float height;
 	// 视频播放时间
 	private int playTime;
    // 自动隐藏顶部和底部View的时间
 	private static final int HIDE_TIME = 5000;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.home_mk_list_detail, container, false);
		final HomeAllCourse h = getSerializableExtra(HomeAllCourse.class);
		m.menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
		contain(view);
		init(view);
//		videoView=(VideoView) view.findViewById(R.id.videoview);
//		mController=new MediaController(context);
//		 //获取网络视屏播放
//		 videoView.setVideoURI(Uri.parse(h.getVideoSrc()));
//		 // 设置videoView与mController建立关联
//		 videoView.setMediaController(mController);
//		 // 设置mController与videoView建立关联
//		 mController.setMediaPlayer(videoView);
		 // 让VideoView获取焦点
//		 videoView.requestFocus();
//		 videoView.start();
		
		
		width = DensityUtil.getWidthInPx(context);
		height = DensityUtil.getHeightInPx(context);

		 view.findViewById(R.id.full_btn).setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View view) {
					fullvideo.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
					RelativeLayout.LayoutParams layoutParams=  
				              new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);  
				           layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);  
				           layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);  
				           layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);  
				           layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
//				           fullvideo.setLayoutParams(layoutParams);
				           mVideo.setLayoutParams(layoutParams);
				}
			});
		 
		mPlay.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View view) {
				switch (view.getId()) {
				case R.id.play_btn:
					if (mVideo.isPlaying()) {
						mVideo.pause();
						mPlay.setImageResource(R.drawable.video_btn_down);
					} else {
						mVideo.start();
						mPlay.setImageResource(R.drawable.video_btn_on);
					}
					break;
				default:
					break;
				}
			}
		});
		mSeekBar.setOnSeekBarChangeListener(mSeekBarChangeListener);
        playVideo(h.getVideoSrc());
		
		return view;
		
	}
//	@Override
//	public void onConfigurationChanged(Configuration newConfig) {
//		if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
//			height = DensityUtil.getWidthInPx(context);
//			width = DensityUtil.getHeightInPx(context);
//		} else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
//			width = DensityUtil.getWidthInPx(context);
//			height = DensityUtil.getHeightInPx(context);
//		}
//		super.onConfigurationChanged(newConfig);
//	}


	private OnSeekBarChangeListener mSeekBarChangeListener = new OnSeekBarChangeListener() {

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			mHandler.postDelayed(hideRunnable, HIDE_TIME);
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
			mHandler.removeCallbacks(hideRunnable);
		}

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			if (fromUser) {
				int time = progress * mVideo.getDuration() / 100;
				mVideo.seekTo(time);
			}
		}
	};


//	@Override
//	public void onDestroy() {
//		super.onDestroy();
//		mHandler.removeMessages(0);
//		mHandler.removeCallbacksAndMessages(null);
//	}

	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 1:
				if (mVideo.getCurrentPosition() > 0) {
					mPlayTime.setText(formatTime(mVideo.getCurrentPosition()));
					int progress = mVideo.getCurrentPosition() * 100 / mVideo.getDuration();
					mSeekBar.setProgress(progress);
					if (mVideo.getCurrentPosition() > mVideo.getDuration() - 100) {
						mPlayTime.setText("00:00");
						mSeekBar.setProgress(0);
					}
					mSeekBar.setSecondaryProgress(mVideo.getBufferPercentage());
				} else {
					mPlayTime.setText("00:00");
					mSeekBar.setProgress(0);
				}
				
				break;
			case 2:
				showOrHide();
				break;

			default:
				break;
			}
		}
	};

	private void playVideo(String videoUrl) {
		mVideo.setVideoPath(videoUrl);
		mVideo.requestFocus();
		mVideo.setOnPreparedListener(new OnPreparedListener() {
			@Override
			public void onPrepared(MediaPlayer mp) {
				mVideo.setVideoWidth(mp.getVideoWidth());
				mVideo.setVideoHeight(mp.getVideoHeight());

				mVideo.start();
				if (playTime != 0) {
					mVideo.seekTo(playTime);
				}

				mHandler.removeCallbacks(hideRunnable);
				mHandler.postDelayed(hideRunnable, HIDE_TIME);
				mDurationTime.setText(formatTime(mVideo.getDuration()));
				Timer timer = new Timer();
				timer.schedule(new TimerTask() {

					@Override
					public void run() {
						mHandler.sendEmptyMessage(1);
					}
				}, 0, 1000);
			}
		});
		mVideo.setOnCompletionListener(new OnCompletionListener() {
			@Override
			public void onCompletion(MediaPlayer mp) {
				mPlay.setImageResource(R.drawable.video_btn_down);
				mPlayTime.setText("00:00");
				mSeekBar.setProgress(0);
			}
		});
	}

	private Runnable hideRunnable = new Runnable() {

		@Override
		public void run() {
			showOrHide();
		}
	};

	@SuppressLint("SimpleDateFormat")
	private String formatTime(long time) {
		DateFormat formatter = new SimpleDateFormat("mm:ss");
		return formatter.format(new Date(time));
	}


	private void showOrHide() {
		if (mBottomView.getVisibility() == View.VISIBLE) {
			mBottomView.clearAnimation();
			Animation animation1 = AnimationUtils.loadAnimation(getActivity(),
					R.anim.option_leave_from_bottom);
			animation1.setAnimationListener(new AnimationImp() {
				@Override
				public void onAnimationEnd(Animation animation) {
					super.onAnimationEnd(animation);
					mBottomView.setVisibility(View.GONE);
				}
			});
			mBottomView.startAnimation(animation1);
		} else {
			mBottomView.setVisibility(View.VISIBLE);
			mBottomView.clearAnimation();
			Animation animation1 = AnimationUtils.loadAnimation(getActivity(),
					R.anim.option_entry_from_bottom);
			mBottomView.startAnimation(animation1);
			mHandler.removeCallbacks(hideRunnable);
			mHandler.postDelayed(hideRunnable, HIDE_TIME);
		}
	}

	private class AnimationImp implements AnimationListener {

		@Override
		public void onAnimationEnd(Animation animation) {

		}

		@Override
		public void onAnimationRepeat(Animation animation) {
		}

		@Override
		public void onAnimationStart(Animation animation) {
		}

	}
	private void init(View view){
		mVideo = (FullScreenVideoView)view.findViewById(R.id.videoview);
		mPlayTime = (TextView) view.findViewById(R.id.play_time);
		mDurationTime = (TextView) view.findViewById(R.id.total_time);
		mPlay = (ImageView) view.findViewById(R.id.play_btn);
		mSeekBar = (SeekBar) view.findViewById(R.id.seekbar);
		mBottomView = view.findViewById(R.id.bottom_layout);
		fullvideo = view.findViewById(R.id.fullvideo);
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
