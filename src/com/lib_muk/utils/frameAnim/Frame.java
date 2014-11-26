package com.lib_muk.utils.frameAnim;

import android.content.Context;
import android.graphics.drawable.Drawable;

public interface Frame {
	public static final int DefaultDuration = 50;
	/**这个帧的时长 */
	public int getDuration();
    /**解析出这个帧,一般是BitmapDrawable */
    public Drawable decodeDrawable(Context context);
    /**解析出预览帧,一般是BitmapDrawable */
    public Drawable decodePreviewDrawable(Context context);
	/**获得已解析出的图像,一般是BitmapDrawable */
	public Drawable getDecodedDrawable();
	/**回收这个帧 */
	public void recycle();
}
