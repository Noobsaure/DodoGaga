package com.me.mygdxgame;

import com.badlogic.gdx.ApplicationListener;
import com.me.mygdxgame.mgr.DataMgr;
import com.me.mygdxgame.mgr.SceneMgr;
import com.me.mygdxgame.mgr.WindowMgr;


public class App implements ApplicationListener{
	
	@Override 
	public void create() {
		DataMgr.init();
		WindowMgr.init();
		
		SceneMgr.init();
	}
	
	@Override
	public void render() {
		SceneMgr.update();
		//System.out.println(Gdx.graphics.getFramesPerSecond());
	}
		
	@Override
	public void dispose(){
	}
	
	@Override
	public void resize(int width, int height) {
		
	}
	
	@Override
	public void pause() {
		
	}
	
	@Override
	public void resume() {
		
	}

}