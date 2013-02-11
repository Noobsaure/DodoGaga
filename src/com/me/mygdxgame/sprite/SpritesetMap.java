package com.me.mygdxgame.sprite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.me.mygdxgame.data.Data;
import com.me.mygdxgame.data.DataTile;
import com.me.mygdxgame.game.Game;
import com.me.mygdxgame.game.GameMap;

public class SpritesetMap {
	
	SpriteBatch batch;
	
	private byte[][] tilemap;
	
	public SpritesetMap(){
		createSpriteBatches();
		createTilemap();
		createFixedSprites();
	}
	
	private void createSpriteBatches(){
		batch = new SpriteBatch();
	}
	
	private void createTilemap(){
		tilemap = new byte[Game.map.tilemapSize[0]][Game.map.tilemapSize[1]];
		
		Random rand = new Random();
		
		for(int i=0; i<Game.map.tilemapSize[0]; i++){
			for(int j=0; j<Game.map.tilemapSize[1]; j++){
				int n = rand.nextInt(255);
				if(n < 200){
					tilemap[i][j] = 0; //Floor
				}
				else{
					tilemap[i][j] = 1; //Wall
				}
			}
		}
	}
	
	private void createFixedSprites(){
	}
	
	public void update(){
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		batch.setProjectionMatrix(Game.cam.combined);
		batch.begin();
		
		Vector3 inter = new Vector3();
		
		Ray pickRay = Game.cam.getPickRay(0, 0);
		Intersector.intersectRayPlane(pickRay, GameMap.xyPlane, inter);
		int iMin = (int) Math.floor(((inter.y + inter.x/2)/Game.map.TILE_SIZE[1]));
		
		pickRay = Game.cam.getPickRay(Gdx.graphics.getWidth(), 0);
		Intersector.intersectRayPlane(pickRay, GameMap.xyPlane, inter);
		int jMin = (int) Math.floor(((inter.y - inter.x/2)/Game.map.TILE_SIZE[1]));
		
		pickRay = Game.cam.getPickRay(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Intersector.intersectRayPlane(pickRay, GameMap.xyPlane, inter);
		int iMax = (int) Math.ceil(((inter.y + inter.x/2)/Game.map.TILE_SIZE[1]));
		
		pickRay = Game.cam.getPickRay(0, Gdx.graphics.getHeight());
		Intersector.intersectRayPlane(pickRay, GameMap.xyPlane, inter);
		int jMax = (int) Math.ceil(((inter.y - inter.x/2)/Game.map.TILE_SIZE[1]));

		/*
		iMin -= 1;
		jMin -= 1;
		iMax += 2;
		jMax += 2;*/
		
		iMin = Math.max(iMin, 0);
		jMin = Math.max(jMin, 0);
		iMax = Math.min(iMax, Game.map.tilemapSize[0]);
		jMax = Math.min(jMax, Game.map.tilemapSize[1]);
		
		//System.out.println(iMin + " " + iMax + "        " + jMin + " " + jMax);
		//Sprite sprite = null;
		Vector2 pos = new Vector2();
		
		
	    List<Sprite> list = new ArrayList<Sprite>();
	    
	    int spriteNumber = 0;
	    
		for(int i = iMin; i < iMax; i++){
			for (int j = jMin; j < jMax; j++){
				pos.set(i*Game.map.TILE_SIZE[0], j*Game.map.TILE_SIZE[1]);
				Game.map.convertToScreen(pos);
				
				byte tileId = tilemap[i][j];
				
				DataTile dataTile = Data.tiles.get(tileId);
				SpriteBase sprite = new SpriteBase();
				sprite.setTexture(dataTile.textureName);
				
				
				sprite.setPosition(pos.x, pos.y + Game.map.TILE_SIZE[1]);
				
				if(dataTile.origin[1] != -1){
					sprite.setOrigin(sprite.getWidth()/2, dataTile.origin[1]);
				}
				else{
					sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight());
				}
				
				if(dataTile.isWall){
					sprite.setY(sprite.getY() - Game.map.TILE_WALL_HEIGHT);
					list.add(sprite);
				}
				else{
					sprite.draw(batch);
				}
				
				
				spriteNumber += 1;
			}
		}
		
		System.out.println("Tile renderered count: " + spriteNumber);
		
	    Collections.sort(list, new Comparator<Sprite>() {
	    	        @Override public int compare(Sprite s1, Sprite s2) {
	    	            return (int) (s1.getY() - s2.getY());
	    	        }

	    	    });
	    
	    for(Sprite spr : list){
	    	spr.draw(batch);
	    }


		batch.end();
	}
	
}
