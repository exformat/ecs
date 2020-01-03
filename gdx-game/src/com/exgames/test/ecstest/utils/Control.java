package com.exgames.test.ecstest.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.exgames.test.ecstest.events.ControlEvents;

public class Control extends InputAdapter implements GestureDetector.GestureListener {

	protected final String TAG = "CONTROL";

	private GestureDetector gestureDetector = new GestureDetector(this);
	private InputMultiplexer inputMultiplexer = new InputMultiplexer();

	public Control(){
		inputMultiplexer.addProcessor(this);
		inputMultiplexer.addProcessor(gestureDetector);
		Gdx.input.setInputProcessor(inputMultiplexer);
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		log("tap: x:" + x + " y:" + y);
		ControlEvents.tap.set(x, y);
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float delteY){
		log("pan: x:" + x + " dx:" + deltaX + " y:" + y + " dy:" + delteY);
		return false;
	}

	@Override
	public boolean longPress(float x, float y){
		log("longPress: x:" + x + " y:" + y);
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		log("zoom");
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		log("fling vX:" + velocityX + " vY:" + velocityY);
		return false;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		log("panStop: x:" + x + " y:" + y);
		return false;
	}

	@Override
	public boolean pinch(Vector2 p1, Vector2 p2, Vector2 p3, Vector2 p4){
		log("pinch");
		return false;
	}

	@Override
	public void pinchStop(){
		log("pinchStop");
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button){
		log("touchDown: x:" + x + " y:" + y);
		return false;
	}

	protected void log(String message){
		Gdx.app.log(TAG, message);
	}
}

