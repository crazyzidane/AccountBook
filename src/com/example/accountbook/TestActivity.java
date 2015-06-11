package com.example.accountbook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Timer;






import com.example.accountbook.ExpenseTrendView;
import com.example.accountbook.R;
import com.example.accountbook.tools.Tools;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TestActivity extends Activity {
	
	
	private ActionBarDrawerToggle drawerbar;
	public DrawerLayout drawerLayout;

	private RelativeLayout left_menu_layout, right_xiaoxi_layout;
	
	private TextView text;
	
	public ImageButton menuBtn,mesBtn;


/*	ExpenseTrendView tu;
	//Button BT_Add;
	Timer mTimer =new Timer();
	HashMap<String, Float> map;
	Double key=8.0;
	Double value=0.0;
	Tools tool=new Tools();*/
	
	
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test);
		
		initView();
		initEvent();
		
	}
	
	
	
	
	
	public void initView() {
		//testFragment = new TestFragment();
		//FragmentManager fragmentManager = getSupportFragmentManager();
		//FragmentTransaction f_transaction = fragmentManager.beginTransaction();
		//f_transaction.replace(R.id.main_content_frame_parent, testFragment);
		//f_transaction.commitAllowingStateLoss();
		
		initMainLayout();
		
		initLeftLayout();
		initRightLayout();
	}
	
	public void initMainLayout(){
    	menuBtn=(ImageButton)findViewById(R.id.menu_btn);
    	mesBtn=(ImageButton)findViewById(R.id.xiaoxi_btn);
    	
    	//点击打开左边菜单
    	menuBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				openLeftLayout();
			}
		});
    	//点击打开右边菜单
    	mesBtn.setOnClickListener(new View.OnClickListener() {
    		
    		@Override
    		public void onClick(View v) {
    			openRightLayout();
    		}
    	});
    	
    }

	public void initLeftLayout() {
		drawerLayout = (DrawerLayout) findViewById(R.id.main_drawer_layout);
		// 设置透明
		drawerLayout.setScrimColor(0x00000000);
		// 左边菜单
		left_menu_layout = (RelativeLayout) findViewById(R.id.main_left_drawer_layout);
		View view2 = getLayoutInflater().inflate(R.layout.menu_layout, null);
		text = (TextView) view2.findViewById(R.id.text);
		text.setText("左边测试菜单");
		left_menu_layout.addView(view2);
	}

	public void initRightLayout() {
		// 左边菜单
		right_xiaoxi_layout = (RelativeLayout) findViewById(R.id.main_right_drawer_layout);
		View view = getLayoutInflater().inflate(R.layout.xiaoxi_layout, null);
		text = (TextView) view.findViewById(R.id.text);
		text.setText("右边测试菜单");
		right_xiaoxi_layout.addView(view);
	}

	private void initEvent() {
		drawerbar = new ActionBarDrawerToggle(this, drawerLayout,
				R.drawable.ic_launcher, R.string.open, R.string.close) {
			@Override
			public void onDrawerOpened(View drawerView) {

				super.onDrawerOpened(drawerView);
			}

			@Override
			public void onDrawerClosed(View drawerView) {

				super.onDrawerClosed(drawerView);
			}
		};
		drawerLayout.setDrawerListener(drawerbar);
	}

	public void openLeftLayout() {
		if (drawerLayout.isDrawerOpen(left_menu_layout)) {
			drawerLayout.closeDrawer(left_menu_layout);
		} else {
			drawerLayout.openDrawer(left_menu_layout);

		}
	}

	// 消息开关事件
	public void openRightLayout() {
		if (drawerLayout.isDrawerOpen(right_xiaoxi_layout)) {
			drawerLayout.closeDrawer(right_xiaoxi_layout);
		} else {
			drawerLayout.openDrawer(right_xiaoxi_layout);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
		//BT_Add=(Button)findViewById(R.id.bt_add);
/*		tu= (ExpenseTrendView)findViewById(R.id.menulist);
		tu.SetTuView(map,50,10,"","元",false);	//设置绘图的相关初始参数
		map=new HashMap<String, Float>();
		map.put("01-02", (float) 0);	//初始图的坐标值
    	map.put("01-03", 3.23f);
    	map.put("01-04", 32.0f);
    	map.put("01-05", 41.0f);
    	map.put("01-06", 16.0f);
    	map.put("01-07", 36.0f);
    	map.put("01-08", 26.0f);
    	tu.setTotalvalue(50);	
    	tu.setPjvalue(10);
    	tu.setMap(map);*/
//		tu.setXstr("");
//		tu.setYstr("");
    	
		//tu.setMargint(20);
		//tu.setMarginb(50);
		
    	//tu.setMstyle(Mstyle.Line);
	
		//BT_Add.setOnClickListener(new click());
    	
    	
    	
/*	class click implements OnClickListener
	{

		@Override
		public void onClick(View v) {
			Random rd=new Random(System.currentTimeMillis());
			Double temp= rd.nextDouble();
			randmap(map, temp*50);
		}}*/
	
	
}
