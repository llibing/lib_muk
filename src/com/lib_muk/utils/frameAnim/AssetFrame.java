package com.lib_muk.utils.frameAnim;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class AssetFrame extends BasicBitmapFrame {
	String mPath;
	public AssetFrame(String assetPath) {
		super();
		this.mPath=assetPath;
	}
	public AssetFrame(int duration, String assetPath) {
		super(duration);
		this.mPath=assetPath;
	}

	@Override
	protected Bitmap decodeBitmap(Context context) throws Exception{
		return BitmapFactory.decodeStream(context.getAssets().open(mPath));
	}

    @Override
    protected Bitmap decodePreviewBitmap(Context context) throws Exception {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        options.inSampleSize = 3;
        return BitmapFactory.decodeStream(context.getAssets().open(mPath), null, options);
    }

}
