package com.exgames.test.ecstest;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.exgames.test.ecstest.systems.RenderSystem;
import com.exgames.test.ecstest.systems.MoveSystem;
import com.exgames.test.ecstest.systems.SoundSystem;
import com.badlogic.ashley.core.Engine;

public class Engine extends PooledEngine
{
	private Batch batch;
	private OrthographicCamera camera;
	private Rectangle screenRect;
	
	private RenderSystem renderSystem;
	private MoveSystem moveSystem;
	private SoundSystem soundSystem;

	public Engine(Batch batch, OrthographicCamera camera, Rectangle screenRect) {
		this.batch = batch;
		this.camera = camera;
		this.screenRect = screenRect;
		
		renderSystem = new RenderSystem(batch, camera);
		moveSystem = new MoveSystem(screenRect);
		soundSystem = new SoundSystem();
		
		addSystem(renderSystem);
		addSystem(moveSystem);
		addSystem(soundSystem);
	}
	
	public void dispose(){
		renderSystem.dispose();
		soundSystem.dispose();
	}
}
