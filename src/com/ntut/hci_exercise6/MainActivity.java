package com.ntut.hci_exercise6;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.Size;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends Activity {
	SurfaceView camaraPreview;
	SurfaceHolder previewHolder;
	Camera camera;
	private static final String SAVE_PICTURE_PATH = "/sdcard/demo06";
	
	private Button buttonPreview;
	private Button buttonTackPhoto;
	private Bitmap mBitmap = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		camaraPreview = (SurfaceView)findViewById(R.id.surfaceView1);
		previewHolder = camaraPreview.getHolder();
		previewHolder.addCallback(surfaceCallback);
		previewHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		
		buttonPreview = (Button)findViewById(R.id.button_preview);
		buttonTackPhoto = (Button)findViewById(R.id.button_take_photo);
		
		buttonPreview.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				camera.startPreview();
			}
		});
		
		buttonTackPhoto.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				camera.takePicture(null, null, picCallback);
			}
		});
	}
	
	Callback surfaceCallback = new Callback() {
		@Override
		public void surfaceDestroyed(SurfaceHolder holder) {
			camera.stopPreview();
			camera.release();
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
			} else if(display.getRotation() == Surface.ROTATION_270) {
				parameters.setPreviewSize(height, width);
				camera.setDisplayOrientation(180);
			}
		}
	};
	
	public PictureCallback picCallback = new PictureCallback() {
		@Override
		public void onPictureTaken(byte[] data, Camera camera) {
			mBitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
			File file = new File(SAVE_PICTURE_PATH);
			if(!file.exists()) {
				file.mkdir();
			}
			
			try {
				File f = new File(SAVE_PICTURE_PATH + File.separator + "Camera_"
						 + System.currentTimeMillis() + ".jpg");
	            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(f));
	            mBitmap.compress(Bitmap.CompressFormat.JPEG, 80, out);
	            out.flush();
	            out.close();
	            camera.stopPreview();
            } catch (FileNotFoundException e) {
	            e.printStackTrace();
            } catch (IOException e) {
	            e.printStackTrace();
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
