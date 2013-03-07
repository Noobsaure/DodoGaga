package com.me.mygdxgame.mgr;

import com.me.mygdxgame.scene.SceneBase;
import com.me.mygdxgame.scene.SceneMap;
import com.me.mygdxgame.utils.Cst;
import com.me.mygdxgame.utils.Point2i;

public class SceneMgr {

	public static SceneBase scene = null;
	public static SceneBase nextScene = null;
	
	public static void init(){		
		nextScene = new SceneMap();
		launchScene();
	}
		
	public static void startSceneLater(SceneBase newScene){
		nextScene = newScene;
	}
	
	public static boolean isSceneChanging(){
		return nextScene != null;
	}
	
	private static void launchScene(){
		if(scene != null){
			scene.terminate();
		}
		scene = nextScene;
		nextScene = null;
		scene.start();
	}
	
	public static void update(){
		scene.update();
		if(isSceneChanging()){
			launchScene();
		}
	}
			
}
