package com.me.mygdxgame.mgr;

import java.util.ArrayList;

import com.me.mygdxgame.data.Data;
import com.me.mygdxgame.data.DataSprite;
import com.me.mygdxgame.data.DataTile;
import com.me.mygdxgame.game.Game;
import com.me.mygdxgame.game.GameCamera;
import com.me.mygdxgame.game.GameMap;
import com.me.mygdxgame.sprite.SpriteStatic;
import com.me.mygdxgame.sprite.SpriteTile;
import com.me.mygdxgame.utils.Cst;

public class DataMgr {
	
	public static void init(){
	    load();
	    createGameObjects();
	}
	
	public static void load(){
		
		Data.tiles = new ArrayList<DataTile>();
		DataTile dataTile;
		DataSprite dataSprite;
		
		dataSprite = new DataSprite(0,"Floor","floor.png");
		dataTile = new DataTile(0,"Floor",dataSprite);
		Data.tiles.add(dataTile);
		TileMgr.add(new SpriteTile("floor.png"));
		
		dataSprite = new DataSprite(1,"Wall","wall.png");
		dataTile = new DataTile(1,"Wall",true,dataSprite);
		Data.tiles.add(dataTile);
		TileMgr.add(new SpriteTile("wall.png",Cst.TILE_WALL_H));
		
		StaticMgr.add(new SpriteStatic("tree.png"));
		
		StaticMgr.add(new SpriteStatic("characterBig.png"));
	}
	
	public static void createGameObjects(){
		Game.cam = new GameCamera();
		Game.map = new GameMap(Cst.MAP_WIDTH, Cst.MAP_HEIGHT);
	}
	
}
