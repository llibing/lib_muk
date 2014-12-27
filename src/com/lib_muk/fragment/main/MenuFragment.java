package com.lib_muk.fragment.main;


import com.fax.utils.view.TopBarContain;
import com.lib_muk.MainActivity;
import com.lib_muk.R;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

public class MenuFragment extends Fragment {
	Context context;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context=getActivity();
	}

	public void showMenu(){
		if(getActivity() instanceof MainActivity){
			((MainActivity)getActivity()).showMenu();
		}
	}

	public void switchFragment(int position){
		if(getActivity() instanceof MainActivity){
			((MainActivity)getActivity()).switchFragment(position);
		}
	}
	public void switchFragment(Fragment fragment){
		if(getActivity() instanceof MainActivity){
			((MainActivity)getActivity()).switchFragment(fragment);
		}
	}
	public TopBarContain createTopBarContain(int layoutResId){
		return new TopBarContain(context).setTitle(getActivity().getTitle()).setContentView(layoutResId)
				.setLeftBtn(R.drawable.topbar_menu, new View.OnClickListener() {
					public void onClick(View v) {
						showMenu();
					}
				});
	}
}
