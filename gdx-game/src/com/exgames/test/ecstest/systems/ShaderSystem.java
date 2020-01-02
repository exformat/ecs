package com.exgames.test.ecstest.systems;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.exgames.test.ecstest.components.ShaderComponent;
import com.exgames.test.ecstest.components.PositionComponent;
import com.exgames.test.ecstest.components.TextureComponent;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

public class ShaderSystem extends IteratingSystem
{

	private SpriteBatch bacth;
	private OrthographicCamera camera;
	private static ComponentMapper shaderMaper = ComponentMapper.getFor(ShaderComponent.class);
	private static ComponentMapper positionMaper = ComponentMapper.getFor(PositionComponent.class);
	private static ComponentMapper textureMaper = ComponentMapper.getFor(TextureComponent.class);
	
	public ShaderSystem(SpriteBatch batch, OrthographicCamera camera){
		super(Family.all(ShaderComponent.class, 
						 PositionComponent.class, 
						 TextureComponent.class).get());
		this.bacth= batch;
		this.camera = camera;
	}
	
	@Override
	protected void processEntity(Entity entity, float deltaT) {
		TextureComponent texture = (TextureComponent)textureMaper.get(entity);
		PositionComponent position = (PositionComponent)positionMaper.get(entity);
		ShaderComponent shader = (ShaderComponent)shaderMaper.get(entity);
		ShaderProgram oldShader = bacth.getShader();
		
		bacth.setShader(shader.getShader());
		shader.run(camera, position, texture.getTexture(), position.zIndex, deltaT);
		
		bacth.begin();
		bacth.draw(texture.getTexture(), position.x, position.y);
		bacth.end();
		bacth.setShader(oldShader);
	}
	
	public void dispose(){
		for(Entity entity: getEntities()){
			ShaderComponent sh = (ShaderComponent)shaderMaper.get(entity);
			sh.getShader().dispose();
		}
	}
}
