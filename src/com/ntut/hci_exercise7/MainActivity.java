package com.ntut.hci_exercise7;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class MainActivity extends ActionBarActivity {
	GeomView geomview;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		geomview = new GeomView(this);
		setContentView(geomview);
	}
}
