package com.exgames.test.ecstest.components;
import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.physics.box2d.Body;

public class PhysicsComponent implements Component {
	private Body body;

	public void setBody(Body body) {
		this.body = body;
	}

	public Body getBody() {
		return body;
	}
}
