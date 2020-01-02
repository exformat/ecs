package com.exgames.test.ecstest.assets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.assets.loaders.TextureLoader.TextureParameter;
import com.exgames.test.ecstest.assets.AstManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import java.io.File;
import com.badlogic.gdx.Audio;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class Assets {
	private final String TAG = "ASSETS",
						 TEXTURE_PATH = "data/textures/",
						 SOUND_PATH   = "data/sound/",
						 MUSIC_PATH   = "data/music/";


	private AstManager assetManager;

	public Assets() {
		assetManager = new AstManager();
	}

	public void loadAssets() {
		log("load assets");
		loadTextures();
		loadSound();
		loadMusic();
	}

	private void loadMusic() {
		log("load music: " + MUSIC_PATH);
		FileHandle[] files = Gdx.files.internal(MUSIC_PATH).list(".wav");
		for (FileHandle file: files) {
			assetManager.load(file.path(), file.name(), Music.class);
		}
	}

	private void loadSound() {
		log("load sounds: " + SOUND_PATH);
		FileHandle[] files = Gdx.files.internal(SOUND_PATH).list(".ogg");
		for (FileHandle file: files) {
			assetManager.load(file.path(), file.name(), Sound.class);
		}
	}

	private void loadTextures() {
		log("load textures: " + TEXTURE_PATH);
		FileHandle[] files = Gdx.files.internal(TEXTURE_PATH).list(".png");
		for (FileHandle file: files) {
			loadFilterTexture(file.path(), file.name());
		}
	}

	private void loadFilterTexture(String fileName, String assetName) {
		TextureParameter param = new TextureParameter();
		param.minFilter = TextureFilter.Linear;
		param.genMipMaps = true;
		assetManager.load(fileName, assetName, Texture.class, param);
	}

	private void log(String message) {
		Gdx.app.log(TAG, message);
	}

	public void setAssetManager(AstManager assetManager) {
		this.assetManager = assetManager;
	}

	public AstManager getAssetManager() {
		return assetManager;
	}
}
