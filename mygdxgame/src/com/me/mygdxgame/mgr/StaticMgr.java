package com.me.mygdxgame.mgr;

import com.me.mygdxgame.sprite.SpriteStatic;

public class StaticMgr {

	private static SpriteStatic[] staticArray = new SpriteStatic[128];
	private static int index = 0;
	
	public static void add(SpriteStatic sprite){
		if(index < staticArray.length) {
			staticArray[index] = sprite;
			index++;
		}
	}
	
	public static SpriteStatic get(int id) {
		SpriteStatic res = null;
		if(id <= index && id >= 0) {
			res = staticArray[id];
		}
		return res;
	}
}
