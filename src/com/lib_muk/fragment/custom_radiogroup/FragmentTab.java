package com.lib_muk.fragment.custom_radiogroup;

import com.lib_muk.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentTab extends Fragment{
	
	public String msg;
	
	TextView txt_msg=null;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState){
		View view = inflater.inflate(R.layout.fragment, container, false);
		txt_msg = (TextView)view.findViewById(R.id.txt_msg);
		txt_msg.setText(msg);
		return view;
	}
	
}


