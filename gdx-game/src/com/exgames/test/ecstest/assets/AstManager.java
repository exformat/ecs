package com.exgames.test.ecstest.assets;
import com.badlogic.gdx.assets.AssetManager;
import java.util.HashMap;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.assets.loaders.AssetLoader;

public class AstManager extends AssetManager
{
	private final String TAG = "ASSET MANAGER";
	
	private HashMap<String, String> nameAssets = new HashMap<String,String>();
	private HashMap<String, float[]> animData = new HashMap<String, float[]>();

	public AstManager(){
	}
	
	public <T extends Object> void load(String fileName, String assetName, Class<T> type){
		load(fileName, type);
		log("load(): " + fileName);
		nameAssets.put(assetName, fileName);
	}

	public <T extends Object> void load(String fileName, String assetName, Class<T> type, AssetLoaderParameters<T> parameter) {
		load(fileName, type, parameter);
		log("load(): " + fileName);
		nameAssets.put(assetName, fileName);
	}

	public void loadAnimation(String fileName, String assetName, float[] data) {
		load(fileName, Texture.class);
		nameAssets.put(assetName, fileName);
		animData.put(assetName, data);
	}
	
	public <T extends Object> void loadAll(Array<String> fileNames, Array<String> assetNames, Class<T>type){
		for(int i = 0; i < fileNames.size; i++){
			load(fileNames.get(i), assetNames.get(i), type);
		}
	}
	
//=====get========================
	@Override
	public <T extends Object> T get(String assetName){
		return super.get(nameAssets.get(assetName));
	}

	@Override
	public <T extends Object> T get(String assetName, Class<T> type){
		log("get(): asset name: " + assetName + " type: " + type);
		return super.get(nameAssets.get(assetName), type);
	}
	
	public Sound getSound(String assetName){
		return get(assetName, Sound.class);
	}
	
	public Texture getTexture(String assetName, boolean filter){
		Texture tex = get(assetName, Texture.class);
		if(filter){
			tex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}
		return tex;
	}
	
	public String getShader(String assetName){
		return get(assetName, String.class);
	}
	
	/*
	public AnimData getAnimData(String animName){
		Texture texture = getTexture(animName, false);
		float[] dat = animData.get(animName);
		AnimData data = new AnimData(texture, (int)dat[0], (int)dat[1], dat[2]);
		
		return data;
	}
	*/
	
	private void log(String message){
		Gdx.app.log(TAG, message);
	}
}
