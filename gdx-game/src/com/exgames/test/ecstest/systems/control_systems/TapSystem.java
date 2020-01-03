package com.exgames.test.ecstest.systems.control_systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.exgames.test.ecstest.components.BodyComponent;
import com.exgames.test.ecstest.components.PositionComponent;
import com.exgames.test.ecstest.components.control_components.TapComponent;
import com.exgames.test.ecstest.events.ControlEvents;
import java.util.Comparator;
import com.badlogic.gdx.Gdx;

public class TapSystem extends SortedIteratingSystem
{
	private final String TAG = "TAP SYSTEM";
	

	private static ComponentMapper tapMapper = ComponentMapper.getFor(TapComponent.class);
	private static ComponentMapper posMaper = ComponentMapper.getFor(PositionComponent.class);
	private static ComponentMapper bodyMapper = ComponentMapper.getFor(BodyComponent.class);
	private Viewport viewport;

	private static Comparator<Entity> comparator = new Comparator<Entity>(){
		public int compare(Entity en1, Entity en2) {

			PositionComponent p1 = (PositionComponent)posMaper.get(en1);
			PositionComponent p2 = (PositionComponent)posMaper.get(en2);

			return p2.compareTo(p1);
		}
	};

	public TapSystem(Viewport viewport){
		super(Family.all(TapComponent.class,
						 BodyComponent.class,
						 PositionComponent.class).get(),
			  comparator);
		this.viewport = viewport;
	}

	@Override
	public void update(float deltaTime) {
		ControlEvents.tap = screenToStageCoordinates(ControlEvents.tap);
		super.update(deltaTime);
	}

	@Override
	protected void processEntity(Entity entity, float deltaT) {
		TapComponent tap = (TapComponent)tapMapper.get(entity);
		BodyComponent body = (BodyComponent)bodyMapper.get(entity);
		if(!ControlEvents.tap.isZero() && body.contains(ControlEvents.tap)){
			tap.set(ControlEvents.tap);
			log("tap " + body.toString());
			ControlEvents.tap.setZero();
		}
	}

	public Vector2 screenToStageCoordinates (Vector2 screenCoords) {
		viewport.unproject(screenCoords);
		return screenCoords;
	}
	
	private void log(String message){
		Gdx.app.log(TAG, message);
	}
}
