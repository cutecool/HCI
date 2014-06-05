package com.ntut.hci_exercise9;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Square {
	private FloatBuffer mFVertexBuffer;
	private ByteBuffer mColorBuffer;
	private ByteBuffer mIndexBuffer;
	
	public Square() {
		byte maxColor = (byte)255;
		
		float vertices[] = {
				-2.0f, -2.0f,
				2.0f, -2.0f,
				-2.0f, 2.0f,
				2.0f, 2.0f
		};
		
		byte colors[] = {
				maxColor, maxColor, 0, maxColor,
				0, maxColor, maxColor, maxColor,
				0, 0, 0, maxColor,
				maxColor, 0, maxColor, maxColor
		};
		
		byte indices[] = {0, 3, 1, 0, 2, 3};
		
		ByteBuffer vbb =ByteBuffer.allocateDirect(vertices.length * 4);
		vbb.order(ByteOrder.nativeOrder());
		mFVertexBuffer = vbb.asFloatBuffer();
		mFVertexBuffer.put(vertices);
		mFVertexBuffer.position(0);
		
		mColorBuffer = ByteBuffer.allocateDirect(colors.length);
		mColorBuffer.put(colors);
		mColorBuffer.position(0);
		
		mIndexBuffer = ByteBuffer.allocateDirect(indices.length);
		mIndexBuffer.put(indices);
		mIndexBuffer.position(0);
	}
	
	public void draw(GL10 gl) {
		gl.glFrontFace(GL10.GL_CW);
		gl.glVertexPointer(2, GL10.GL_FLOAT, 0, mFVertexBuffer);
		gl.glColorPointer(4, GL10.GL_UNSIGNED_BYTE, 0, mColorBuffer);
		gl.glDrawElements(GL10.GL_TRIANGLES, 6, GL10.GL_UNSIGNED_BYTE, mIndexBuffer);
		gl.glFrontFace(GL10.GL_CCW);
	}
}
