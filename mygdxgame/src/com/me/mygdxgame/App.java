package com.me.mygdxgame;

import com.badlogic.gdx.ApplicationListener;
import com.me.mygdxgame.game.Game;
import com.me.mygdxgame.mgr.DataMgr;
import com.me.mygdxgame.mgr.SceneMgr;
import com.me.mygdxgame.mgr.StageMgr;
import com.me.mygdxgame.mgr.WindowMgr;
import com.me.mygdxgame.utils.Debug;
import com.me.mygdxgame.utils.script.ScriptMgr;


public class App implements ApplicationListener{
	
	@Override 
	public void create() {
		Debug.setDebugMode(true);
		DataMgr.init();
		WindowMgr.init();
		StageMgr.init();
		//ScriptMgr.init();
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
		Game.camera.resize(width,height);
		WindowMgr.resize(width, height);
	}
	
	@Override
	public void pause() {
		
	}
	
	@Override
	public void resume() {
		
	}

}