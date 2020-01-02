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

public class Assets
{
	private final String TAG = "ASSETS",
						 TEXTURE_PATH = "data/textures/",
						 SOUND_PATH = "data/sound/",
						 MUSIC_PATH = "data/music/";
	
	
	private AstManager assetManager = new AstManager();
	
	public Assets() {}
	
	public void loadAssets(){
		Gdx.app.log(TAG, "load assets");
		loadTextures();
		loadSound();
		//loadMusic();
		
	}
	
	private void loadMusic(){
		Gdx.app.log(TAG, "load " + MUSIC_PATH);
		FileHandle[] files = Gdx.files.internal(MUSIC_PATH).list(".wav");
		for(FileHandle file: files){
			Gdx.app.log(TAG, "load: " + file.path());
			assetManager.load(file.path(), file.name(), Music.class);
		}
	}
	
	private void loadSound(){
		Gdx.app.log(TAG, "load " + SOUND_PATH);
		FileHandle[] files = Gdx.files.internal(SOUND_PATH).list(".ogg");
		for(FileHandle file: files){
			Gdx.app.log(TAG, "load: " + file.path());
			assetManager.load(file.path(), file.name(), Sound.class);
		}
	}
	
	private void loadTextures(){
		Gdx.app.log(TAG, "load " + TEXTURE_PATH);
		FileHandle[] files = Gdx.files.internal(TEXTURE_PATH).list(".png");
		for(FileHandle file: files){
			Gdx.app.log(TAG, "load: " + file.path());
			loadFilterTexture(file.path(), file.name());
		}
	}
	
	private void loadFilterTexture(String fileName, String assetName){
		TextureParameter param = new TextureParameter();
		param.minFilter = TextureFilter.Linear;
		param.genMipMaps = true;
		assetManager.load(fileName, assetName, Texture.class, param);
	}
	
	private void log(String message){
		Gdx.app.log(TAG, message);
	}
	
	public void setAssetManager(AstManager assetManager) {
		this.assetManager = assetManager;
	}

	public AstManager getAssetManager() {
		return assetManager;
	}
}
