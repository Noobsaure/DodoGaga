package com.me.mygdxgame.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.me.mygdxgame.utils.Cst;

public class GameCamera extends OrthographicCamera{
	public GameCamera(){super(16 * Cst.TILE_W, 16 * Cst.TILE_W  * (Gdx.graphics.getHeight() / (float)Gdx.graphics.getWidth()));}
}
