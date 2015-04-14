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
import com.lib_muk.model.HomeAllCourse;
import com.lib_muk.pulldownmenu.NotePopupWindow;
import com.lib_muk.videoview.utils.DensityUtil;
import com.lib_muk.videoview.utils.FullScreenVideoView;
import com.lib_muk.videoview.utils.LightnessController;
import com.lib_muk.videoview.utils.VolumnController;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
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
 	private View mBottomView;
 	private LinearLayout content,foot,liangdu,yingliang;
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
    // 自动隐藏顶部View的时间
 	private static final int HIDE_TIME = 5000;
    // 原始屏幕亮度
 	private int orginalLight;
    // 音频管理器
 	private AudioManager mAudioManager;
    // 声音调节Toast
 	private VolumnController volumnController;
 	//自定义的弹出框类 
 	NotePopupWindow notePopupWindow;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.home_mk_list_detail, container, false);
		final HomeAllCourse h = getSerializableExtra(HomeAllCourse.class);
		m.menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
		contain(view);
		init(view);
		volumnController = new VolumnController(context);
		mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
		width = DensityUtil.getWidthInPx(context);
		height = DensityUtil.getHeightInPx(context);
		threshold = DensityUtil.dip2px(context, 18);
		orginalLight = LightnessController.getLightness(getActivity());
		 view.findViewById(R.id.full_btn).setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View view) {
					if(content.getVisibility()==view.VISIBLE){
					content.setVisibility(View.GONE);
					foot.setVisibility(View.GONE);
					RelativeLayout.LayoutParams layoutParams=  
				              new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);  
				           mVideo.setLayoutParams(layoutParams);
				           getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
				          ((ImageView) view.findViewById(R.id.full_btn)).setImageResource(R.drawable.window_bg);
				          liangdu.setVisibility(view.VISIBLE);
				          yingliang.setVisibility(view.VISIBLE);
				          
					}else{
						content.setVisibility(View.VISIBLE);
						foot.setVisibility(View.VISIBLE);
						getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
						((ImageView) view.findViewById(R.id.full_btn)).setImageResource(R.drawable.fullscreen_bg);
					}
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
        playVideo("http://video.mukewang.com/08c927f4-c06f-420d-865a-897f741f6350/L.mp4");
        
        view.findViewById(R.id.bottom_mk_attention).setOnClickListener(new View.OnClickListener(){
        	int i=0;
        	@Override
			public void onClick(View view) {
				if(i==0){
					Toast.makeText(context, "关注成功！", Toast.LENGTH_SHORT).show();
					((ImageView)view.findViewById(R.id.bottom_mk_attention)).setImageResource(R.drawable.focus_course_click1);
					i=1;
				}else{
					Toast.makeText(context, "已取消关注！", Toast.LENGTH_SHORT).show();
					((ImageView)view.findViewById(R.id.bottom_mk_attention)).setImageResource(R.drawable.focus_course_unclick1);
					i=0;
				}
			}
        });
        view.findViewById(R.id.bottom_mk_back).setOnClickListener(new View.OnClickListener(){
        	@Override
        	public void onClick(View view) {
    		 Context context=view.getContext();
				if(!((FragmentActivity) context).getSupportFragmentManager().popBackStackImmediate()){
					((Activity) context).finish();
				}
        	}
        });
        view.findViewById(R.id.bottom_mk_note).setOnClickListener(new View.OnClickListener(){
        	@Override
        	public void onClick(View view) {
        		  //实例化SelectPicPopupWindow  
        		notePopupWindow = new NotePopupWindow(getActivity(), itemsOnClick);  
                //显示窗口  
        		notePopupWindow.showAtLocation(getActivity().findViewById(R.id.drawerLayout), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0,0); //设置layout在PopupWindow中显示的位置  
        	}
        });
        
		return view;
		
	}
	//为弹出窗口实现监听类  
    private OnClickListener  itemsOnClick = new OnClickListener(){  
  
        public void onClick(View v) {  
        	notePopupWindow.dismiss();  
            switch (v.getId()) {  
            case R.id.cacel:  
                break;  
            case R.id.send:                 
                break;  
            default:  
                break;  
            }  
              
                  
        }  
          
    };  
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
			height = DensityUtil.getWidthInPx(context);
			width = DensityUtil.getHeightInPx(context);
		} else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
			width = DensityUtil.getWidthInPx(context);
			height = DensityUtil.getHeightInPx(context);
		}
		super.onConfigurationChanged(newConfig);
	}


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
	
	private void backward(float delataX) {
		int current = mVideo.getCurrentPosition();
		int backwardTime = (int) (delataX / width * mVideo.getDuration());
		int currentTime = current - backwardTime;
		mVideo.seekTo(currentTime);
		mSeekBar.setProgress(currentTime * 100 / mVideo.getDuration());
		mPlayTime.setText(formatTime(currentTime));
	}

	private void forward(float delataX) {
		int current = mVideo.getCurrentPosition();
		int forwardTime = (int) (delataX / width * mVideo.getDuration());
		int currentTime = current + forwardTime;
		mVideo.seekTo(currentTime);
		mSeekBar.setProgress(currentTime * 100 / mVideo.getDuration());
		mPlayTime.setText(formatTime(currentTime));
	}

	private void volumeDown(float delatY) {
		int max = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		int current = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
		int down = (int) (delatY / height * max * 3);
		int volume = Math.max(current - down, 0);
		mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, volume, 0);
		int transformatVolume = volume * 100 / max;
		volumnController.show(transformatVolume);
	}

	private void volumeUp(float delatY) {
		int max = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		int current = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
		int up = (int) ((delatY / height) * max * 3);
		int volume = Math.min(current + up, max);
		mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, volume, 0);
		int transformatVolume = volume * 100 / max;
		volumnController.show(transformatVolume);
	}

	private void lightDown(float delatY) {
		int down = (int) (delatY / height * 255 * 3);
		int transformatLight = LightnessController.getLightness(getActivity()) - down;
		LightnessController.setLightness(getActivity(), transformatLight);
	}

	private void lightUp(float delatY) {
		int up = (int) (delatY / height * 255 * 3);
		int transformatLight = LightnessController.getLightness(getActivity()) + up;
		LightnessController.setLightness(getActivity(), transformatLight);
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		mHandler.removeMessages(0);
		mHandler.removeCallbacksAndMessages(null);
		getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}

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
		mVideo.setOnTouchListener(mTouchListener);
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
	private float mLastMotionX;
	private float mLastMotionY;
	private int startX;
	private int startY;
	private int threshold;
	private boolean isClick = true;

	private OnTouchListener mTouchListener = new OnTouchListener() {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			final float x = event.getX();
			final float y = event.getY();

			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				mLastMotionX = x;
				mLastMotionY = y;
				startX = (int) x;
				startY = (int) y;
				break;
			case MotionEvent.ACTION_MOVE:
				float deltaX = x - mLastMotionX;
				float deltaY = y - mLastMotionY;
				float absDeltaX = Math.abs(deltaX);
				float absDeltaY = Math.abs(deltaY);
				// 声音调节标识
				boolean isAdjustAudio = false;
				if (absDeltaX > threshold && absDeltaY > threshold) {
					if (absDeltaX < absDeltaY) {
						isAdjustAudio = true;
					} else {
						isAdjustAudio = false;
					}
				} else if (absDeltaX < threshold && absDeltaY > threshold) {
					isAdjustAudio = true;
				} else if (absDeltaX > threshold && absDeltaY < threshold) {
					isAdjustAudio = false;
				} else {
					return true;
				}
				if (isAdjustAudio) {
					if (x < width / 2) {
						if (deltaY > 0) {
							lightDown(absDeltaY);
						} else if (deltaY < 0) {
							lightUp(absDeltaY);
						}
					} else {
						if (deltaY > 0) {
							volumeDown(absDeltaY);
						} else if (deltaY < 0) {
							volumeUp(absDeltaY);
						}
					}

				} else {
					if (deltaX > 0) {
						forward(absDeltaX);
					} else if (deltaX < 0) {
						backward(absDeltaX);
					}
				}
				mLastMotionX = x;
				mLastMotionY = y;
				break;
			case MotionEvent.ACTION_UP:
				if (Math.abs(x - startX) > threshold
						|| Math.abs(y - startY) > threshold) {
					isClick = false;
				}
				mLastMotionX = 0;
				mLastMotionY = 0;
				startX = (int) 0;
				if (isClick) {
					showOrHide();
				}
				isClick = true;
				break;

			default:
				break;
			}
			return true;
		}

	};


	private void showOrHide() {
		if (mBottomView.getVisibility() == View.VISIBLE) {
			mBottomView.clearAnimation();
			Animation animation = AnimationUtils.loadAnimation(getActivity(),
					R.anim.option_leave_from_bottom);
			animation.setAnimationListener(new AnimationImp() {
				@Override
				public void onAnimationEnd(Animation animation) {
					super.onAnimationEnd(animation);
					mBottomView.setVisibility(View.GONE);
					liangdu.setVisibility(View.GONE);
			        yingliang.setVisibility(View.GONE);
				}
			});
			mBottomView.startAnimation(animation);
		}else{
			mBottomView.setVisibility(View.VISIBLE);
			mBottomView.clearAnimation();
			Animation animation = AnimationUtils.loadAnimation(getActivity(),
					R.anim.option_entry_from_bottom);
			mBottomView.startAnimation(animation);
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
		content=(LinearLayout)view.findViewById(R.id.content);
		foot=(LinearLayout)view.findViewById(R.id.foot);
		liangdu=(LinearLayout)view.findViewById(R.id.liangdu);
		yingliang=(LinearLayout)view.findViewById(R.id.yingliang);
		
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
