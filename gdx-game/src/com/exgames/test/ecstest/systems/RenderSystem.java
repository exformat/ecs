package com.exgames.test.ecstest.systems;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.exgames.test.ecstest.components.BodyComponent;
import com.exgames.test.ecstest.components.TextureComponent;
import java.util.Comparator;
import com.badlogic.ashley.core.ComponentMapper;
import com.exgames.test.ecstest.components.PositionComponent;

public class RenderSystem extends SortedIteratingSystem {

	private Batch batch;
	private OrthographicCamera camera;

	private static Comparator<Entity> comparator = new Comparator<Entity>(){
		public int compare(Entity en1, Entity en2) {
			ComponentMapper maper = ComponentMapper.getFor(PositionComponent.class);
			PositionComponent p1 = (PositionComponent)maper.get(en1);
			PositionComponent p2 = (PositionComponent)maper.get(en2);

			return p1.compareTo(p2);
		}
	};

	public RenderSystem(Batch batch, OrthographicCamera camera) {
		super(Family.all(BodyComponent.class,
						 TextureComponent.class).get(),
			  comparator);
		this.batch = batch;
		this.camera = camera;
	}

	@Override
	public void update(float deltaTime) {
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.enableBlending();
		batch.begin();
		
		super.update(deltaTime);
		
		batch.end();
	}

	@Override
	protected void processEntity(Entity entity, float deltaT) {
		ComponentMapper bodMaper = ComponentMapper.getFor(BodyComponent.class);
		ComponentMapper texMaper = ComponentMapper.getFor(TextureComponent.class);
		BodyComponent body = (BodyComponent)bodMaper.get(entity);
		TextureComponent texture = (TextureComponent)texMaper.get(entity);
		
		render(body, texture);
	}

	private void render(BodyComponent body, TextureComponent texture){
		batch.draw(texture.getTexture(), body.x, body.y, body.width, body.height); 
	}
	
	public void dispose(){
		batch.dispose();
		ComponentMapper mapper = ComponentMapper.getFor(TextureComponent.class);
		for(Entity entity: getEntities()){
			TextureComponent texture = (TextureComponent)mapper.get(entity);
			texture.getTexture().dispose();
		}
	}
}
