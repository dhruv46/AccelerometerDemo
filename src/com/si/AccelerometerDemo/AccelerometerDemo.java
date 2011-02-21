package com.si.AccelerometerDemo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.si.AccelerometerDemo.R.id;

public class AccelerometerDemo extends Activity {
	private final String TAG = "AccelerometerDemo";
	private SensorManager mSensorManager;
	private float mAccelX = 0;
	private float mAccelY = 0;
	private float mAccelZ = 0;
	private ImageButton imageButton;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		imageButton = (ImageButton) findViewById(id.txt_view);
		imageButton.setBackgroundColor(Color.TRANSPARENT);
		mSensorManager = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);
		mSensorManager.registerListener(mSensorAccelerometer,SensorManager.SENSOR_ACCELEROMETER,SensorManager.SENSOR_DELAY_GAME);
		
	}

	@Override
	protected void onResume() {
		super.onResume();
		registerListener();
	}

	@Override
	public void onSaveInstanceState(Bundle icicle) {
		super.onSaveInstanceState(icicle);
		unregisterListener();
	}

	public void registerListener() {
		mSensorManager.registerListener(mSensorAccelerometer,
				SensorManager.SENSOR_ACCELEROMETER,
				SensorManager.SENSOR_DELAY_GAME);
	}

	public void unregisterListener() {
		mSensorManager.unregisterListener(mSensorAccelerometer);
	}

	private final SensorListener mSensorAccelerometer = new SensorListener() {
		// method called whenever new sensor values are reported.
		public void onSensorChanged(int sensor, float[] values) {
			// grab the values required to respond to user movement.
			mAccelX = values[0];
			mAccelY = values[1];
			mAccelZ = values[2];
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
			params.leftMargin = Math.round(mAccelX) * 20;
			params.topMargin = Math.abs(Math.round(mAccelY) * 20);
			imageButton.setLayoutParams(params);
			
		}

		public void onAccuracyChanged(int sensor, int accuracy) {
			// currently not used
		}
	};
}