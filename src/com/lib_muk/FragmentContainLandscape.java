package com.lib_muk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;


public class FragmentContainLandscape extends FragmentContain{
    public static void start(Activity activity, Class<? extends Fragment> c){
        start(activity, c, new Bundle(), 0);
    }
    public static void start(Activity activity, Fragment f){
        start(activity, f.getClass(), f.getArguments(), 0);
    }
    public static void start(Activity activity, Class<? extends Fragment> c, Intent args, int request){
    	start(activity, c, args.getExtras(), request);
    }
    public static void start(Activity activity, Class<? extends Fragment> c, Bundle args, int request){
        activity.startActivityForResult(new Intent(activity, FragmentContainLandscape.class)
                .putExtra(Extra_ClassName, c)
                .putExtras(args==null?new Bundle():args), request);
    }

    public static void start(Fragment f, Class<? extends Fragment> c, Intent args, int request){
    	start(f, c, args.getExtras(), request);
    }
    public static void start(Fragment f, Class<? extends Fragment> c, Bundle args, int request){
        f.startActivityForResult(new Intent(f.getActivity(), FragmentContainLandscape.class)
                .putExtra(Extra_ClassName, c)
                .putExtras(args==null?new Bundle():args), request);
    }
}
