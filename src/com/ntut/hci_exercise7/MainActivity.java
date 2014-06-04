package com.ntut.hci_exercise7;

import javax.microedition.khronos.opengles.GL10;

import rajawali.RajawaliActivity;
import rajawali.lights.DirectionalLight;
import rajawali.materials.Material;
import rajawali.materials.shaders.FragmentShader;
import rajawali.materials.shaders.VertexShader;
import rajawali.math.vector.Vector3.Axis;
import rajawali.primitives.Plane;
import rajawali.renderer.RajawaliRenderer;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

public class MainActivity extends RajawaliActivity {
	RajawaliRenderer mRenderer;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mRenderer = new BasicRenderer(this);
		setRenderer(mRenderer);
		//setContentView(mSurfaceView);
	}
	
	private final class BasicRenderer extends RajawaliRenderer {
		DirectionalLight mLight;
		Plane mPlane;
		Material mMaterial;
		byte maxColor=(byte)255;
		float colors[] = {
				maxColor, maxColor, 0, maxColor,
				0, maxColor, maxColor, maxColor,
				0, 0, 0, maxColor,
				maxColor, 0, maxColor, maxColor
		};

		public BasicRenderer(Context context) {
			super(context);
		}

		protected void initScene() {
			mLight = new DirectionalLight(1f, 0.2f, -1.0f); // set the direction
			mLight.setColor(1.0f, 1.0f, 1.0f);
			mLight.setPower(2);

//			mMaterial = new Material(new VertexShader(), new FragmentShader());
			mMaterial = new Material();
			mMaterial.setColor(colors);
			
			mPlane = new Plane(2.0f, 2.0f, 1, 1, Axis.Z, true, true, 1);
			mPlane.setMaterial(mMaterial);
			mPlane.setLookAt(getCurrentCamera().getPosition());
			
			getCurrentScene().addLight(mLight);
			getCurrentScene().addChild(mPlane);
			getCurrentCamera().setZ(6);
		}

		public void onDrawFrame(GL10 glUnused) {
			super.onDrawFrame(glUnused);
			mPlane.setRotY(mPlane.getRotY() + 1);
		}
	}
	
}
