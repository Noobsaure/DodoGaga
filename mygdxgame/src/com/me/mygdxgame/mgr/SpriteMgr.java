package com.me.mygdxgame.mgr;

import java.util.Hashtable;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.me.mygdxgame.sprite.SpriteAnimated;
import com.me.mygdxgame.sprite.SpriteBase;
import com.me.mygdxgame.sprite.SpriteStatic;
import com.me.mygdxgame.sprite.SpriteTile;
import com.me.mygdxgame.data.Data;
import com.me.mygdxgame.data.DataSprite;
import com.me.mygdxgame.data.DataTile;
import com.me.mygdxgame.utils.Cst;

public class SpriteMgr {

	private static SpriteTile[] tiles = new SpriteTile[127];
	private static SpriteStatic[] statics = new SpriteStatic[Data.sprites.size()];
	private static SpriteAnimated[] animateds = new SpriteAnimated[Data.sprites.size()];
	
	private static void addTile(int id){
		if(tiles[id] == null){
			DataTile dataTile = Data.tiles.get(id);
			DataSprite dataSprite = Data.sprites.get(dataTile.spriteId);
			SpriteTile sprite = new SpriteTile(dataSprite.textureFilename);
			if(dataTile.isWall){
				sprite.setElevation(Cst.TILE_WALL_H);
			}
			tiles[id] = sprite;
		}
	}
	
	public static void addStatic(int id){
		if(statics[id] == null){
			DataSprite dataSprite = Data.sprites.get(id);
			SpriteStatic sprite = new SpriteStatic(dataSprite.textureFilename);
			statics[id] = sprite;
		}
	}
	
	public static void addAnimated(int id){
		if(animateds[id] == null){
			DataSprite dataSprite = Data.sprites.get(id);
			SpriteAnimated sprite = new SpriteAnimated(dataSprite.textureFilename);
			animateds[id] = sprite;
		}
	}
	
	public static SpriteTile getTile(int id){
		addTile(id);
		return new SpriteTile(tiles[id].getTextureName(),tiles[id].getElevation(),tiles[id].getColor());
	}
	
	public static SpriteStatic getStatic(int id){
		addStatic(id);
		return new SpriteStatic(statics[id].getTextureName(),statics[id].getElevation(),statics[id].getColor());
		
	}
	
	public static SpriteAnimated getAnimated(int id){
		addAnimated(id);
		SpriteAnimated sprite = new SpriteAnimated(animateds[id].getTextureName(),animateds[id].getElevation(),animateds[id].getColor());
		sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight());
		return sprite;
	}
}
