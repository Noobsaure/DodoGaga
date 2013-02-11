package com.me.mygdxgame.mgr;

import java.util.ArrayList;
import java.util.List;

import com.me.mygdxgame.data.Data;
import com.me.mygdxgame.data.DataTile;
import com.me.mygdxgame.game.Game;
import com.me.mygdxgame.game.GameCamera;
import com.me.mygdxgame.game.GameMap;

public class DataMgr {
	
	public static void init(){
	    loadDatabase();
	    createGameObjects();
	}
	
	public static void loadDatabase(){
		Data.tiles = new ArrayList<DataTile>();
		DataTile data;
		data = new DataTile(0, "Floor");
		data.textureName = "floor.png";
		Data.tiles.add(data);
		data = new DataTile(1, "Wall");
		data.isWall = true;
		data.textureName = "wall.png";
		data.origin = new float[]{128, 128};
		Data.tiles.add(data);
		
		
	}
	
	public static void createGameObjects(){
		Game.cam = new GameCamera();
		Game.map = new GameMap();
	}
	
}
