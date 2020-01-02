package com.exgames.test.ecstest.components;
import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.audio.Sound;
import com.exgames.test.ecstest.events.Events;

public class SoundComponent implements Component
{
	private Sound sound;
	private boolean play = false;

	public void play(){
		if(Events.isCollision){
			Events.isCollision = false;
			sound.play();
		}
	}
	
	public void setPlay(boolean play) {
		this.play = play;
	}

	public boolean isPlay() {
		return play;
	}

	public void setSound(Sound sound) {
		this.sound = sound;
	}

	public Sound getSound() {
		return sound;
	}
}
