package com.example.accountbook.tools;

import android.content.Context;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.accountbook.R;

public class DrawerLayoutHelper {
	
	private Context mContext;
	
	private ActionBarDrawerToggle drawerbar;
	public DrawerLayout drawerLayout;

	private RelativeLayout left_menu_layout, right_xiaoxi_layout;
	
	private TextView text;
	
	public ImageButton menuBtn,mesBtn;
	
	View view;
	
	
	public DrawerLayoutHelper(Context context){
		this.mContext = context;
	}
	
	
	public void initView() {
		//testFragment = new TestFragment();
		//FragmentManager fragmentManager = getSupportFragmentManager();
		//FragmentTransaction f_transaction = fragmentManager.beginTransaction();
		//f_transaction.replace(R.id.main_content_frame_parent, testFragment);
		//f_transaction.commitAllowingStateLoss();
		
		LayoutInflater mInflater = LayoutInflater.from(mContext);
		view = mInflater.inflate(R.layout.test, null);
		
		initMainLayout();
		
		//initLeftLayout();
		//initRightLayout();
	}
	
	public void initMainLayout(){
    	menuBtn=(ImageButton)view.findViewById(R.id.menu_btn);
    	mesBtn=(ImageButton)view.findViewById(R.id.xiaoxi_btn);
    	
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

	/*public void initLeftLayout() {
		drawerLayout = (DrawerLayout) view.findViewById(R.id.main_drawer_layout);
		// 设置透明
		drawerLayout.setScrimColor(0x00000000);
		// 左边菜单
		left_menu_layout = (RelativeLayout) view.findViewById(R.id.main_left_drawer_layout);
		View view2 = getLayoutInflater().inflate(R.layout.menu_layout, null);
		text = (TextView) view2.findViewById(R.id.text);
		text.setText("左边测试菜单");
		left_menu_layout.addView(view2);
	}

	public void initRightLayout() {
		// 左边菜单
		right_xiaoxi_layout = (RelativeLayout) view.findViewById(R.id.main_right_drawer_layout);
		View view = getLayoutInflater().inflate(R.layout.xiaoxi_layout, null);
		text = (TextView) view.findViewById(R.id.text);
		text.setText("右边测试菜单");
		right_xiaoxi_layout.addView(view);
	}*/
	
	

/*	private void initEvent() {
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
	}*/

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

}
