package com.me.mygdxgame.sprite;

import com.me.mygdxgame.game.GameEntity;
import com.me.mygdxgame.utils.Cst;

public class SpriteAnimated extends SpriteBase{
	
	private int elevation = 0;
	
	public SpriteAnimated(String textureName){
		super(textureName);
	}
	
	public void update(GameEntity entity){
		float x = entity.getTilePosition().x * Cst.TILE_HW - entity.getTilePosition().y * Cst.TILE_HW;
		float y = entity.getTilePosition().x * Cst.TILE_HH + entity.getTilePosition().y * Cst.TILE_HH;
		x += entity.getInnerTilePosition().x * Cst.CELL_HW - entity.getInnerTilePosition().y * Cst.CELL_HW;
		y += entity.getInnerTilePosition().x * Cst.CELL_HH + entity.getInnerTilePosition().y * Cst.CELL_HH;
		setPosition(x+Cst.CELL_HW, y-getHeight()+Cst.CELL_HH-getElevation());
	}
}
