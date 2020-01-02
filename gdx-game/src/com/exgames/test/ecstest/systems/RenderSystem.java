package com.exgames.test.ecstest.systems;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.exgames.test.ecstest.components.BodyComponent;
import com.exgames.test.ecstest.components.PositionComponent;
import com.exgames.test.ecstest.components.TextureComponent;
import java.util.Comparator;

public class RenderSystem extends SortedIteratingSystem {

	private SpriteBatch batch;
	private OrthographicCamera camera;
	private static ComponentMapper bodMaper = ComponentMapper.getFor(BodyComponent.class);
	private static ComponentMapper texMaper = ComponentMapper.getFor(TextureComponent.class);
	private static ComponentMapper posMaper = ComponentMapper.getFor(PositionComponent.class);

	private static Comparator<Entity> comparator = new Comparator<Entity>(){
		public int compare(Entity en1, Entity en2) {
			
			PositionComponent p1 = (PositionComponent)posMaper.get(en1);
			PositionComponent p2 = (PositionComponent)posMaper.get(en2);

			return p1.compareTo(p2);
		}
	};

	public RenderSystem(SpriteBatch batch, OrthographicCamera camera) {
		super(Family.all(BodyComponent.class,
						 TextureComponent.class,
						 PositionComponent.class).get(),
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
		BodyComponent body = (BodyComponent)bodMaper.get(entity);
		TextureComponent texture = (TextureComponent)texMaper.get(entity);
		
		render(body, texture);
	}

	private void render(BodyComponent body, TextureComponent texture){
		batch.draw(texture.getTexture(), 
				   body.x, body.y, 
				   body.width/2, body.height/2,
				   body.width, body.height,
				   body.scale.x,body.scale.y,
				   body.degress,
				   0,0,
				   (int)body.width,(int)body.height,
				   false,false); 
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
