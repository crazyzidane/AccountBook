package com.example.accountbook;

import com.example.accountbook.ExpenseTrendView;
import com.example.accountbook.R;
import com.example.accountbook.tools.HttpUtil;
import com.example.accountbook.tools.Tools;
import com.example.accountbook.db.DBHelper;

import java.io.File;
import java.io.FileInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Timer;
import java.util.UUID;

import android.R.integer;
import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	
	ExpenseTrendView tu;
	//Button BT_Add;
	Timer mTimer =new Timer();
	HashMap<String, Float> map;
	Double key=8.0;
	Double value=0.0;
	Tools tool=new Tools();
	
	
	private ActionBarDrawerToggle drawerbar;
	public DrawerLayout drawerLayout;
	private RelativeLayout left_menu_layout, right_xiaoxi_layout;
	private TextView text;
	public ImageButton menuBtn,mesBtn;
	
	
	
	private Button mBtnAddAccount;
	private TextView mViewSumExpense;
	private TextView mViewSumIncome;
	private TextView mViewSumBalance;
	private Button mBtnTest;
	private Button mBtnBackup;
	
	private ExpenseTrendView mViewExpenseTrend;
	
	DBHelper dbHelper;
	
	private float mSumExpenseMoney;
	private float mSumBalanceMoney;
	private float mSumIncomeMoney;
	
	//存储数据库文件的字节数组
	private byte[] dbfile_data;
	
	private String mBackupFile;
	
	HashMap<String, Float> mWeekDataMap;
	String today;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test);
		
		mWeekDataMap = new HashMap<String, Float>();
		
		//创建DBHelper对象，一定记得创建啊啊啊啊啊
		dbHelper = new DBHelper(MainActivity.this);
		
		mBtnAddAccount = (Button) findViewById(R.id.btn_add_account);
		mViewSumExpense = (TextView) findViewById(R.id.text_sum_expense);
		mViewSumIncome = (TextView) findViewById(R.id.text_sum_income);
		mViewSumBalance = (TextView) findViewById(R.id.text_sum_balance);
		
		mViewExpenseTrend = (ExpenseTrendView) findViewById(R.id.menulist);
		
		mBtnBackup = (Button) findViewById(R.id.btn_backup);
		
		
		//测试上传数据库文件
		//上传的路径
		final String upload_path = "http://10.88.12.121:8080/uploadCamera/UploadAction";
		final String dbFilePath = getApplicationContext().getDatabasePath("account_db").getAbsolutePath();
		final File file = new File(dbFilePath);
		
		mBtnBackup.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//mBtnBackup.setText(dbFilePath);
				
				mBackupFile = generateFileName();
				
				dbfile_data = HttpUtil.getBytesFromFile(file);
				if(dbfile_data != null){
					HttpUtil.sendFormByPost(upload_path, dbfile_data, mBackupFile);
				}
				
			}
		});
		

		
		//显示最近一周的支出趋势图
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date currDay = new Date(System.currentTimeMillis());
		//Calendar calendar = Calendar.getInstance();
		today = format.format(currDay);
		String dayTemp;
		float moneyTemp;
		mViewExpenseTrend.SetTuView(mWeekDataMap, 2000, 1000, "", "元", false);
		for(int i=6; i>=0; i--){
			dayTemp = getSpecifiedDayBefore(today, -i);
			dbHelper.queryDayMoney(DBHelper.TABLE_NAME_EXPENSE, dayTemp);
			moneyTemp = dbHelper.mTheDayMoeny;
			mWeekDataMap.put(dayTemp, moneyTemp);
		}
		
		
		//根据每周中最大支出那天的支出值来设置Total和Pjvalue的值
		mViewExpenseTrend.setTotalAndPjValue(mWeekDataMap);
		
		
		mViewExpenseTrend.setMap(mWeekDataMap);
		
		
		
		
		//测试查询当天消费函数queryDayMoney()
