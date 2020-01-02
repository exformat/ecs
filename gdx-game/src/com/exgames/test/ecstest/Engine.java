package com.exgames.test.ecstest;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.exgames.test.ecstest.systems.MoveSystem;
import com.exgames.test.ecstest.systems.RenderSystem;
import com.exgames.test.ecstest.systems.ShaderSystem;
import com.exgames.test.ecstest.systems.SoundSystem;

public class Engine extends PooledEngine
{
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private Rectangle screenRect;
	
	private RenderSystem renderSystem;
	private MoveSystem moveSystem;
	private SoundSystem soundSystem;
	private ShaderSystem shaderSystem;

	public Engine(SpriteBatch batch, OrthographicCamera camera, Rectangle screenRect) {
		this.batch = batch;
		this.camera = camera;
		this.screenRect = screenRect;
		
		renderSystem = new RenderSystem(batch, camera);
		moveSystem = new MoveSystem(screenRect);
		soundSystem = new SoundSystem();
		shaderSystem = new ShaderSystem(batch, camera);
		
		addSystem(renderSystem);
		addSystem(shaderSystem);
		addSystem(moveSystem);
		addSystem(soundSystem);
	}
	
	public void dispose(){
		renderSystem.dispose();
		shaderSystem.dispose();
		soundSystem.dispose();
	}
}
