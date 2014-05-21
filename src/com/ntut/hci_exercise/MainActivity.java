package com.ntut.hci_exercise;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends FragmentActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		FragmentManager manager = getFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		
		Fragment1 f1 = new Fragment1();
		Fragment2 f2 = new Fragment2();
		transaction.add(R.id.fragment1, f1);
//		transaction.add(R.id.fragment1, f2);
		transaction.commit();
		
//		Button btn1 = (Button)findViewById(R.id.button1);
//		btn1.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				Fragment2 f2 = new Fragment2();
//				transaction.replace(android.R.id.content, f2);
//				transaction.addToBackStack(null);
//				transaction.commit();
//			}
//		});
	}
}
