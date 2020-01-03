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
	private static ComponentMapper PhyMapper = ComponentMapper.getFor(PhysicsComponent.class);
	
	private Rectangle screen;
	private float pixPerMeter = 128;
	
	public MoveSystem(Rectangle screen){
		super(Family.all(positionType, 
						 velosityType,
						 bodyType,
						 PhysicsComponent.class).get());
		this.screen = screen;
	}

	@Override
	protected void processEntity(Entity entity, float deltaT) {
		
		//PositionComponent positionComponent = (PositionComponent)positionMapper.get(entity);
		//VelocityComponent velosityComponent = (VelocityComponent)velosityMapper.get(entity);
		BodyComponent bodyComponent = (BodyComponent)bodyMapper.get(entity);
		PhysicsComponent physComponent = (PhysicsComponent)PhyMapper.get(entity);
		
		bodyComponent.x = physComponent.getBody().getPosition().x * pixPerMeter;
		bodyComponent.y = physComponent.getBody().getPosition().y * pixPerMeter;
		bodyComponent.degress = physComponent.getBody().getAngle();
		//move(positionComponent, velosityComponent, bodyComponent, deltaT);
		
	}

	private void move(PositionComponent position, VelocityComponent velocity, BodyComponent body, float deltaT) { 
		body.x += velocity.x * deltaT;
		body.y += velocity.y * deltaT;
		body.degress = velocity.angle();
		
		boolean hcol = false;
		boolean vcol = false;
		
		if(hasHorizontalCollision(body)){
			velocity.x *= -1;
			body.x = position.x ;
			hcol = true;
		}
		
		if(hasVerticalCollision(body)){
			velocity.y *= -1;
			body.y = position.y;
			vcol = true;
		}
		
		if(!hcol && !vcol){
			position.x = body.x;
			position.y = body.y;
			Events.isCollision = false;
		}
	} 
	
	private boolean hasHorizontalCollision(BodyComponent bodyComponent){
		boolean horCollision = bodyComponent.x < screen.x || (bodyComponent.x + bodyComponent.width) > (screen.x + screen.width);
		Events.isCollision = true;
		return horCollision;
	}
	
	private boolean hasVerticalCollision(BodyComponent bodyComponent){ 
		boolean vertCollision = bodyComponent.y < screen.y || (bodyComponent.y + bodyComponent.height) > (screen.y + screen.height);
		Events.isCollision = true;
		return vertCollision;
	}
}
