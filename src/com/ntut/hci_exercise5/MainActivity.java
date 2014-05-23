package com.ntut.hci_exercise5;

import android.app.Activity;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.WindowManager;

public class MainActivity extends Activity {
	SurfaceView camaraPreview;
	SurfaceHolder previewHolder;
	Camera camera;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		camaraPreview = (SurfaceView)findViewById(R.id.surfaceView1);
		previewHolder = camaraPreview.getHolder();
		previewHolder.addCallback(surfaceCallback);
		previewHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}
	
	Callback surfaceCallback = new Callback() {
		@Override
		public void surfaceDestroyed(SurfaceHolder holder) {
			
		}
		
		@Override
		public void surfaceCreated(SurfaceHolder holder) {
			camera = Camera.open();
			try {
				camera.setPreviewDisplay(previewHolder);
			} catch (Throwable t) {
				Log.e("AR Camera", "Exception in setPreviewDisplay()", t);
				camera.release();
			}
		}
		
		@Override
		public void surfaceChanged(SurfaceHolder holder, int format, int width,	int height) {
			Parameters parameters = camera.getParameters();
			Size size = getBestPreviewSize(width, height, parameters);
			
			if(size != null) {
				parameters.setPreviewSize(size.width, size.height);
				camera.setParameters(parameters);
				camera.startPreview();
			}
			
			WindowManager wm = getWindowManager();
			Display display = wm.getDefaultDisplay();
			
			if(display.getRotation() == Surface.ROTATION_0) {
				parameters.setPreviewSize(height, width);
				camera.setDisplayOrientation(90);
			}
		}
	};
	
	private Size getBestPreviewSize(int width,	int height, Parameters parameters) {
		Size result = null;
		for(Size size : parameters.getSupportedPreviewSizes()) {
			if(size.width <= width && size.height <= height) {
				if(result == null) {
					result = size;
				} else {
					int resultarea = result.width * result.height;
					int newarea = size.width * size.height;
					if(newarea > resultarea) {
						result = size;
					}
				}
			}
		}
		return result;
	}
}
