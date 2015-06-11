package com.example.accountbook.tools;

import java.util.List;

import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class ViewPagerAdapter extends PagerAdapter {
	
	private List<View> mListView;
	
	

	public ViewPagerAdapter(List<View> mListView) {
		super();
		this.mListView = mListView;
	}

	//获取当前窗体的界面数
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mListView.size();
	}

	
	//判断是否由对象生成界面
	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}
	
	
	//初始化position位置的界面
	@Override
	public Object instantiateItem(View container, int position) {
		// TODO Auto-generated method stub
		((ViewGroup)container).addView(mListView.get(position), 0);
		return mListView.get(position);
	}
	
	
	// 销毁position位置的界面
	@Override
	public void destroyItem(View container, int position, Object object) {
		// TODO Auto-generated method stub
		((ViewGroup) container).removeView(mListView.get(position));
	}

	@Override
	public void restoreState(Parcelable arg0, ClassLoader arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public Parcelable saveState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void startUpdate(View arg0) {
		// TODO Auto-generated method stub

	}
	

}
