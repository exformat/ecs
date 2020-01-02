package com.exgames.test.ecstest.components;
import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class BodyComponent extends Rectangle implements Component
{
	public float degress = 0;
	public Vector2 scale = new Vector2(1,1);
	
}
