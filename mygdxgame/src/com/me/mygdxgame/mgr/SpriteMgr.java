package com.me.mygdxgame.mgr;

import com.me.mygdxgame.sprite.SpriteStatic;
import com.me.mygdxgame.sprite.SpriteTile;

public class SpriteMgr {

	private static SpriteStatic[] staticArray = new SpriteStatic[128];
	private static int staticIndex = 0;
	private static SpriteTile[] tileArray = new SpriteTile[128];
	private static int tileIndex = 0;
	
	public static void add(SpriteStatic sprite){
		if(staticIndex < staticArray.length) {
			staticArray[staticIndex] = sprite;
			staticIndex++;
		}
	}
	
	public static SpriteStatic getStatic(int id) {
		SpriteStatic res = null;
		if(id <= staticIndex && id >= 0) {
			res = staticArray[id];
		}
		return res;
	}
	
	public static void addTile(SpriteTile sprite){
		if(tileIndex < tileArray.length) {
			tileArray[tileIndex] = sprite;
			tileIndex++;
		}
	}
	
	public static SpriteTile getTile(int id) {
		SpriteTile res = null;
		if(id <= tileIndex && id >= 0) {
			res = tileArray[id];
		}
		return res;
	}
}
