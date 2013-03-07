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

		Debug.debugMsg("Loading database");

		Data.textures = new ArrayList<DataTexture>();
		//TODO
		Data.textures.add(new DataTexture(0,"Floor","greenTile.png"));
		Data.textures.add(new DataTexture(1,"WallLeft","wallLeft.png"));
		Data.textures.add(new DataTexture(2,"WallRight","wallRight.png"));
		Data.textures.add(new DataTexture(3,"Character","characterBig.png"));

		Debug.debugMsg("Done");

		//Load maps database
		Data.maps = new ArrayList<DataMap>();
		//Map 0

		Debug.debugMsg("Building maps");

		DataMap data = new DataMap(0, "Map000", Cst.MAP_SIZE);
		Random rand = new Random();
		int height;
		//int tmplol = 1;
		for(int i=data.getSizeInTiles().x-1; i >= 0; i--){
			for(int j=data.getSizeInTiles().y-1; j >= 0; j--){
				data.setTile(i,j,Cst.FLOOR);
				height = rand.nextInt(2);//rand.nextInt(tmplol);
				data.setHeight(i,j,(byte)height);
				if(height > data.getMaximumHeight()) {
					data.setMaximumHeight(height);
				}
				//tmplol = Math.max(tmplol, rand.nextInt(tmplol+3));
			}
		}
		data.loadMap();
		Data.maps.add(data);

		Debug.debugMsg("Done");
	}

	public static void createGameObjects(){
		Game.camera = new GameCamera();
		Game.map = new GameMap();
	}

}
