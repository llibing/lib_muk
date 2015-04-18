package com.lib_muk.model;

import java.math.BigDecimal;
import java.util.Date;

public class CoursesEntity implements java.io.Serializable {
	/** id */
	private java.lang.String id;
	/** 计划阶段外键 */
	// private java.lang.String planstageId;
	/** 课程子类外键 */
	// private java.lang.String coursecatlogId;
	/** 教师外键 */
	// private java.lang.String teacherId;
	/** 课程名称 */
	private java.lang.String coursename;
	/** 封面图片 */
	private java.lang.String imgsrc;
	/** 创建时间 */
	private java.util.Date createtime;
	/** 课程简介 */
	private java.lang.String courseabout;
	/** 课程难度 */
	private java.lang.String coursediffcult;
	/** 课程须知 */
	private java.lang.String coursenote;
	/** 能学到什么 */
	private java.lang.String whatlearn;

}
