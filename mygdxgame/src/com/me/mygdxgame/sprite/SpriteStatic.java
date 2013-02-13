package com.me.mygdxgame.sprite;

import com.me.mygdxgame.game.GameEntity;
import com.me.mygdxgame.utils.Cst;

public class SpriteStatic extends SpriteBase{
	
	protected int elevation;
	
	public SpriteStatic(String textureName){
		this(textureName, 0);
	}
	
	public SpriteStatic(String textureName, int elevation){
		this(textureName, elevation, 1, 1, 1, 1);
	}
	
	public SpriteStatic(String textureName, int elevation, float r, float g, float b, float a){
		super(textureName, r, g, b, a);
		this.elevation = elevation;
	}
	
	public void update(GameEntity entity){
		/*
		float x = Cst.TILE_HW + Cst.TILE_W * entity.getTilePosition().x + Cst.TILE_HW * (entity.getTilePosition().y % 2);
		float y = Cst.TILE_HH + Cst.TILE_HH * entity.getTilePosition().y;
		x += entity.getInnerTilePosition().x * Cst.CELL_HW - entity.getInnerTilePosition().y * Cst.CELL_HW;
		y += entity.getInnerTilePosition().x * Cst.CELL_HH + entity.getInnerTilePosition().y * Cst.CELL_HH;
		setPosition(x-Cst.CELL_HW, y-getHeight()-getElevation()-Cst.CELL_HH);*/
		
	}
	
	public int getElevation() {
		return elevation;
	}
	
	public void setElevation(int elevation) {
		this.elevation = elevation;
	}
	
}
