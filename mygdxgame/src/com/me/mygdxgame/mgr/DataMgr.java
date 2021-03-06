package com.me.mygdxgame.mgr;

import java.util.ArrayList;
import java.util.Random;

import com.me.mygdxgame.data.Data;
import com.me.mygdxgame.data.DataMap;
import com.me.mygdxgame.data.DataTexture;
import com.me.mygdxgame.game.Game;
import com.me.mygdxgame.game.GameCamera;
import com.me.mygdxgame.game.GameMap;
import com.me.mygdxgame.utils.Cst;
import com.me.mygdxgame.utils.Debug;

public class DataMgr {

	public static void init(){
		loadDatabase();
		createGameObjects();
	}

	public static void loadDatabase(){

		Data.textures = new ArrayList<DataTexture>();
		//TODO
		Data.textures.add(new DataTexture(0,"blueTile01","blueTile01.png"));
		Data.textures.add(new DataTexture(1,"blueTile02","blueTile02.png"));
		Data.textures.add(new DataTexture(2,"blueTile03","blueTile03.png"));
		Data.textures.add(new DataTexture(3,"baseWall01","baseWall01.png"));
		Data.textures.add(new DataTexture(4,"Character","characterBig.png"));
		Data.textures.add(new DataTexture(5,"HighlightedTile","highlightedTile.png"));
		Data.textures.add(new DataTexture(6,"blueTileSlope","blueTileSlope.png"));

		//Load maps database
		Data.maps = new ArrayList<DataMap>();
		//Map 0

		DataMap data = new DataMap(0, "Map000", Cst.MAP_SIZE);
		Random rand = new Random();
		int height;
		for(int i=data.getSizeInTiles().x-1; i >= 0; i--){
			for(int j=data.getSizeInTiles().y-1; j >= 0; j--){
				height = 1+rand.nextInt(5);
				//if(rand.nextInt(6) > 3) {
				//	data.setTile(i,j,(byte)6);
				//} else
					data.setTile(i,j,(byte)(0));
				data.setHeight(i,j,(byte)height);
				if(height > data.getMaximumHeight()) {
					data.setMaximumHeight(height);
				}
			}
		}
		data.loadMap();
		Data.maps.add(data);
	}

	public static void createGameObjects(){
		Game.camera = new GameCamera();
		Game.map = new GameMap();
	}

}
