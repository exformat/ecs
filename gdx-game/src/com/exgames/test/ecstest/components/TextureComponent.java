package com.exgames.test.ecstest.components;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.files.FileHandle;

public class TextureComponent implements Component {
	private Texture Texture;

	public void setTexture(Texture texture) {
		Texture = texture;
	}

	public Texture getTexture() {
		return Texture;
	}
}
