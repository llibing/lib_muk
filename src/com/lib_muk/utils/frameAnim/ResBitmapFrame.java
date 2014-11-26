package com.lib_muk.utils.frameAnim;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ResBitmapFrame extends BasicBitmapFrame{
	int mResId;
	public ResBitmapFrame(int duration, int resId) {
		super(duration);
		mResId = resId;
	}

	public ResBitmapFrame(int resId) {
		super();
		mResId = resId;
	}

	@Override
	protected Bitmap decodeBitmap(Context context) throws Exception {
		return BitmapFactory.decodeResource(context.getResources(), mResId);
	}

    @Override
    protected Bitmap decodePreviewBitmap(Context context) throws Exception {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        options.inSampleSize = 3;
        return BitmapFactory.decodeResource(context.getResources(), mResId, options);
    }
}
