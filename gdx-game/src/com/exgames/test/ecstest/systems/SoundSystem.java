package com.exgames.test.ecstest.systems;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Family;
import com.exgames.test.ecstest.components.SoundComponent;
import com.badlogic.ashley.core.ComponentMapper;
import com.exgames.test.ecstest.events.Events;

public class SoundSystem extends IteratingSystem
{
	private static ComponentMapper maper = ComponentMapper.getFor(SoundComponent.class);
	
	public SoundSystem(){
		super(Family.all(SoundComponent.class).get());
	}
	
	@Override
	protected void processEntity(Entity entity, float deltaT) {
		SoundComponent soundComponent = (SoundComponent)maper.get(entity);
		if(Events.isCollision){
			soundComponent.getSound().play(1);
			
			Events.isCollision = false;
		}
	}
	
	public void dispose(){
		ComponentMapper mapper = ComponentMapper.getFor(SoundComponent.class);
		for(Entity entity: getEntities()){
			SoundComponent sound = (SoundComponent)mapper.get(entity);
			sound.getSound().dispose();
		}
	}
}
