package com.lib_muk.fragment.homework;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import com.fax.utils.bitmap.BitmapManager;
import com.lib_muk.MyApp;
import com.lib_muk.MyFragment;
import com.lib_muk.R;
import com.lib_muk.videoview.utils.NativeImageLoader;
import com.lib_muk.videoview.utils.NativeImageLoader.NativeImageCallBack;
import com.lib_muk.views.MyTopBar;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout.LayoutParams;

/**
 * TODO
 * 家庭作业    内页*/

public class HomeWorkDetailFragment extends MyFragment{
	private String[] items = new String[] { "选择本地图片", "拍照" };; 
	private LinearLayout login_content = null;
	private Point mPoint ;//用来封装ImageView的宽和高的对象
	
	//myHomework
	File imagesFile;
	private LinearLayout myHorizontal = null;
	private LinearLayout text_type;
	private LinearLayout text_type_medias;
	private ArrayList<String> pathlist;
	/** 拍照 */
	private static final int TakingPictures = 0;
	/** 摄像 */
	private static final int CAMERA = 1;
	/** 选择本地图片 */
	private static final int IMAGE_REQUEST_CODE = 2;
	/** 选择本地视频 */
    private static final int CAMERA_REQUEST_CODE = 3;
	TextView camera_text;
	EditText topic_nam,content_txt;
	File personalFile;
	
	
	//headerworkpager
		TextView title_name,title_time,title_detail,total_time;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.homework_fragment_main_detal, container, false);
		init(view);
		//上传作业
				view.findViewById(R.id.commit_button).setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View arg0) {
							if (pathlist.size() == 0) {
								Toast.makeText(context, "请先填写作业！", Toast.LENGTH_SHORT).show();
								return;
							}
					   }
				});
			view.findViewById(R.id.camera).setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View arg0) {
							TakingPicturesShowDialog();
					}
			});
		return new MyTopBar(context).setLeftBack().setTitle("作业内容").setContentView(view);
	}
	
	
	private void TakingPicturesShowDialog() {
		new AlertDialog.Builder(context)
		.setTitle("        请选择！")
		.setItems(items, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				switch (which) {
				case 0:
					Intent intentFromGallery = new Intent(Intent.ACTION_GET_CONTENT);
					intentFromGallery.setType("image/*"); // 设置文件类型
					startActivityForResult(intentFromGallery,IMAGE_REQUEST_CODE);
					break;
				case 1:
					Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
					if (MyApp.hasSdcard()) {
						imagesFile = new File(Environment.getExternalStorageDirectory(),System.currentTimeMillis()+".jpg");
						intentFromCapture.putExtra( MediaStore.EXTRA_OUTPUT,Uri.fromFile(imagesFile));
					}
					startActivityForResult(intentFromCapture,TakingPictures);
					break;
				}
			}
		}).setNegativeButton("取消", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		}).show();
		
	}
	
	private void init(View view){
		//headerworkpager
		title_name = (TextView) view.findViewById(R.id.title_name);
		title_time = (TextView) view.findViewById(R.id.title_time);
		
		
		myHorizontal = (LinearLayout) view.findViewById(R.id.myHorizontal);
		text_type_medias = (LinearLayout) view.findViewById(R.id.medias);
		login_content = (LinearLayout) view.findViewById(R.id.login_content);
		camera_text = (TextView) view.findViewById(R.id.camera_text);
		topic_nam = (EditText) view.findViewById(R.id.topic_nam);
		content_txt = (EditText) view.findViewById(R.id.content_txt);
		pathlist = new ArrayList<String>();
		
	}
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
            switch (requestCode) {
            case TakingPictures:
                if (MyApp.hasSdcard() && imagesFile.length()!=0) {
        	        pathlist.add(imagesFile.getPath());
        	        myHorizontal.setVisibility(View.VISIBLE);
        	        updateNetWorkImgLayout(myHorizontal,pathlist);
                }else if(!MyApp.hasSdcard()){
                    Toast.makeText(context, "未找到存储卡，无法存储照片！",Toast.LENGTH_LONG).show();
                }
                break;
            case IMAGE_REQUEST_CODE:
            	if (data != null) {
            		Uri uri=data.getData();
            		String[] proj = { MediaStore.Images.Media.DATA };
            		Cursor cursor = getActivity().managedQuery(uri, proj, null, null,null);
            		int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            		cursor.moveToFirst();
            		String path=cursor.getString(column_index);
            	    personalFile=new File(path);
            	    pathlist.add(personalFile.getPath());
            	    myHorizontal.setVisibility(View.VISIBLE);
            	    updateNetWorkImgLayout(myHorizontal,pathlist);
            	}
            	break;
            }
            super.onActivityResult(requestCode, resultCode, data);
    }
	
	 /**
	   * 更新图片布局
	   * @param <T>
	   */
	  private <T> void updateNetWorkImgLayout(LinearLayout imageHorizontal ,ArrayList<T> list){
		  imageHorizontal.removeAllViews();
		  for (int i = 0; i < list.size(); i++) {
			  ImageView img = new ImageView(context);
	    		  try {
	    			  saveFile(imagesFile.getPath());
	    		  } catch (Exception e) {
	    			  e.printStackTrace();
	    		  }
	    		  setImageBitmap(img, pathlist.get(i).toString());
	    		  imageHorizontal.addView(img);
	    		  setImgLayoutParams(img,i,list);
		  }
	  }
	
	  /**
	   * 设置ImageView的尺寸布局
	 * @param <T>
	   */
	  private <T> void setImgLayoutParams(final ImageView img,int i,final ArrayList<T> list) {
	      LayoutParams lp = new LayoutParams(0,0);
	      lp.width = (login_content.getMeasuredWidth()-80)/3;
	      lp.height = lp.width;
//	      lp.width = (screenWidth-120)/3;
//	      lp.height = lp.width;
	      mPoint = new Point(lp.width, lp.height);
	      lp.rightMargin = 5;
	      lp.leftMargin=5;
	      lp.bottomMargin = 10;
	      img.setLayoutParams(lp);
	      final int t=i;
	      img.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					ImageView img1 = new ImageView(context);
						img1.setImageBitmap(BitmapFactory.decodeFile(pathlist.get(t).toString()));
	                	new AlertDialog.Builder(context).setTitle("图片详情").setView(img1)
	                	.setNegativeButton(android.R.string.ok, null)
						.setPositiveButton("删除", new DialogInterface.OnClickListener() {
		                    @Override
		                    public void onClick(DialogInterface dialog, int which) {
		                    	pathlist.remove(t);
								img.setVisibility(View.GONE);
								updateNetWorkImgLayout(myHorizontal,pathlist);
		                         }
				           }).show();
				  }
			});
	      img.setScaleType(ScaleType.CENTER_CROP);
	  }
	  
	//存储进SD卡
	    public void saveFile(String fileName) throws Exception {
	    	Bitmap bitmap=BitmapFactory.decodeFile(fileName);
			if(bitmap==null) return;
			Bitmap newBitmap=scaleBitmap(bitmap);
	        File dirFile = new File(fileName);  
	        //检测图片是否存在
	        if(dirFile.exists()){  
	            dirFile.delete();  //删除原图片
	        }  
	        File myCaptureFile = new File(fileName);  
	        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));  
	        //100表示不进行压缩，80表示压缩率为20%
	        newBitmap.compress(Bitmap.CompressFormat.JPEG, 80, bos);  
	        bos.flush();  
	        bos.close();  
	    }  
	    
	    
	  /**
	   * 为ImageView设置图片
	   */
	  private void setImageBitmap(final ImageView img,String url){
			
			Bitmap bitmap = NativeImageLoader.getInstance().loadNativeImage(url, mPoint , new NativeImageCallBack() {
				public void onImageLoader(Bitmap bitmap, String path) {
					if(bitmap != null && img != null){  
						img.setImageBitmap(bitmap);  
					}
				}
			});
			if(bitmap != null){  
				img.setImageBitmap(bitmap); 
			}else{
				img.setImageResource(R.drawable.pictures_no);
			    } 
			
	  }
	  
	//将图片控制在1024x768之内
		private Bitmap scaleBitmap(Bitmap in){
			int widthLimit=768;
			int heightLimit=1024;
			int inWidth=in.getWidth();
			int inHeight=in.getHeight();
			if(inWidth<=widthLimit&&inHeight<=heightLimit) return in;
			float scale=Math.min(widthLimit*1f/inWidth,heightLimit*1f/inHeight);
			Bitmap re=Bitmap.createScaledBitmap(in, (int)(inWidth*scale), (int) (inHeight*scale), true);
			in.recycle();
			return re;
		}
}
