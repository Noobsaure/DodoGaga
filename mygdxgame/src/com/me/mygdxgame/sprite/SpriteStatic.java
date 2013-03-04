package com.me.mygdxgame.sprite;

import com.badlogic.gdx.graphics.Color;
import com.me.mygdxgame.game.GameEntity;
import com.me.mygdxgame.utils.Cst;

public class SpriteStatic extends SpriteBase{
  	
	protected int elevation;
	
	public SpriteStatic(String textureName){
		this(textureName, 0);
		super.setOrigin(getWidth()/2, getHeight());
	}
	
	public SpriteStatic(String textureName, int elevation){
		this(textureName, elevation, new Color(1,1,1,1));
	}
	
	public SpriteStatic(String textureName, int elevation, Color color){
		super(textureName, color);
		this.elevation = elevation;
	}
	
	public void update(GameEntity entity){
		float resultX = 0;
		float resultY = 0;
		
		//Je me place en haut au ileu de la tile visuelle
		int i = entity.getTilePosition().x;
		int j = entity.getTilePosition().y;
		float x = i*Cst.TILE_W + Cst.TILE_HW * (j % 2) + Cst.TILE_HW;
		float y = j*Cst.TILE_HH;
		
		//j'utilise la methode convertToIsodu rendu en diamond pour trouver la bonne cell pos
		float x2 = (float)entity.getInnerTilePosition().x;
		float y2 = (float)entity.getInnerTilePosition().y;
		resultX = - (y2 * Cst.CELL_HW) + (x2 * Cst.CELL_HW);
		resultY = (y2 * Cst.CELL_HH) + (x2 * Cst.CELL_HH) + Cst.CELL_HH;
		
		setPosition(x+resultX, y+resultY);
		
		//Ancienne methode
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
