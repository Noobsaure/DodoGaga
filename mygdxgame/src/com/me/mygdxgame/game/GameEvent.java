package com.me.mygdxgame.game;

import com.me.mygdxgame.utils.Point2i;

public class GameEvent extends GameEntity{

	public GameEvent(int spriteId){
		super(spriteId);
	}
	
	public GameEvent(int spriteId, Point2i tilePosition, Point2i innerTilePosition) {
		super(spriteId,tilePosition,innerTilePosition);
	}
	
	public void update(){
		//Game.map.refreshEventPosition((int)tilePosition.x, (int)tilePosition.y, this);
	}
	
}