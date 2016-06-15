package com.javacodegeeks.android.viewflipperapp;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ViewFlipper;

public class MainActivity extends Activity {
	private ViewFlipper viewFlipper;
	private float lastX;
	private float currentX;

	SoundPool soundPool;
	int idOso;
	int idPitido;

	public MainActivity() {
		Log.i("Execute", "Constructor");
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		viewFlipper = (ViewFlipper) findViewById(R.id.viewflipper);
		soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
		idOso = soundPool.load(getBaseContext(), R.raw.oso, 0);
		idPitido = soundPool.load(getBaseContext(), R.raw.pitido, 0);
	}

	static int times = 0;

	public boolean onTouchEvent(MotionEvent touchevent) {
		Log.i("getX", " = "+touchevent.getX());
//		Log.i("Touch", "Event" + touchevent.getAction());
		times++;
		if (times == 2 && touchevent.getAction() == 1) {
			musicOso();
			times = 0;
//			Log.i("Entry", "Music");
		}
		if (touchevent.getAction() == 1 && times != 2) {
			times = 0;
		}

		// if (viewFlipper.getDisplayedChild() == 3) {
		// Log.i("Execute---", "Movment = " + touchevent.getAction());

		// if (touchevent.getAction() != 2) {
		// // Log.i("Flag", "movment = "+movment);
		// musicOso();
		// }
		//
		//
		// if (touchevent.getAction() == 0) {
		// musicOso();
		// }

		// }

		// 0 = Windows Pc 3 = El gran 2 = Sex 1 = Ubuntu pc
		switch (touchevent.getAction()) {
		case (MotionEvent.ACTION_DOWN):
			lastX = touchevent.getX();
//			Log.i("lastX", "lastX = " + lastX);
			return true;
		case (MotionEvent.ACTION_UP):
			
//			if (MotionEvent.ACTION_UP > 5) {
//			}
		
			// onInterceptTouchEvent
			actionUp(touchevent);
			return true;
			// case (MotionEvent.ACTION_MOVE):
			// Log.i("Action", "Move");
			// return true;
		}

		return super.onTouchEvent(touchevent);
	}

	public void actionUp(MotionEvent touchevent) {
		currentX = touchevent.getX();
		if (lastX < currentX) {
			if (viewFlipper.getDisplayedChild() == 0) {
				Log.e("Imagen = ", "Windows Pc");
			} else if (viewFlipper.getDisplayedChild() == 1) {
				Log.e("Imagen = ", "Sex Pistols");
			} else if (viewFlipper.getDisplayedChild() == 2) {
				Log.e("Imagen = ", "El Gran Silencio");
			} else if (viewFlipper.getDisplayedChild() == 3) {
				Log.e("Imagen = ", "Windows Pc");
			}
			viewFlipper.setInAnimation(this, R.anim.slide_in_from_left);
			viewFlipper.setOutAnimation(this, R.anim.slide_out_to_right);
			viewFlipper.showNext();
		} else if (lastX > currentX) {
			if (viewFlipper.getDisplayedChild() == 0) {
				Log.e("ImagenR = ", "El Gran Silencio");
			} else if (viewFlipper.getDisplayedChild() == 1) {
				Log.e("ImagenR = ", "(---No llamo nada---)-Windows Pc");
			} else if (viewFlipper.getDisplayedChild() == 2) {
				Log.e("ImagenR = ", "Ubuntu Pc");
			} else if (viewFlipper.getDisplayedChild() == 3) {
				Log.e("ImagenR = ", "Sex Pistols");
			}
			viewFlipper.setInAnimation(this, R.anim.slide_in_from_right);
			viewFlipper.setOutAnimation(this, R.anim.slide_out_to_left);
			viewFlipper.showPrevious();

		}
	}

	public void musicOso() {
		Log.i("Execute", "Touch");
		soundPool.play(idPitido, 1, 1, 1, 0, 1);
	}

}