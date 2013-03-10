package com.me.mygdxgame.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.me.mygdxgame.utils.Cst;

public class GameCamera extends OrthographicCamera{
	public GameCamera(){
		super(32 * Cst.TILE_HW, 32 * Cst.TILE_HW  * (Gdx.graphics.getHeight() / (float)Gdx.graphics.getWidth()));
		this.position.set(0,0,1000);
		this.far = 1000;
	}
}