/*		mBtnTest = (Button) findViewById(R.id.btn_test);
		mBtnTest.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				String dayTest;
				float dayMoney;
				dayTest = getSpecifiedDayBefore(today, -0);

				dbHelper.queryDayMoney(DBHelper.TABLE_NAME_EXPENSE, today);
				dayMoney = dbHelper.mTheDayMoeny;
				
				//mBtnTest.setText(dayTest);
				mBtnTest.setText(String.valueOf(dayMoney));
				
				//mWeekDataMap.put(dayTest, dayMoney);
				
			}
		});*/
		
		
		
		mBtnTest = (Button) findViewById(R.id.btn_test);
		mBtnTest.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, ExpenseListViewActivity.class);
				startActivity(intent);
			}
		});
		
		
		
		
		//显示当前账目概况
		showSumAccount();
		
		
		//增加账目
		mBtnAddAccount.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, AccountPagesTabWidget.class);
				startActivity(intent);
			}
		});
		
		
		
		//DrawerLayout
		initView();
		initEvent();
		
	}
	
	/**
	 * 随机生成要备份到服务器的数据库的名称，采用UUID
	 * UUID保证名称的唯一性
	 * @return
	 */
	private String generateFileName(){
		return UUID.randomUUID().toString();
	}
	
	

	/**
	 * 账目概览
	 */
	private void showSumAccount() {
		// TODO Auto-generated method stub
		/**
		 * 设置总支出
		 */
		dbHelper.queryAll(DBHelper.TABLE_NAME_EXPENSE);
		mViewSumExpense.setText(String.valueOf(dbHelper.mSumMoney));
		mSumExpenseMoney = dbHelper.mSumMoney;
		
		//设置余粮
		mSumIncomeMoney = Float.parseFloat(mViewSumIncome.getText().toString());
		mSumBalanceMoney = Float.parseFloat(mViewSumBalance.getText().toString());
		mSumBalanceMoney = mSumIncomeMoney - mSumExpenseMoney;
		mViewSumBalance.setText(String.valueOf(mSumBalanceMoney));
		
		//设置账目颜色
		showAccountColors();
		
	}

	/**
	 * 设置账目余额颜色
	 */
	private void showAccountColors() {
		// TODO Auto-generated method stub
		if(mSumBalanceMoney < 500){
			mViewSumBalance.setTextColor(getResources().getColor(R.color.red));
		}else if((500 < mSumBalanceMoney) && (mSumBalanceMoney < 1000)){
			mViewSumBalance.setTextColor(getResources().getColor(R.color.yellow));
		}else {
			mViewSumBalance.setTextColor(getResources().getColor(R.color.txt_gray));
		}
		
	}

	
	
	/**
	 * 获取与给定日期相差balance天的日期，返回日期的格式为MM-dd
	 * @param specifiedDay:指定日期
	 * @param balance:相差天数
	 * @return
	 */
	public static String getSpecifiedDayBefore(String specifiedDay, int balance){
		Calendar calendar = Calendar.getInstance();		//得到日历
		Date date = null;
		try {
			date =  new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//把给定日期时间赋给日历
		calendar.setTime(date);
		int day = calendar.get(Calendar.DATE);
		
		//设置相差balance的日期
		calendar.set(Calendar.DATE, day + balance);
		
		//格式化日期	并转换为String
		String dayResult = new SimpleDateFormat("MM-dd").format(calendar.getTime());
		
		return dayResult;
		
	}
	
	
	//测试前一天，另一种方法，暂不用
/*	public String getSpecifiedDayBefore(){
		Date dNow = new Date(System.currentTimeMillis());
		java.util.Date dBefore;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dNow);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		dBefore = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String dayBefore = format.format(dBefore);
		return dayBefore;
	}*/
	
	
	
	/**
	 * 从其他activity返回此acitivity时刷新界面
	 * 注意activity的生命周期
	 */
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		onCreate(null);
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
	
	
	
	
	
	
	
	
	
	
	
}
