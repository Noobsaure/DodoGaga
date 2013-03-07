package com.me.mygdxgame.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.me.mygdxgame.utils.Cst;

public class GameCamera extends OrthographicCamera{
	public GameCamera(){super(20, 20 * (Gdx.graphics.getHeight() / (float)Gdx.graphics.getWidth()));}
}
