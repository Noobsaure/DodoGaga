package com.me.mygdxgame.scene;

import com.me.mygdxgame.mgr.IntervalMgr;
import com.me.mygdxgame.mgr.WindowMgr;

public abstract class SceneBase{

	public void start() {
	}

	public void update() {
		//IntervalMgr.update();
		WindowMgr.update();
	}
	
	public void terminate() {
	}


}
