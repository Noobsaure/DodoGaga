package com.me.mygdxgame.mgr;

import java.util.ArrayList;
import java.util.Random;

import com.me.mygdxgame.data.Data;
import com.me.mygdxgame.data.DataMap;
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
	    loadDatabase();
	    createGameObjects();
	}
	
	public static void loadDatabase(){
		
		//Load maps database
		Data.maps = new ArrayList<DataMap>();
		
			//Map 0
			DataMap data = new DataMap(0, "Map000", Cst.MAP_SIZE);
			Random rand = new Random();
			int height;
			int tmplol = 0;
			for(int i=data.tileSize.x-1; i >= 0; i--){
				for(int j=data.tileSize.y-1; j >= 0; j--){
					data.tilemap[i][j] = Cst.FLOOR;
					height = rand.nextInt(3+tmplol);
					data.heightmap[i][j] = (byte)height;
					if(height > data.maximumHeight) {
						data.maximumHeight = height;
					}
					tmplol = Math.max(tmplol, rand.nextInt(tmplol+2));
				}
			}
			Data.maps.add(data);
		
		//Load sprites database
		Data.sprites = new ArrayList<DataSprite>();
		DataSprite dataSprite;
			
			//sprite 0  (floor)
			dataSprite = new DataSprite(0, "Floor", "greenTile.png");
			Data.sprites.add(dataSprite);
			
			//sprite 1 (wall)
			dataSprite = new DataSprite(1, "Wall", "wall.png");
			Data.sprites.add(dataSprite);
			
			//sprite 2 (tree)
			dataSprite = new DataSprite(2, "Tree", "tree.png");
			Data.sprites.add(dataSprite);
			
			//sprite 3 (character big)
			dataSprite = new DataSprite(3, "characterBig", "characterBig.png");
			Data.sprites.add(dataSprite);
			
			//sprite 1 (wall)
			dataSprite = new DataSprite(4, "WallLeft", "wallLeft.png");
			Data.sprites.add(dataSprite);
			
			//sprite 1 (wall)
			dataSprite = new DataSprite(5, "WallLeft2", "wallLeft2.png");
			Data.sprites.add(dataSprite);
			
			//sprite 1 (wall)
			dataSprite = new DataSprite(6, "WallRight", "wallRight.png");
			Data.sprites.add(dataSprite);
			
			//sprite 1 (wall)
			dataSprite = new DataSprite(7, "WallRight2", "wallRight2.png");
			Data.sprites.add(dataSprite);
			
		//Load tiles database
		Data.tiles = new ArrayList<DataTile>();
		DataTile dataTile;
		
			//Tile 0
			
			dataTile = new DataTile(0, "Floor", 0, false);
			Data.tiles.add(dataTile);
			//SpriteMgr.addTile(new SpriteTile("floor.png"));
			
			//Tile 1
			dataTile = new DataTile(1, "Wall", 1, true);
			Data.tiles.add(dataTile);
			//SpriteMgr.addTile(new SpriteTile("wall.png",Cst.TILE_WALL_H));
		
		//SpriteMgr.add(new SpriteStatic("tree.png"));
		
		//SpriteMgr.add(new SpriteStatic("characterBig.png"));
	}
	
	public static void createGameObjects(){
		Game.camera = new GameCamera();
		Game.map = new GameMap();
	}
	
}
