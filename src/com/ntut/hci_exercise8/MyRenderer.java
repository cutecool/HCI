package com.ntut.hci_exercise8;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

import android.opengl.GLSurfaceView;

public class MyRenderer implements GLSurfaceView.Renderer {
	private boolean mTranslucentBackground;
	private Square mSquare;
	private Cube mCube;
	private float mCubeRotation;
	private float mTransY;

	public MyRenderer(boolean useTranslucentBackground) {
		mTranslucentBackground = useTranslucentBackground;
		mSquare = new Square();
		mCube = new Cube();
	}

	@Override
	public void onDrawFrame(GL10 gl) {
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		gl.glTranslatef(0.0f, (float)Math.sin(mTransY), -5.0f);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
		mSquare.draw(gl);
		mTransY += .075f;

		// draw Cube
		gl.glLoadIdentity();
		gl.glTranslatef(0.0f, -2.0f, -4.0f);
		gl.glRotatef(mCubeRotation, 1.0f, 1.0f, 1.0f);
		mCube.draw(gl);
		mCubeRotation -= 0.25f;
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		gl.glViewport(0, 0, width, height);
		float ratio = (float) width / height;
		gl.glMatrixMode(GL11.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glFrustumf(-ratio, ratio, -1, 1, 1, 10);
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		if (mTranslucentBackground) {
			gl.glClearColor(0, 0, 0, 0);
		} else {
			gl.glClearColor(1, 1, 1, 1);
		}
		gl.glClearDepthf(1.0f);
		gl.glEnable(GL11.GL_CULL_FACE);
		gl.glShadeModel(GL11.GL_SMOOTH);
		gl.glEnable(GL11.GL_DEPTH_TEST);
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
	}
}
