package com.lib_muk.fragment.myinfo;


import com.lib_muk.MyFragment;
import com.lib_muk.R;
import com.lib_muk.model.MessagesEntityList.MessagesEntity;
import com.lib_muk.model.VideoEntityList.VideoEntity;
import com.lib_muk.views.MyTopBar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * TODO
 * 我的消息 页卡 */

public class MyInfoDetailFragment extends MyFragment{
	public static final int Request_Home = 1;
	public static final String TAG ="MyCourseFragment";
	TextView info_title,info_time,info_detail;
	MessagesEntity m;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.myinfo_fragment_main_detail, container, false);
		
		m=(MessagesEntity) getArguments().getSerializable(MessagesEntity.class.getName());
		
		info_title = (TextView) view.findViewById(R.id.info_title);
		info_time=(TextView)view.findViewById(R.id.info_time);
		info_detail=(TextView)view.findViewById(R.id.info_detail);
		info_title.setText(m.getTeacherEntity_realName());
		info_time.setText(m.getMessagename());
		info_detail.setText(m.getMessagecontent());
		return new MyTopBar(context).setLeftBack().setTitle("消息内容").setContentView(view);
	}
}
