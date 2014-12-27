package com.lib_muk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.FrameLayout;

public class FragmentContain extends FragmentActivity{
	public static final String Extra_ClassName = "ClassName";
    public static void start(Activity activity, Class<? extends Fragment> c){
        start(activity, c, new Bundle(), 0);
    }
    public static void start(Activity activity, Fragment f){
        start(activity, f.getClass(), f.getArguments(), 0);
    }
    public static void start(Activity activity, Fragment f, int request){
        start(activity, f.getClass(), f.getArguments(), request);
    }
    public static void start(Activity activity, Class<? extends Fragment> c, int request){
    	start(activity, c, new Intent(), request);
    }
    public static void start(Activity activity, Class<? extends Fragment> c, Intent args, int request){
    	start(activity, c, args.getExtras(), request);
    }
    public static void start(Activity activity, Class<? extends Fragment> c, Bundle args, int request){
        activity.startActivityForResult(new Intent(activity, FragmentContain.class)
                .putExtra(Extra_ClassName, c)
                .putExtras(args==null?new Bundle():args), request);
    }

    public static void start(Fragment f, Class<? extends Fragment> c, int request){
    	start(f, c, new Intent(), request);
    }
    public static void start(Fragment f, Class<? extends Fragment> c, Intent args, int request){
    	if(args==null) args= new Intent();
    	start(f, c, args.getExtras(), request);
    }
    public static void start(Fragment f, Class<? extends Fragment> c, Bundle args, int request){
    	if(args==null) args= new Bundle();
        f.startActivityForResult(new Intent(f.getActivity(), FragmentContain.class)
                .putExtra(Extra_ClassName, c)
                .putExtras(args==null?new Bundle():args), request);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contain);

        try {
            Fragment fragment = ((Class<? extends Fragment>)getIntent().getSerializableExtra(Extra_ClassName)).newInstance();
            getIntent().removeExtra(Extra_ClassName);
            Bundle b= getIntent().getExtras();
            fragment.setArguments(b == null? new Bundle():b);
            getSupportFragmentManager().beginTransaction().replace(R.id.contain, fragment).commit();
        } catch (Exception e) {
        }
    }
}
