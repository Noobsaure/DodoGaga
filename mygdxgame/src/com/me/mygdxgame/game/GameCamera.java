package com.me.mygdxgame.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class GameCamera extends OrthographicCamera{
	
	public GameCamera(){
		
		super(20, 20 * (Gdx.graphics.getHeight() / (float)Gdx.graphics.getWidth()));			
		position.set(0, 20, 0);
		//up.scale(0, -1, 0);
		direction.set(1, -1, 0);
		near =0;
		far = 100;
		
		//setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}
}
