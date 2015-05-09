package com.lib_muk.pulldownmenu;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.fax.utils.http.HttpUtils;
import com.fax.utils.task.ResultAsyncTask;
import com.google.gson.Gson;
import com.lib_muk.MyApp;
import com.lib_muk.R;
import com.lib_muk.model.CommitEntity;
import com.lib_muk.model.NoteEntity;
import com.lib_muk.model.VideoEntity;

import android.app.Activity;  
import android.content.Context;  
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;  
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;  
import android.view.MotionEvent;  
import android.view.View;  
import android.view.View.OnClickListener;  
import android.view.View.OnTouchListener;  
import android.view.ViewGroup.LayoutParams;  
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;  
import android.widget.Toast;

public class NotePopupWindow extends PopupWindow {  
	  
	  
    private ImageView cacel, send;  
    public EditText content_txt;  
    private View mMenuView;
    SharedPreferences sp=MyApp.getDefaultSp();
    
    public NotePopupWindow(final Activity context,OnClickListener itemsOnClick,final VideoEntity videoEntity) {  
        super(context);  
        LayoutInflater inflater = (LayoutInflater) context  
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
        mMenuView = inflater.inflate(R.layout.home_mk_list_detail_note, null);  
        cacel = (ImageView) mMenuView.findViewById(R.id.cacel);  
        send = (ImageView) mMenuView.findViewById(R.id.send);  
        content_txt = (EditText) mMenuView.findViewById(R.id.content_txt);  
        //取消按钮  
        cacel.setOnClickListener(new OnClickListener() {  
            public void onClick(View v) {  
                //销毁弹出框  
                dismiss();  
            }  
        });  
        //设置按钮监听  
//        send.setOnClickListener(itemsOnClick);  
        send.setOnClickListener(new OnClickListener() {  
            public void onClick(View v) {
            	if (TextUtils.isEmpty(content_txt.getText().toString())) {
					Toast.makeText(context, "请先填写笔记！", Toast.LENGTH_SHORT).show();
					return;
				}
                //销毁弹出框  
//                dismiss();  
            	new ResultAsyncTask<NoteEntity>(context) {
        			@Override
        			protected NoteEntity doInBackground(Void... params) {
        				ArrayList<NameValuePair> pairslist=new ArrayList<NameValuePair>();
        				pairslist.add(new BasicNameValuePair("notescontent",content_txt.getText().toString()));
        				pairslist.add(new BasicNameValuePair("studentEntity.id",sp.getString(MyApp.USER_ID, null)));
        				pairslist.add(new BasicNameValuePair("videoEntity.Id",videoEntity.getId()));
        				String json=HttpUtils.reqForPost(MyApp.Host+"notesController.do?uploadnote", pairslist);
        				try {
        					return new Gson().fromJson(json, NoteEntity.class);
        				} catch (Exception e) {
        				}
        				return null;
        			}
        			@Override
        			protected void onPostExecuteSuc(NoteEntity result) {
        				if(result.getSuccess().equals("true")){
        					Toast.makeText(context, "笔记提交成功！", Toast.LENGTH_SHORT).show();
        					dismiss();
        				}
        			}
        			@Override
        			protected void onPostExecuteFail(NoteEntity result) {
        			}
        		}.setProgressDialog().execute();
            }  
        });
        //设置SelectPicPopupWindow的View  
        this.setContentView(mMenuView);  
        //设置SelectPicPopupWindow弹出窗体的宽  
        this.setWidth(LayoutParams.FILL_PARENT);  
        //设置SelectPicPopupWindow弹出窗体的高  
        this.setHeight(LayoutParams.WRAP_CONTENT);  
        //设置SelectPicPopupWindow弹出窗体可点击  
        this.setFocusable(true);  
        //设置SelectPicPopupWindow弹出窗体动画效果  
        this.setAnimationStyle(R.style.AnimBottom);  
        //实例化一个ColorDrawable颜色为半透明  
        ColorDrawable dw = new ColorDrawable(0xb0000000);  
        //设置SelectPicPopupWindow弹出窗体的背景  
        this.setBackgroundDrawable(dw);  
        //mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框  
        mMenuView.setOnTouchListener(new OnTouchListener() {  
            public boolean onTouch(View v, MotionEvent event) {  
                int height = mMenuView.findViewById(R.id.pop_layout).getTop();  
                int y=(int) event.getY();  
                if(event.getAction()==MotionEvent.ACTION_UP){  
                    if(y<height){  
                        dismiss();  
                    }  
                }                 
                return true;  
            }  
        });  
  
    }  
  
}  
