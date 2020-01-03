package com.exgames.test.ecstest.systems;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.physics.box2d.World;
import com.exgames.test.ecstest.components.BodyComponent;
import com.exgames.test.ecstest.components.PhysicsComponent;

public class PhysicsSystem extends IteratingSystem
{
	
	private static ComponentMapper bodyMapper = ComponentMapper.getFor(BodyComponent.class);
	private static ComponentMapper PhyMapper = ComponentMapper.getFor(PhysicsComponent.class);
	
	
	private float STEP = 1/60;
	private float pixPerMeter = 128;
	private World word;
	
	public PhysicsSystem(World world){
		super(Family.all(PhysicsComponent.class,
						 BodyComponent.class).get());
		this.word = world;
	}
	
	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		word.step(STEP, 6, 2);
	}
	
	@Override
	protected void processEntity(Entity entity, float deltaT) {
		BodyComponent bodyComponent = (BodyComponent)bodyMapper.get(entity);
		PhysicsComponent physComponent = (PhysicsComponent)PhyMapper.get(entity);

		bodyComponent.x = physComponent.getBody().getPosition().x;
		bodyComponent.y = physComponent.getBody().getPosition().y;
		bodyComponent.degress = physComponent.getBody().getAngle();
		
	}
}
