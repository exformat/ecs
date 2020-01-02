package com.exgames.test.ecstest.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public class PositionComponent extends Vector2 implements Component, Comparable{

	public int zIndex = 0;

	@Override
	public int compareTo(Object p1) {
		PositionComponent pos = (PositionComponent)p1;
		return (zIndex < pos.zIndex) ? -1 : ((zIndex == pos.zIndex) ? 0 : 1);
	}
}
