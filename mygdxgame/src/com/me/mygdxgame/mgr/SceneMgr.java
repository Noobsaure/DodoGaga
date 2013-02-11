package com.me.mygdxgame.mgr;

import com.me.mygdxgame.scene.Scene;
import com.me.mygdxgame.scene.SceneMap;
import com.me.mygdxgame.utils.Cst;
import com.me.mygdxgame.utils.Point2i;

public class SceneMgr {

	public static Scene scene = null;
	public static Scene nextScene = null;
	
	public static void init(){		
		nextScene = new SceneMap();
		launchScene();
	}
	
	//public static void exit(){
	//	scene = null;
	//}
		
	public static void startSceneLater(Scene newScene){
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
