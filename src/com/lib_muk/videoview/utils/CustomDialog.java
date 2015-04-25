package com.lib_muk.videoview.utils;
 

import com.lib_muk.R;
import com.lib_muk.clickshow.ClickShowFrameLayout;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
 
/**
 * 
 * 为您的应用程序创建自定义对话框窗口
 * 自定义对话框依靠自定义布局文件允许您创建和使用自己的外观和感觉.
 * 
 */
public class CustomDialog extends Dialog {
 
    public CustomDialog(Context context, int theme) {
        super(context, theme);
    }
 
    public CustomDialog(Context context) {
        super(context);
    }
 
    /**
     *  Builder类创建一个自定义对话框
     */
    public static class Builder {
 
        private Context context;
        private String title;
        private String titleDetail;
        private String message;
        private String positiveButtonText;
        private String negativeButtonText;
        private View contentView;
        private int icon;
 
        private DialogInterface.OnClickListener 
                        positiveButtonClickListener,
                        negativeButtonClickListener;
 
        public Builder(Context context) {
            this.context = context;
        }
 
        /**
         * 设置对话框的消息内容
         * @param title
         * @return
         */
        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }
 
        /**
         * 设置对话框的信息资源
         * @param title
         * @return
         */
        public Builder setMessage(int message) {
            this.message = (String) context.getText(message);
            return this;
        }
 
        /**
         * 设置对话框的title资源
         * @param title
         * @return
         */
        public Builder setTitle(int title) {
            this.title = (String) context.getText(title);
            return this;
        }
 
        /**
         * 设置对话框的title内容
         * @param title
         * @return
         */
        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }
        public Builder setTitleDetail(String titleDetail) {
        	this.titleDetail = titleDetail;
        	return this;
        }
 
        /**
         * 设置一个自定义内容视图对话框。
         * 如果设置一个消息,contentView不添加到对话框……
         * @param v
         * @return
         */
        public Builder setContentView(View v) {
            this.contentView = v;
            return this;
        }
        /**
         * 设置一个自定义内容视图对话框。
         * 如果设置一个消息,contentView不添加到对话框……
         * @param v
         * @return
         */
        public Builder setIcon(int icon) {
        	this.icon = icon;
        	return this;
        }
 
        /**
         * 设置PositiveButton的按钮侦听器
         * @param positiveButtonText
         * @param listener
         * @return
         */
        public Builder setPositiveButton(int positiveButtonText,
                DialogInterface.OnClickListener listener) {
            this.positiveButtonText = (String) context
                    .getText(positiveButtonText);
            this.positiveButtonClickListener = listener;
            return this;
        }
 
        /**
         * @param positiveButtonText
         * @param listener
         * @return
         */
        public Builder setPositiveButton(String positiveButtonText,
                DialogInterface.OnClickListener listener) {
            this.positiveButtonText = positiveButtonText;
            this.positiveButtonClickListener = listener;
            return this;
        }
 
        /**
         * 设置NegativeButton的按钮侦听器
         * @param negativeButtonText
         * @param listener
         * @return
         */
        public Builder setNegativeButton(int negativeButtonText,
                DialogInterface.OnClickListener listener) {
            this.negativeButtonText = (String) context
                    .getText(negativeButtonText);
            this.negativeButtonClickListener = listener;
            return this;
        }
 
        /**
         * @param negativeButtonText
         * @param listener
         * @return
         */
        public Builder setNegativeButton(String negativeButtonText,
                DialogInterface.OnClickListener listener) {
            this.negativeButtonText = negativeButtonText;
            this.negativeButtonClickListener = listener;
            return this;
        }
 
        /**
         * 创建自定义对话框
         */
        public CustomDialog create() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // 实例化自定义主题的对话框
            final CustomDialog dialog = new CustomDialog(context, 
            		R.style.DialogTheme);
            dialog.setCanceledOnTouchOutside(false);
            View layout = inflater.inflate(R.layout.custom_dialog_layout, null);
            dialog.addContentView(layout, new LayoutParams(
                    LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
            // 设置对话框title
            if(title != null){
            	((TextView) layout.findViewById(R.id.title)).setText(title);
            	((TextView) layout.findViewById(R.id.title_detail)).setText(titleDetail);
            	((ImageView) layout.findViewById(R.id.icon)).setImageResource(icon);
//            	((ImageView) layout.findViewById(R.id.icon)).setVisibility(View.VISIBLE);
            }else{
            	((LinearLayout) layout.findViewById(R.id.titleicon)).setVisibility(View.GONE);
            }
            // 设置对话框icon
            // 设置确认按钮
            if (positiveButtonText != null) {
                ((TextView) layout.findViewById(R.id.positivetext))
                        .setText(positiveButtonText);
                if (positiveButtonClickListener != null) {
                    ((ClickShowFrameLayout) layout.findViewById(R.id.positiveButton))
                            .setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    positiveButtonClickListener.onClick(
                                    		dialog, 
                                            DialogInterface.BUTTON_POSITIVE);
                                }
                            });
                }
            } else {
                // 如果没有确认按钮设置可见性了
                layout.findViewById(R.id.positiveButton).setVisibility(
                        View.GONE);
            }
            // 设置取消按钮
            if (negativeButtonText != null) {
                ((TextView) layout.findViewById(R.id.negativetext))
                        .setText(negativeButtonText);
                if (negativeButtonClickListener != null) {
                    ((ClickShowFrameLayout) layout.findViewById(R.id.negativeButton))
                            .setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                	negativeButtonClickListener.onClick(
                                    		dialog, 
                                            DialogInterface.BUTTON_NEGATIVE);
                                }
                            });
                }
            } else {
                // 如果没有确认按钮设置可见性了
                layout.findViewById(R.id.negativeButton).setVisibility(
                        View.GONE);
            }
            // 设置内容信息
            if (message != null) {
                ((TextView) layout.findViewById(
                		R.id.message)).setText(message);
                ((LinearLayout) layout.findViewById(R.id.content)).setVisibility(View.VISIBLE);
            } else if (contentView != null) {
                // 如果没有消息添加contentView的对话框
                ((LinearLayout) layout.findViewById(R.id.content))
                        .removeAllViews();
                ((LinearLayout) layout.findViewById(R.id.content))
                        .addView(contentView, 
                                new LayoutParams(
                                        LayoutParams.MATCH_PARENT, 
                                        LayoutParams.MATCH_PARENT));
                ((LinearLayout) layout.findViewById(R.id.content)).setVisibility(View.VISIBLE);
            }
            dialog.setContentView(layout);
            return dialog;
        }
 
    }
 
}