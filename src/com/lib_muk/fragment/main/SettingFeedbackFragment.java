package com.lib_muk.fragment.main;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import com.lib_muk.MyFragment;
import com.lib_muk.R;
import com.lib_muk.views.MyTopBar;

//意见反馈
public class SettingFeedbackFragment extends MyFragment{
	@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			 View view = inflater.inflate(R.layout.setting_mk_list_feedback, container, false);
			MyTopBar topBar = (MyTopBar) new MyTopBar(context).setLeftBack()
					.setTitle("意见反馈").setContentView(view);
			//TODO 数据绑定、提交
			return topBar;
		}
}
