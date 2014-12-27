package com.lib_muk;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import com.fax.utils.bitmap.BitmapManager;
import com.fax.utils.http.HttpUtils;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

public class MyApp extends Application {
	private static MyApp myApp;

	@Override
	public void onCreate() {
		super.onCreate();
		myApp=this;
		HttpUtils.DEBUG = BuildConfig.DEBUG;//FIXME 正式版去掉
		CrashHandler.getInstance().init(getApplicationContext());
		BitmapManager.init(this, null, null, getExternalCacheDir());
		HttpUtils.setWebViewCookieStore(this);
	}
	public static SharedPreferences getDefaultSp(){
		return PreferenceManager.getDefaultSharedPreferences(myApp);
	}
	/**
	 * 判断是否已经写入过这个key，没写入就现在写入
	 * @param key 值
	 * @return 是否之前已写入
	 */
	public static boolean hasKeyOnce(String key){
		SharedPreferences sp = getDefaultSp();
		
		boolean hasKey = sp.getBoolean(key, false);
		sp.edit().putBoolean(key, true).commit();
		return hasKey;
	}
	
	
	public static float convertToDp(int value){
		return myApp.getResources().getDisplayMetrics().density * value;
	}
	

	/**完全删除一个文件夹*/
	public static void delectFile(File file){
		if(file==null) return;
		if(file.isDirectory()){
			File[] files = file.listFiles();
			for(File f:files){
				delectFile(f);
			}
			file.delete();
		}else{
			file.delete();
		}
	}

