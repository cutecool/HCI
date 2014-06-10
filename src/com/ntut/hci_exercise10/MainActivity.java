package com.ntut.hci_exercise10;

import android.R.bool;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	private GLSurfaceView glSurfaceView;
	private boolean rendererSet = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		glSurfaceView = new GLSurfaceView(this);
		final ActivityManager activityManager = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
		final ConfigurationInfo configurationInfo = activityManager.getDeviceConfigurationInfo();
		final boolean supportEs2 = configurationInfo.reqGlEsVersion >= 0x20000
				|| (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1
						&& Build.FINGERPRINT.startsWith("generic"))
						|| Build.FINGERPRINT.startsWith("unknown")
						|| Build.MODEL.contains("google_sdk")
						|| Build.MODEL.contains("Emulator")
						|| Build.MODEL.contains("Android SDK built for x86");
		
		if(supportEs2) {
			glSurfaceView.setEGLContextClientVersion(2);
			glSurfaceView.setRenderer(new FirstRenderer());
			rendererSet = true;
		} else {
			Toast.makeText(this, "This devices does not support OpenGL ES 2.0.", Toast.LENGTH_SHORT).show();
			return;
		}
		setContentView(glSurfaceView);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		if(rendererSet) {
			glSurfaceView.onPause();
		}
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		if(rendererSet) {
			glSurfaceView.onResume();
		}
	}
	
//	@Override
//	public boolean onCreateOptionMenu(Menu menu) {
//		getMenuInflater().inflate(R.menu.f, menu);
//		return true;
//	}
}
