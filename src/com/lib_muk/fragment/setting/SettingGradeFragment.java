package com.lib_muk.fragment.setting;


import com.lib_muk.MyFragment;
import com.lib_muk.R;
import com.lib_muk.model.SlidingItem;
import com.lib_muk.views.MyTopBar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * 
 * 关于我们
 * 
*/

public class SettingGradeFragment extends MyFragment{
	public static final String TAG ="SettingGradeFragment";
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		 final View view = inflater.inflate(R.layout.setting_mk_list_grade, container, false);
		 final RadioGroup radioGroup=(RadioGroup) view.findViewById(R.id.radioGroup);
		 radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			    @Override 
				 public void onCheckedChanged(RadioGroup group, int checkedId) {
			    	RadioButton radioButton = (RadioButton)view.findViewById(radioGroup.getCheckedRadioButtonId());
			    	 String grade=radioButton.getText().toString();
			    	 Toast.makeText(context, grade, Toast.LENGTH_SHORT).show();
				 }
			});
		 
		return new MyTopBar(context).setLeftBack().setTitle("满意度调查").setContentView(view);
	}
}
