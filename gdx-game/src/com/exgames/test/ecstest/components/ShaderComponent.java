package com.exgames.test.ecstest.components;
import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class ShaderComponent implements Component
{
	private ShaderProgram shader;


	public void initShader(String vert, String frag){
		ShaderProgram.pedantic = false;
		shader = new ShaderProgram(Gdx.files.internal(vert), Gdx.files.internal(frag));
		if (!shader.isCompiled())
			throw new GdxRuntimeException("Could not compile shader: "+shader.getLog());

		if (shader.getLog().length()!=0)
			System.out.println(shader.getLog());
		
	}
	
	public void run(OrthographicCamera cam, Vector2 pos, Texture tex, int numTex, float dt){
		shader.setUniformi("u_texture", 1000 - numTex);
		shader.setUniformf("v_texCoords", pos);
		
		shader.begin();
		shader.end();
		
	}
	
	public void setShader(ShaderProgram shader) {
		this.shader = shader;
	}

	public ShaderProgram getShader() {
		return shader;
	}
}
