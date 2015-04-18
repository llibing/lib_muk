package com.lib_muk.model;

import java.io.Serializable;

import com.lib_muk.R;
/**
 * 左侧菜单
 * @author libing
 *
 */

public class HomeAllCourse implements Serializable{
	public static final HomeAllCourse homeAllCourse_webview1=new HomeAllCourse(0,"http://img.mukewang.com/549bafc10001d8ee06000338-590-330.jpg","WebView实战详解","大范甘迪公司的广告撒打发斯蒂芬费","http://218.195.208.149/mp4files/607900000041658F/video.mukewang.com/121f25e1-bc67-4161-8453-a0fea616d753/L.mp4","1");
	public static final HomeAllCourse homeAllCourse_webview2=new HomeAllCourse(1,"http://img.mukewang.com/549bafc10001d8ee06000338-590-330.jpg","WebView","","http://218.195.208.149/mp4files/607900000041658F/video.mukewang.com/121f25e1-bc67-4161-8453-a0fea616d753/L.mp4","");
	public static final HomeAllCourse homeAllCourse_webview3=new HomeAllCourse(2,"http://img.mukewang.com/549bafc10001d8ee06000338-590-330.jpg","httpClict","","http://218.195.208.149/mp4files/607900000041658F/video.mukewang.com/121f25e1-bc67-4161-8453-a0fea616d753/L.mp4","");
	public static final HomeAllCourse homeAllCourse_webview4=new HomeAllCourse(3,"http://img.mukewang.com/549bafc10001d8ee06000338-590-330.jpg","android-json","","http://218.195.208.149/mp4files/607900000041658F/video.mukewang.com/121f25e1-bc67-4161-8453-a0fea616d753/L.mp4","");
	public static final HomeAllCourse homeAllCourse_webview5=new HomeAllCourse(4,"http://img.mukewang.com/549bafc10001d8ee06000338-590-330.jpg","java","","http://218.195.208.149/mp4files/607900000041658F/video.mukewang.com/121f25e1-bc67-4161-8453-a0fea616d753/L.mp4","");
	public static final HomeAllCourse[] homeAllCourse_ITEM = new HomeAllCourse[]{
		homeAllCourse_webview1,
		homeAllCourse_webview2,
		homeAllCourse_webview3,
		homeAllCourse_webview4,
		homeAllCourse_webview5,
	};
	public static final HomeAllCourse[] homeAllCourse_ITEMmycourse = new HomeAllCourse[]{
		homeAllCourse_webview1,
	};
	int id;
	String imgId;
	String allCourseName;
	String allCourseDescription;
	String videoSrc;
	String attention;
	
	public HomeAllCourse(int id, String imgId, String allCourseName,
			String allCourseDescription, String videoSrc,String attention) {
		super();
		this.id = id;
		this.imgId = imgId;
		this.allCourseName = allCourseName;
		this.allCourseDescription = allCourseDescription;
		this.videoSrc = videoSrc;
		this.attention=attention;
	}
	public String getAttention() {
		return attention;
	}
	public void setAttention(String attention) {
		this.attention = attention;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getImgId() {
		return imgId;
	}
	public void setImgId(String imgId) {
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
