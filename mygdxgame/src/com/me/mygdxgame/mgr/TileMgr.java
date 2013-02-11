package com.me.mygdxgame.mgr;

import com.me.mygdxgame.sprite.SpriteTile;

public class TileMgr {

	private static SpriteTile[] tileArray = new SpriteTile[128];
	private static int index = 0;
	
	public static void add(SpriteTile sprite){
		if(index < tileArray.length) {
			tileArray[index] = sprite;
			index++;
		}
	}
	
	public static SpriteTile get(int id) {
		SpriteTile res = null;
		if(id <= index && id >= 0) {
			res = tileArray[id];
		}
		return res;
	}
}
