package com.me.mygdxgame.scene;

import com.me.mygdxgame.mgr.WindowMgr;

public abstract class SceneBase{

	public void start() {
	}

	public void update() {
		updatePre();
		updateMain();
		updatePost();
	}
	
	public void updatePre(){
		//IntervalMgr.update();
	}
	
	public void updateMain(){
		
	}
	
	public void updatePost(){
		WindowMgr.update();
	}
	
	public void terminate() {
	}


}
