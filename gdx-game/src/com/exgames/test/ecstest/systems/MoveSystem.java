package com.exgames.test.ecstest.systems;
import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Rectangle;
import com.exgames.test.ecstest.components.BodyComponent;
import com.exgames.test.ecstest.components.PhysicsComponent;
import com.exgames.test.ecstest.components.PositionComponent;
import com.exgames.test.ecstest.components.VelocityComponent;
import com.exgames.test.ecstest.events.Events;

public class MoveSystem extends IteratingSystem
{
	
	private static Class<?extends Component> positionType = PositionComponent.class;
	private static Class<?extends Component> velosityType = VelocityComponent.class;
	private static Class<?extends Component> bodyType = BodyComponent.class;
	
	private static ComponentMapper positionMapper = ComponentMapper.getFor(positionType);
	private static ComponentMapper velosityMapper = ComponentMapper.getFor(velosityType);
	private static ComponentMapper bodyMapper = ComponentMapper.getFor(bodyType);
	
	public MoveSystem(){
		super(Family.all(positionType, 
						 velosityType,
						 bodyType).get());
	}

	@Override
	protected void processEntity(Entity entity, float deltaT) {
		PositionComponent positionComponent = (PositionComponent)positionMapper.get(entity);
		VelocityComponent velosityComponent = (VelocityComponent)velosityMapper.get(entity);
		BodyComponent bodyComponent = (BodyComponent)bodyMapper.get(entity);
		
		move(positionComponent, velosityComponent, bodyComponent, deltaT);
	}

	private void move(PositionComponent position, VelocityComponent velocity, BodyComponent body, float deltaT) { 
		body.x += velocity.x * deltaT;
		body.y += velocity.y * deltaT;
		body.degress = velocity.angle();
	} 
}
