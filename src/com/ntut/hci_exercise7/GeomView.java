package com.ntut.hci_exercise7;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;

import com.ntut.hci_exercise7.R;

public class GeomView extends View implements Runnable {
	private Paint paint01 = null;
	private Paint paint02 = null;
	Bitmap bitmap01 = null;

	public GeomView(Context context) {
		super(context);
		paint01 = new Paint();
		paint02 = new Paint();
		new Thread(this).start();
		bitmap01 = ((BitmapDrawable) getResources().getDrawable(R.drawable.jack)).getBitmap();
	}

	public void onDraw(Canvas canvas) {
		paint01.setColor(Color.RED);
		paint02.setColor(Color.BLUE);

		paint01.setAntiAlias(true);
		paint02.setStrokeWidth(5);

		canvas.drawColor(Color.WHITE);
		canvas.clipRect(0, 0, 320, 480);

		canvas.drawLine(20, 50, 200, 50, paint01);
		canvas.drawLine(20, 100, 150, 100, paint02);
		canvas.drawPoint(250, 100, paint02);

		canvas.drawRect(new Rect(20, 150, 120, 220), paint01);
		canvas.drawRect(new Rect(150, 150, 220, 220), paint02);

		canvas.drawCircle(70, 270, 40, paint01);
		canvas.drawCircle(200, 270, 40, paint02);

		paint01.setTextSize(5);
		canvas.drawText("My first canvas", 20, 350, paint01);

		scale(0.5f, 0.5f, 0, 0);
		rotate(30, 0, 0);
		canvas.drawBitmap(bitmap01, 20, 20, null);
	}

	public void scale(float sx, float sy, float px, float py) {
		Matrix matrix = new Matrix();
		matrix.reset();
		matrix.setScale(sx, sy);
		bitmap01 = Bitmap.createBitmap(bitmap01, 0, 0, bitmap01.getWidth(), bitmap01.getHeight(), matrix, true);
	}

	public void rotate(float angle, float px, float py) {
		Matrix matrix = new Matrix();
		matrix.reset();
		matrix.setRotate(angle, px, py);
		bitmap01 = Bitmap.createBitmap(bitmap01, 0, 0, bitmap01.getWidth(), bitmap01.getHeight(), matrix, true);
	}

	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				e.printStackTrace();
			}
			postInvalidate();
		}
	}
}
