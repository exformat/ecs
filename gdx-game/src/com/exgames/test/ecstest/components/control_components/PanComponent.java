package com.exgames.test.ecstest.components.control_components;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.ashley.core.Component;

public class PanComponent extends Vector2 implements Component
{
	private Vector2 delta = new Vector2(0,0);


	public void setDelta(float dx, float dy) {
		delta.x = dx;
		delta.y = dy;
	}

	public Vector2 getDelta() {
		return delta;
	}
	
	public float dx(){
		return delta.x;
	}
	
	public float dy(){
		return delta.y;
	}
}
