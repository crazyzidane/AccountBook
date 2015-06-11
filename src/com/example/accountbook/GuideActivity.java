package com.example.accountbook;

import java.util.ArrayList;
import java.util.List;

/**
 * 进入主界面前的引导界面
 * ViewPager
 */

import com.example.accountbook.tools.ViewPagerAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GuideActivity extends Activity {
	
	private ViewPager mViewPager;
	List<View> mListView;
	ViewPagerAdapter mAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewpager_guide);
		
		//动态加载viewpager的三个布局文件，图片分别放入布局中，作为背景
		LayoutInflater mInflater = getLayoutInflater().from(this);
		View item1 = mInflater.inflate(R.layout.viewpager_item1, null);
		View item2 = mInflater.inflate(R.layout.viewpager_item2, null);
		View item3 = mInflater.inflate(R.layout.viewpager_item3, null);
		
		//添加页面数据
		mListView = new ArrayList<View>();
		mListView.add(item1);
		mListView.add(item2);
		mListView.add(item3);
		
		//实例化适配器
		mViewPager = (ViewPager) findViewById(R.id.view_guide);
		mAdapter = new ViewPagerAdapter(mListView);
		mViewPager.setAdapter(mAdapter);
		
		//设置默认当前项
		mViewPager.setCurrentItem(0);
		
		//对最后一个页面的Button进行处理
		View view = mListView.get(2);
		
		//注意此处是获取动态加载的界面中的button，漏掉view会出错
		Button mBtnGoMain = (Button) view.findViewById(R.id.btn_gomain);
		mBtnGoMain.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GoToMainActivity();
			}

		});
		
		
	}
	
	
	//进入主界面
	private void GoToMainActivity(){
		Intent intent = new Intent();
		intent.setClass(GuideActivity.this, MainActivity.class);
		startActivity(intent);
		finish();
	}
	

}
