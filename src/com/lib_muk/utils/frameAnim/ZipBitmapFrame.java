package com.lib_muk.utils.frameAnim;

import java.io.InputStream;
import java.util.zip.ZipFile;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ZipBitmapFrame extends BasicBitmapFrame {
	ZipFile zipFile;
	String entryName;
	public ZipBitmapFrame (ZipFile zipFile, String entryName){
		this.zipFile = zipFile;
		this.entryName = entryName;
	}
	public ZipBitmapFrame (ZipFile zipFile, String entryName, int duration){
		super(duration);
		this.zipFile = zipFile;
		this.entryName = entryName;
	}
	@Override
	protected Bitmap decodeBitmap(Context context) throws Exception {
		InputStream is = zipFile.getInputStream(zipFile.getEntry(entryName));
		return BitmapFactory.decodeStream(is);
	}

}
