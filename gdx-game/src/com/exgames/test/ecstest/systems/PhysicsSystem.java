package com.exgames.test.ecstest.systems;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;

public class PhysicsSystem extends IteratingSystem
{

	public PhysicsSystem(){
		super(Family.all().get());
	}
	
	@Override
	protected void processEntity(Entity p1, float p2) {

	}

	
}
