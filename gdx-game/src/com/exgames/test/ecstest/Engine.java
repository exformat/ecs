package com.exgames.test.ecstest;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.exgames.test.ecstest.systems.MoveSystem;
import com.exgames.test.ecstest.systems.RenderSystem;
import com.exgames.test.ecstest.systems.ShaderSystem;
import com.exgames.test.ecstest.systems.SoundSystem;
import com.exgames.test.ecstest.systems.control_systems.TapSystem;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Engine extends PooledEngine
{
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private Viewport viewport;
	private Rectangle screenRect;
	
	private RenderSystem renderSystem;
	private MoveSystem moveSystem;
	private SoundSystem soundSystem;
	private ShaderSystem shaderSystem;
	
	private TapSystem tapSystem;

	public Engine(SpriteBatch batch, OrthographicCamera camera, Viewport viewport, Rectangle screenRect) {
		this.batch = batch;
		this.camera = camera;
		this.screenRect = screenRect;
		this.viewport = viewport;
		
		renderSystem = new RenderSystem(batch, camera);
		moveSystem = new MoveSystem(screenRect);
		soundSystem = new SoundSystem();
		shaderSystem = new ShaderSystem(batch, camera);
		
		tapSystem = new TapSystem(viewport);
		
		addSystem(renderSystem);
		addSystem(shaderSystem);
		addSystem(moveSystem);
		addSystem(soundSystem);
		addSystem(tapSystem);
	}
	
	public void dispose(){
		renderSystem.dispose();
		shaderSystem.dispose();
		soundSystem.dispose();
	}
}
