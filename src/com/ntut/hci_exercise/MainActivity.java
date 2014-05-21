package com.ntut.hci_exercise;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class MainActivity extends FragmentActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		FragmentManager manager = getFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		
//		if(d.getWidth() > d.getHeight()) {
//			Fragment1 f1 = new Fragment1();
//			transaction.replace(android.R.id.content, f1);
//		} else {
//			Fragment2 f2 = new Fragment2();
//			transaction.replace(android.R.id.content, f2);
//		}
//		transaction.commit();
	}
}
