package com.example.accountbook;



import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class TestMainActivity extends Activity {
	
	private View view;
    public ImageButton menuBtn,mesBtn;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_mainactivity);
		
		initView();
	}
	
	public void initView(){
    	menuBtn=(ImageButton)view.findViewById(R.id.menu_btn);
    	mesBtn=(ImageButton)view.findViewById(R.id.xiaoxi_btn);
    	
    	final TestActivity ta = new TestActivity();
    	//点击打开左边菜单
    	menuBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ta.openLeftLayout();
			}
		});
    	//点击打开右边菜单
    	mesBtn.setOnClickListener(new View.OnClickListener() {
    		
    		@Override
    		public void onClick(View v) {
    			ta.openRightLayout();
    		}
    	});
    	
    }
	
}