	public static String imei(){
		String str = ((TelephonyManager)myApp.getSystemService("phone")).getDeviceId();
		if(str==null||str.length()==0){
			SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(myApp);
			long imei=sp.getLong("imei", -1);
			if(imei<0){
				imei=new Random().nextInt(Integer.MAX_VALUE);
				sp.edit().putLong("imei", imei).commit();
			}
			return imei+"";
		}
		return str;
	}
	/**
	 * 获得当前版本的versionCode
	 */
	public static String getVersionName(){
		try {
			return myApp.getPackageManager().getPackageInfo(myApp.getPackageName(), 0).versionName;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Unkown";
	}
	/**关闭系统输入法*/
	public static final void hideKeyBoard(Context context,View view){
		if(view==null || context ==null) return;
		InputMethodManager inputManager = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
		inputManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
	}
	/**弹出系统输入法*/
	public static void showSystemInputBord(EditText et){
		et.requestFocusFromTouch();
		InputMethodManager inputManager = (InputMethodManager) et.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
		inputManager.showSoftInput(et, 0);
	}
	/**快速创建Fragment实例 */
	public static Fragment createFragment(Class<? extends Fragment> c, Bundle bundle){
		try {
			Fragment f = c.newInstance();
			f.setArguments(bundle);
			return f;
		} catch (Exception e) {
		}
		return null;
	}
	/**快速创建Fragment实例 */
	public static Fragment createFragment(Class<? extends Fragment> c, Serializable... ss){
		return createFragment(c, createIntent(ss).getExtras());
	}
	/**快速绑定传递的数据 */
	public static Intent createIntent(Serializable... ss){
		Intent intent = new Intent();
		for(Serializable s : ss){
			if(s!=null) intent.putExtra(s.getClass().getName(), s);
		}
		return intent;
	}
	/**快速绑定传递的数据 */
	public static Bundle createBundle(Serializable... ss){
		return createIntent(ss).getExtras();
	}
	
	public static File getCrashDir() {
		return myApp.getExternalCacheDir().getParentFile();
	}
	/**
	 * UncaughtException处理类,当程序发生Uncaught异常的时候,有该类来接管程序,并记录发送错误报告.
	 * 
	 * @author user
	 * 
	 */
	static class CrashHandler implements UncaughtExceptionHandler {
		
		public static final String TAG = "CrashHandler";
		
		//系统默认的UncaughtException处理类 
		private Thread.UncaughtExceptionHandler mDefaultHandler;
		//CrashHandler实例
		private static CrashHandler INSTANCE = new CrashHandler();
		//程序的Context对象
		private Context mContext;
		//用来存储设备信息和异常信息
		private Map<String, String> infos = new HashMap<String, String>();
	
		//用于格式化日期,作为日志文件名的一部分
		private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
	
		/** 保证只有一个CrashHandler实例 */
		private CrashHandler() {
		}
	
		/** 获取CrashHandler实例 ,单例模式 */
		public static CrashHandler getInstance() {
			return INSTANCE;
		}
	
		/**
		 * 初始化
		 * 
		 * @param context
		 */
		public void init(Context context) {
			mContext = context;
			//获取系统默认的UncaughtException处理器
			mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
			//设置该CrashHandler为程序的默认处理器
			Thread.setDefaultUncaughtExceptionHandler(this);
		}
	
		/**
		 * 当UncaughtException发生时会转入该函数来处理
		 */
		@Override
		public void uncaughtException(Thread thread, Throwable ex) {
			if (!handleException(ex) && mDefaultHandler != null) {
				//如果用户没有处理则让系统默认的异常处理器来处理
				mDefaultHandler.uncaughtException(thread, ex);
			} else {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					Log.e(TAG, "error : ", e);
				}
				//退出程序
				android.os.Process.killProcess(android.os.Process.myPid());
				System.exit(1);
			}
		}
	
		/**
		 * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
		 * 
		 * @param ex
		 * @return true:如果处理了该异常信息;否则返回false.
		 */
		private boolean handleException(Throwable ex) {
			if (ex == null) {
				return false;
			}
			//使用Toast来显示异常信息
			new Thread() {
				@Override
				public void run() {
					Looper.prepare();
					Toast.makeText(mContext, "捕获到了异常，已记录到"+MyApp.getCrashDir().getPath(), Toast.LENGTH_LONG).show();
					Looper.loop();
				}
			}.start();
			//收集设备参数信息 
			collectDeviceInfo(mContext);
			//保存日志文件 
			saveCrashInfo2File(ex);
			return true;
		}
		
		/**
		 * 收集设备参数信息
		 * @param ctx
		 */
		public void collectDeviceInfo(Context ctx) {
			try {
				PackageManager pm = ctx.getPackageManager();
				PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(), PackageManager.GET_ACTIVITIES);
				if (pi != null) {
					String versionName = pi.versionName == null ? "null" : pi.versionName;
					String versionCode = pi.versionCode + "";
					infos.put("versionName", versionName);
					infos.put("versionCode", versionCode);
				}
			} catch (NameNotFoundException e) {
				Log.e(TAG, "an error occured when collect package info", e);
			}
			Field[] fields = Build.class.getDeclaredFields();
			for (Field field : fields) {
				try {
					field.setAccessible(true);
					infos.put(field.getName(), field.get(null).toString());
					Log.d(TAG, field.getName() + " : " + field.get(null));
				} catch (Exception e) {
					Log.e(TAG, "an error occured when collect crash info", e);
				}
			}
		}
	
		/**
		 * 保存错误信息到文件中
		 * 
		 * @param ex
		 * @return	返回文件名称,便于将文件传送到服务器
		 */
		private String saveCrashInfo2File(Throwable ex) {
			
			StringBuffer sb = new StringBuffer();
			for (Map.Entry<String, String> entry : infos.entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();
				sb.append(key + "=" + value + "\n");
			}
			
			Writer writer = new StringWriter();
			PrintWriter printWriter = new PrintWriter(writer);
			ex.printStackTrace(printWriter);
			Throwable cause = ex.getCause();
			while (cause != null) {
				cause.printStackTrace(printWriter);
				cause = cause.getCause();
			}
			printWriter.close();
			String result = writer.toString();
			Log.e("fax", result);
			sb.append(result);
			try {
				long timestamp = System.currentTimeMillis();
				String time = formatter.format(new Date());
				String fileName = "crash-" + time + "-" + timestamp + ".log";
				if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
					File dir = MyApp.getCrashDir();
					if (!dir.exists()) {
						dir.mkdirs();
					}
					FileOutputStream fos = new FileOutputStream(new File(dir.getPath(),fileName));
					fos.write(sb.toString().getBytes());
					fos.close();
				}
				return fileName;
			} catch (Exception e) {
				Log.e(TAG, "an error occured while writing file...", e);
			}
			return null;
		}
	}
}
