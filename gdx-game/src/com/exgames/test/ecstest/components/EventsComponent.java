package com.exgames.test.ecstest.components;
import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public class EventsComponent implements Component
{
	private boolean collision = false;
	private boolean playSound = false;
	private Vector2 collisionCoord = new Vector2(0, 0);

	public void setPlaySound(boolean playSound) {
		this.playSound = playSound;
	}

	public boolean isPlaySound() {
		return playSound;
	}

	public void setCollisionCoord(Vector2 collisionCoord) {
		this.collisionCoord = collisionCoord;
	}

	public Vector2 getCollisionCoord() {
		return collisionCoord;
	}
	
	public void setCollision(boolean collision) {
		this.collision = collision;
	}

	public boolean isCollision() {
		return collision;
	}
}
