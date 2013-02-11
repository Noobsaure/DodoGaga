package com.me.mygdxgame.mgr;

import com.me.mygdxgame.scene.Scene;
import com.me.mygdxgame.scene.SceneMap;

public class SceneMgr {

	public static Scene scene = null;
	public static Scene nextScene = null;
	
	public static void init(){		
		nextScene = new SceneMap(new int[]{1000,1000});
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
