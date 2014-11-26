package com.lib_muk.utils.frameAnim;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;

public class FrameFactory {
	public static List<Frame> createFramesFromZip(ZipFile zipFile, String entryNameFormat, int start, int end, int duration){
		ArrayList<Frame> frames = new ArrayList<Frame>();
		for(int i=start;i<end;i++){
			ZipEntry zipEntry = zipFile.getEntry(String.format(entryNameFormat, i));
			if(zipEntry!=null) frames.add(new ZipBitmapFrame(zipFile, zipEntry.getName(), duration));
		}
		return frames;
	}
	public static List<Frame> createFramesFromZip(ZipFile zipFile, String entryDir, int duration){
		ArrayList<Frame> frames = new ArrayList<Frame>();
		for(ZipEntry entry : Collections.list(zipFile.entries())){
			if(!entry.isDirectory() && entry.getName().startsWith(entryDir)){
				frames.add(new ZipBitmapFrame(zipFile, entry.getName(), duration));
			}
		}
		return frames;
	}
	
	public static List<Frame> createFramesFromAsset(Context context, String dir, int duration){
		try {
			return createFramesFromAsset(dir, context.getAssets().list(dir), duration);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ArrayList<Frame>();
	}
	public static List<Frame> createFramesFromAsset(String dir, String[] fileNames, int duration){
		ArrayList<Frame> frames=new ArrayList<Frame>();
		for(String name:fileNames){
			frames.add(new AssetFrame(duration, dir+"/"+name));
		}
		return frames;
	}
	public static List<Frame> createFramesFromAsset(String[] paths, int duration){
		ArrayList<Frame> frames=new ArrayList<Frame>();
		for(String path:paths){
			frames.add(new AssetFrame(duration, path));
		}
		return frames;
	}
	
	public static FrameList createFrameFromAnimRes(Context context, int animResId){
		FrameList frames=new FrameList();
		
		XmlResourceParser parser=context.getResources().getXml(animResId);
        AttributeSet attrs = Xml.asAttributeSet(parser);

        int type;
        try { while ((type = parser.next()) != XmlPullParser.START_TAG && type != XmlPullParser.END_DOCUMENT) {
			}
		} catch (Exception e) {
		}
        
		Resources r=context.getResources();
        TypedArray a = r.obtainAttributes(attrs,getInnernalResIntArray("AnimationDrawable"));
            
        boolean mOneShot = a.getBoolean(getInnernalResInt("AnimationDrawable_oneshot"), true);
        frames.setOneShot(mOneShot);
        
        a.recycle();
        
        final int innerDepth = parser.getDepth()+1;
        int depth;
        try {
			while ((type=parser.next()) != XmlPullParser.END_DOCUMENT &&
			        ((depth = parser.getDepth()) >= innerDepth || type != XmlPullParser.END_TAG)) {
			    if (type != XmlPullParser.START_TAG) {
			        continue;
			    }

			    if (!parser.getName().equals("item")) {
			        continue;
			    }
			    
			    a = r.obtainAttributes(attrs, getInnernalResIntArray("AnimationDrawableItem"));
			    int duration = a.getInt(getInnernalResInt("AnimationDrawableItem_duration") , -1);
			    int drawableRes = a.getResourceId(getInnernalResInt("AnimationDrawableItem_drawable") , 0);
			    a.recycle();
			    if(drawableRes!=0){
			    	if (duration >= 0) {//时间>=0代表正常图片
			            frames.add(new ResBitmapFrame(duration, drawableRes));
			        } else {//时间<0代表动画组
			            frames.addAll(createFrameFromAnimRes(context, drawableRes));
			        }
			    }
			}
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        return frames;
	}
	
	private static int[] getInnernalResIntArray(String res){
		return (int[]) getInnernalRes(res);
	}
	private static int getInnernalResInt(String res){
		return (Integer) getInnernalRes(res);
	}
	private static Object getInnernalRes(String res){
		try {
			String className="com.android.internal.R$styleable";
			if(res.startsWith(className)) res=res.substring(className.length());
			Class styleable=Class.forName(className);
			return styleable.getField(res).get(styleable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}


