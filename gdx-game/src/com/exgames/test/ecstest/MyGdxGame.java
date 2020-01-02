package com.exgames.test.ecstest;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.exgames.test.ecstest.assets.Assets;
import com.exgames.test.ecstest.assets.AstManager;
import com.exgames.test.ecstest.components.BodyComponent;
import com.exgames.test.ecstest.components.PositionComponent;
import com.exgames.test.ecstest.components.TextureComponent;
import com.exgames.test.ecstest.components.VelocityComponent;
import com.exgames.test.ecstest.components.SoundComponent;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.Audio;
import com.exgames.test.ecstest.components.ShaderComponent;

public class MyGdxGame implements ApplicationListener {
	
	float WIDTH = 720;
	float HIGTH = 1280;

	Rectangle screenRect;

	SpriteBatch batch;
	OrthographicCamera camera;
	Viewport viewport;

	private Engine engine;

	private AstManager manager;
	private Assets assets;

	@Override
	public void create() {
		assets = new Assets();
		assets.loadAssets();
		manager = assets.getAssetManager();

		batch = new SpriteBatch(1000);
		viewport = new FitViewport(WIDTH, HIGTH);
		camera = (OrthographicCamera)viewport.getCamera();
		camera.translate(WIDTH / 2, HIGTH / 2);
		screenRect = new Rectangle(0, 0, WIDTH, HIGTH);

		engine = new Engine(batch, camera, screenRect);
	}

	boolean start = false;
	@Override
	public void render() {        
	    Gdx.gl.glClearColor(1, 1, 1, 1);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if (manager.update()) {
			if (!start) {
				engine.addEntity(createBackground());
				engine.addEntity(createHero());
				start = true;
			}
		}

		if (start) {
			engine.update(Gdx.graphics.getDeltaTime());
		}
	}

	private Entity createHero() {
		Entity entity = engine.createEntity();

		PositionComponent position = engine.createComponent(PositionComponent.class);
		position.set(100, 100);
		position.zIndex = 1;
		entity.add(position);

		BodyComponent body = engine.createComponent(BodyComponent.class);
		body.set(0, 0, 256, 256);
		entity.add(body);

		VelocityComponent velocity = engine.createComponent(VelocityComponent.class);
		velocity.set(-80, -100);
		entity.add(velocity);

		TextureComponent texture = engine.createComponent(TextureComponent.class);
		texture.setTexture(manager.getTexture("image.png", true));
		texture.getTexture().bind(1000 - position.zIndex);
		entity.add(texture);
		
		SoundComponent sound = engine.createComponent(SoundComponent.class);
		sound.setSound(manager.getSound("sound.ogg"));
		entity.add(sound);
		
		
		
		return entity;
	}

	private Entity createBackground() {
		Entity entity = engine.createEntity();
		PositionComponent position = engine.createComponent(PositionComponent.class);
		position.set(0, 0);
		position.zIndex = 0;
		entity.add(position);

		BodyComponent body = engine.createComponent(BodyComponent.class);
		body.set(0, 0, WIDTH, HIGTH);
		entity.add(body);

		TextureComponent texture = engine.createComponent(TextureComponent.class);
		texture.setTexture(manager.getTexture("logo.png", true));
		texture.getTexture().bind(1000 - position.zIndex);
		entity.add(texture);

		/*
		ShaderComponent shader = engine.createComponent(ShaderComponent.class);
		shader.initShader("data/shaders/vert.glsl","data/shaders/invert_shader.glsl");
		entity.add(shader);
		*/
		return entity;
	}

	@Override
	public void dispose() {
		engine.dispose();
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
		screenRect.width = viewport.getWorldWidth();
		screenRect.height = viewport.getWorldHeight();
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
