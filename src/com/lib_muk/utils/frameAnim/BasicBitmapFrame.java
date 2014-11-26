package com.lib_muk.utils.frameAnim;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

public abstract class BasicBitmapFrame implements Frame {
	int mDuration = DefaultDuration;
	private static Bitmap DecodeFailBitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ALPHA_8);
	public static BitmapDrawable DecodeFailDrawable = new BitmapDrawable(DecodeFailBitmap);
    BitmapDrawable mBitmapDrawable;
    BitmapDrawable mBitmapPreviewDrawable;
	public BasicBitmapFrame(int duration){
		if(duration>=0) mDuration=duration;
	}
	public BasicBitmapFrame(){
	}
	@Override
	public int getDuration() {
		return mDuration;
	}
	public void setDuration(int duration){
		this.mDuration=duration;
	}

    @Override
    public Drawable decodeDrawable(Context context) {
        if(mBitmapDrawable==null){
            try {
                mBitmapDrawable=new BitmapDrawable(context.getResources(), decodeBitmap(context));
                recycleDrawable(mBitmapPreviewDrawable);
                mBitmapPreviewDrawable=null;
            } catch (Exception e) {
                mBitmapDrawable=DecodeFailDrawable;
                e.printStackTrace();
            }
        }
        return mBitmapDrawable;
    }
    @Override
    public Drawable decodePreviewDrawable(Context context) {
        if(mBitmapPreviewDrawable==null){
            try {
            	Bitmap bitmap = decodePreviewBitmap(context);
                if(bitmap!=null)
                	mBitmapPreviewDrawable=new BitmapDrawable(context.getResources(), bitmap);
                else 
                    mBitmapPreviewDrawable=DecodeFailDrawable;
            } catch (Exception e) {
                mBitmapPreviewDrawable=DecodeFailDrawable;
            }
        }
        return mBitmapPreviewDrawable;
    }
	/**解析出Bitmap，只会调用一次
	 * @throws Exception */
	protected abstract Bitmap decodeBitmap(Context context) throws Exception;
    /**解析出预览Bitmap，只会调用一次，没有显式的调用就不需要这个方法
     * @throws Exception */
    protected Bitmap decodePreviewBitmap(Context context) throws Exception{return null;}

	@Override
	public Drawable getDecodedDrawable() {
		return mBitmapDrawable;
	}

	@Override
	public void recycle() {
        recycleDrawable(mBitmapPreviewDrawable);
        mBitmapPreviewDrawable=null;
		recycleDrawable(mBitmapDrawable);
        mBitmapDrawable=null;
	}
    private void recycleDrawable(BitmapDrawable drawable){
        if(drawable==DecodeFailDrawable) return;
        Bitmap bitmap;
        if(drawable!=null && (bitmap=drawable.getBitmap())!=null){
            if(bitmap!=DecodeFailBitmap) bitmap.recycle();
        }
    }

}
