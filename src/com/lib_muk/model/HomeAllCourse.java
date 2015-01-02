package com.lib_muk.model;

import java.io.Serializable;

import com.lib_muk.R;
/**
 * 左侧菜单
 * @author libing
 *
 */

public class HomeAllCourse implements Serializable{
	public static final HomeAllCourse homeAllCourse_webview1=new HomeAllCourse(0,R.drawable.sliding_allcourse_icon,"Android中的WebView实战详解","","http://218.195.208.149/mp4files/607900000041658F/video.mukewang.com/121f25e1-bc67-4161-8453-a0fea616d753/L.mp4");
	public static final HomeAllCourse homeAllCourse_webview2=new HomeAllCourse(1,R.drawable.sliding_mycourse_icon,"Android中的WebView实战详解","","http://218.195.208.149/mp4files/607900000041658F/video.mukewang.com/121f25e1-bc67-4161-8453-a0fea616d753/L.mp4");
	public static final HomeAllCourse homeAllCourse_webview3=new HomeAllCourse(2,R.drawable.sliding_message_icon,"Android中的WebView实战详解","","http://218.195.208.149/mp4files/607900000041658F/video.mukewang.com/121f25e1-bc67-4161-8453-a0fea616d753/L.mp4");
	public static final HomeAllCourse homeAllCourse_webview4=new HomeAllCourse(3,R.drawable.sliding_download_icon,"Android中的WebView实战详解","","http://218.195.208.149/mp4files/607900000041658F/video.mukewang.com/121f25e1-bc67-4161-8453-a0fea616d753/L.mp4");
	public static final HomeAllCourse homeAllCourse_webview5=new HomeAllCourse(4,R.drawable.sliding_setting_icon,"Android中的WebView实战详解","","http://218.195.208.149/mp4files/607900000041658F/video.mukewang.com/121f25e1-bc67-4161-8453-a0fea616d753/L.mp4");
	public static final HomeAllCourse[] homeAllCourse_ITEM = new HomeAllCourse[]{
		homeAllCourse_webview1,
		homeAllCourse_webview2,
		homeAllCourse_webview3,
		homeAllCourse_webview4,
		homeAllCourse_webview5,
	};
	int id;
	int imgId;
	String allCourseName;
	String allCourseDescription;
	String videoSrc;
	
	public HomeAllCourse(int id, int imgId, String allCourseName,
			String allCourseDescription, String videoSrc) {
		super();
		this.id = id;
		this.imgId = imgId;
		this.allCourseName = allCourseName;
		this.allCourseDescription = allCourseDescription;
		this.videoSrc = videoSrc;
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
	public String getAllCourseName() {
		return allCourseName;
	}
	public void setAllCourseName(String allCourseName) {
		this.allCourseName = allCourseName;
	}
	public String getAllCourseDescription() {
		return allCourseDescription;
	}
	public void setAllCourseDescription(String allCourseDescription) {
		this.allCourseDescription = allCourseDescription;
	}
	public String getVideoSrc() {
		return videoSrc;
	}
	public void setVideoSrc(String videoSrc) {
		this.videoSrc = videoSrc;
	}

}
