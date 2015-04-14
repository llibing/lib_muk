package com.lib_muk.model;

import java.io.Serializable;

import com.lib_muk.R;
/**
 * 左侧菜单
 * @author libing
 *
 */

public class SlidingItem implements Serializable{
	public static final SlidingItem sliding_allcourse=new SlidingItem(0,R.drawable.sliding_allcourse_icon,"全部课程");
	public static final SlidingItem sliding_mycourse=new SlidingItem(1,R.drawable.sliding_mycourse_icon,"我的课程");
	public static final SlidingItem sliding_message=new SlidingItem(2,R.drawable.sliding_message_icon,"我的消息");
	public static final SlidingItem sliding_upload=new SlidingItem(3,R.drawable.sliding_upload_icon,"作业动态");
	public static final SlidingItem sliding_download=new SlidingItem(4,R.drawable.sliding_download_icon,"下载");
	public static final SlidingItem sliding_setting=new SlidingItem(5,R.drawable.sliding_setting_icon,"设置");
	public static final SlidingItem[] sliding_ITEMS = new SlidingItem[]{
		sliding_allcourse,
		sliding_mycourse,
		sliding_message,
		sliding_upload,
		sliding_download,
		sliding_setting,
	};
	int id;
	int imgId;
	String slidingName;
	public SlidingItem(int id,int imgId, String slidingName) {
		super();
		this.id=id;
		this.imgId = imgId;
		this.slidingName = slidingName;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getImgId() {
		return imgId;
	}
	public void setImgId(int imgId) {
		this.imgId = imgId;
	}
	public String getSlidingName() {
		return slidingName;
	}
	public void setSlidingName(String slidingName) {
		this.slidingName = slidingName;
	}
	

}
