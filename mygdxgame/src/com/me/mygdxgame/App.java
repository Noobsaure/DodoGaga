package com.me.mygdxgame;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.me.mygdxgame.game.Game;
import com.me.mygdxgame.game.GameCamera;
import com.me.mygdxgame.mgr.DataMgr;
import com.me.mygdxgame.mgr.SceneMgr;
import com.me.mygdxgame.mgr.WindowMgr;
import com.me.mygdxgame.utils.Debug;


public class App implements ApplicationListener{
	
	@Override 
	public void create() {
		Debug.setDebugMode(true);
		DataMgr.init();
		WindowMgr.init();
		SceneMgr.init();
	}
	
	@Override
	public void render() {
		SceneMgr.update();
	}
		
	@Override
	public void dispose(){
	}
	
	@Override
	public void resize(int width, int height) {
		Game.camera = new GameCamera();
	}
	
	@Override
	public void pause() {
		
	}
	
	@Override
	public void resume() {
		
	}

}