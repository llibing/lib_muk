package com.lib_muk.utils.view.pager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class ViewsPagerAdapter extends PagerAdapter {
	private ArrayList<View> mListViews;

	public ViewsPagerAdapter(Context context, int... layoutResIds) {
		View[] views = new View[layoutResIds.length];
		for (int i = 0, size = views.length; i < size; i++) {
			views[i] = View.inflate(context, layoutResIds[i], null);
		}
		this.mListViews = new ArrayList<View>(Arrays.asList(views));// 构造方法，参数是我们的页卡，这样比较方便。
	}

	public ViewsPagerAdapter(View... views) {
		this.mListViews = new ArrayList<View>(Arrays.asList(views));// 构造方法，参数是我们的页卡，这样比较方便。
	}

	public ViewsPagerAdapter(List<? extends View> mListViews) {
		this.mListViews = new ArrayList<View>(mListViews);// 构造方法，参数是我们的页卡，这样比较方便。
	}

	public void addView(View view) {
		mListViews.add(view);
		notifyDataSetChanged();
	}

	public View getView(int position) {
		if (position < 0 || position > mListViews.size() || mListViews == null)
			return null;
		return mListViews.get(position);
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView(mListViews.get(position));// 删除页卡
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) { // 这个方法用来实例化页卡
		container.addView(mListViews.get(position), 0);// 添加页卡
		return mListViews.get(position);
	}

	@Override
	public int getCount() {
		return mListViews.size();// 返回页卡的数量
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;// 官方提示这样写
	}
}