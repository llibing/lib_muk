package com.lib_muk.utils.frameAnim;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.annotation.SuppressLint;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;

/**逐帧动画，一次性消耗，播放完后不能再执行
 * Android自带的DrawableAnimation会OOM且不好控制播放 */
public class FrameAnimation implements Animatable{
	List<Frame> mFrames=new ArrayList<Frame>();
	FrameAnimListener mListener;
	int mCurrentFrameIndex=-1;
//	Frame mCurrentFrame;
	View mView;
	InnerTask animTask = new InnerTask();
	private boolean isPause;
	private boolean isFinish = true;
	private boolean isOneShot = true;
	public FrameAnimation(View view){
		this.mView=view;
	}
	public FrameAnimation(View view, List<Frame> frames){
		this.mView=view;
		this.mFrames.addAll(frames);
	}
	public FrameAnimation(View view, FrameList frames){
		this.mView=view;
		this.mFrames.addAll(frames);
		isOneShot = (frames.isOneShot());
	}
	private boolean isReverse = false;
	public void reverseFrames(){
		isReverse = !isReverse;
		Collections.reverse(mFrames);
	}
	public List<Frame> getAllFrames(){
		return mFrames;
	}
	public void addFrame(Frame frame){
		mFrames.add(frame);
	}
	public void setFrames(List<Frame> frames){
		mFrames.clear();
		isReverse = false;
		mFrames.addAll(frames);
	}
	public void addFrames(List<Frame> frames){
		mFrames.addAll(frames);
	}
	public void setFrameAnimListener(FrameAnimListener listener){
		mListener=listener;
	}
	public void pause(){
		isPause=true;
	}
	public void resume(){
		isPause=false;
	}
	public Frame getCurrentFrame(){
		if(mCurrentFrameIndex<0 || mCurrentFrameIndex>=mFrames.size()) return null;
		return mFrames.get(mCurrentFrameIndex);
	}
	
	
	public boolean isOneShot() {
		return isOneShot;
	}
	public boolean isFinish(){
		return isFinish;
	}
	public void setOneShot(boolean isOneShot) {
		this.isOneShot = isOneShot;
	}

	@Override
	public void start() {
		if(animTask!=null) animTask.cancel(true);
		mCurrentFrameIndex = 0;
		animTask = new InnerTask();
		animTask.execute();
		isFinish = false;
		if(mListener!=null) mListener.onStart(this);
	}
	@Override
	public void stop() {
		animTask.cancel(true);
	}
	@Override
	public boolean isRunning() {
		return animTask.isCancelled() || isFinish();
	}
	
	@SuppressLint("NewApi")
	public static void setDrawableToView(View view , Drawable drawable){
        if(view instanceof ImageView){
			((ImageView)view).setImageDrawable(drawable);
		}else{
			if(Build.VERSION.SDK_INT>=16) view.setBackground(drawable);
			else view.setBackgroundDrawable(drawable);
		}
	}
	/**运行和处理动画逻辑 */
	class InnerTask extends AsyncTask<Void, Frame, Void>{
		int decodingFrameIndex;
		/**获得待展示的帧 */
		private Frame getPreShowFrame(){
			if(mFrames.size()==0){
				isFinish=true;
				return null;
			}
			while(decodingFrameIndex == mCurrentFrameIndex+1){//主线程还没有收到上一次publish的图片，等候
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return null;
			}
//			int mCurrentFrameIndex = mCurrentFrame==null ? -1:mFrames.indexOf(mCurrentFrame);
			decodingFrameIndex = mCurrentFrameIndex+1;//获得下一帧
			if(decodingFrameIndex >= mFrames.size() -1){//到尾了
				if(isOneShot) isFinish=true;//结束
				else decodingFrameIndex=0;//重复
			}
			return mFrames.get(decodingFrameIndex);
		}
		
		private void playFrame(){
			Frame frame = getPreShowFrame();
			if(frame==null) return;
			long timeBeforeDecode=System.currentTimeMillis();
//			Log.d("fax", "decodingFrame,Index:"+decodingFrameIndex);
			frame.decodeDrawable(mView.getContext());
			publishProgress(frame);
			try {
				long timeDecodeUse = System.currentTimeMillis()-timeBeforeDecode;
				long sleep = frame.getDuration()-timeDecodeUse;
				if(sleep>0) Thread.sleep(sleep);
			} catch (Exception ignored) {
			}
		}
		@Override
		protected Void doInBackground(Void... params) {
			while(true){
				while(isPause){
					try { Thread.sleep(500);
					} catch (InterruptedException e) {
					}
				}
				
				playFrame();
				if(isFinish){
					break;
				}
			}
			return null;
		}
		LruCache<Integer, Frame> cacheFrames = new LruCache<Integer, Frame>(2){
			@Override
			protected void entryRemoved(boolean evicted, Integer key, Frame oldValue, Frame newValue) {
				super.entryRemoved(evicted, key, oldValue, newValue);
				if(oldValue!=null) oldValue.recycle();
//				Log.d("fax", "recycle frame:"+key);
			}
		};
		@SuppressLint("NewApi")
		@Override
		protected void onProgressUpdate(Frame... values) {
			Frame lastFrame = getCurrentFrame();
			if(lastFrame!=null){
				cacheFrames.put(mCurrentFrameIndex, lastFrame);
			}
			
			Frame frame=values[0];
			Drawable drawable = frame.getDecodedDrawable();
			setDrawableToView(mView, drawable);
			mCurrentFrameIndex = decodingFrameIndex;
//			Log.d("fax", "setDrawableToView, mCurrentFrameIndex:"+mCurrentFrameIndex);
			if(mListener!=null){
				int position = mCurrentFrameIndex;
				if(isReverse) position = mFrames.size() -1 - position;
				mListener.onPlaying(FrameAnimation.this, position);
			}
			if(isFinish){
				if(mListener!=null) mListener.onFinish(FrameAnimation.this);
			}
		}
		@SuppressLint("NewApi")
		public void execute(){
			if(android.os.Build.VERSION.SDK_INT<11) super.execute();
			else executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
		}
		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			isFinish = true;
		}
	}
}