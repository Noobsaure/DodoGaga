package com.me.mygdxgame.scene;

import com.me.mygdxgame.mgr.WindowMgr;

public abstract class SceneBase implements Scene{

	@Override
	public void start() {
	}

	@Override
	public void update() {
		WindowMgr.update();
	}
	
	@Override
	public void terminate() {
	}


}
