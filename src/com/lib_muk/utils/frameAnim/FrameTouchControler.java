package com.lib_muk.utils.frameAnim;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by linfaxin on 2014/7/9 009.
 * Email: linlinfaxin@163.com
 * 控制view随着touch改变bg
 */
public class FrameTouchControler {

    /**
     * 控制view随着touch改变bg
     */
    public static void controlView(final View view, final List<Frame> frames){
    	controlView(view, frames, 0, null);
    }
    /**
     * 控制view随着touch改变bg
     */
    public static void controlView(final View view, final List<Frame> frames, TouchControlerListener listener){
    	controlView(view, frames, 0, listener);
    }
    /**
     * 控制view随着touch改变bg
     */
    public static void controlView(final View view, final List<Frame> frames,final int initIndex, final TouchControlerListener listener){
        final Context context=view.getContext();
        Drawable drawable = (frames.get(initIndex).decodeDrawable(context));
        FrameAnimation.setDrawableToView(view, drawable);
        
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetector.onTouchEvent(event);

                if(event.getAction()==MotionEvent.ACTION_UP){
                    if(decodeRun!=null){
                        handler.removeCallbacks(decodeRun);
                        decodeRun.run();
                    }
                }
                return true;
            }
            Runnable decodeRun=new Runnable() {
                @Override
                public void run() {
                    FrameAnimation.setDrawableToView(view, frames.get(index).decodeDrawable(context));
                }
            };
            Handler handler=new Handler();
            final int oneFrameRange = context.getResources().getDisplayMetrics().widthPixels / 36;
            final int framesSize = frames.size();
            int index=initIndex;
            int startIndex=initIndex;

            final GestureDetector gestureDetector=new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){
                @Override
                public boolean onDown(MotionEvent e) {
                    startIndex=index;
                    return true;
                }

        		ArrayList<Frame> cacheFrames = new ArrayList<Frame>();
                @Override
                public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                    float distance=e2.getX()-e1.getX();
                    int nextIndex = (int) (startIndex + (distance / oneFrameRange)) % framesSize;
                    if(nextIndex<0) nextIndex+=framesSize;
                    if(nextIndex != index){
                    	cacheFrames.add(frames.get(index));
                    	while(cacheFrames.size()>2){//缓存2帧，避免意外的draw recycle bitmap
        					cacheFrames.remove(0).recycle();
        				}
                        index = nextIndex;

                        if(decodeRun!=null) handler.removeCallbacks(decodeRun);

                        Drawable drawable = frames.get(index).decodePreviewDrawable(context);
                        if(drawable != BasicBitmapFrame.DecodeFailDrawable){
                            FrameAnimation.setDrawableToView(view, drawable);
                            handler.postDelayed(decodeRun, 300);
                        }else decodeRun.run();
                        if(listener != null) listener.onFrameChange(frames.get(index), index);
                    }
                    return true;
                }
            });
        });
    }
    public interface TouchControlerListener{
    	public void onFrameChange(Frame frame, int index);
    }
}
