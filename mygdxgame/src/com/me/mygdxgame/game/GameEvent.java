package com.me.mygdxgame.game;

import com.me.mygdxgame.utils.Point2i;

public class GameEvent extends GameEntity{

	public GameEvent(int id){
		super(id);
	}
	
	public GameEvent(int id, Point2i tilePosition, Point2i innerTilePosition) {
		super(id,tilePosition,innerTilePosition);
	}
	
	public void update(){
		//Game.map.refreshEventPosition((int)tilePosition.x, (int)tilePosition.y, this);
	}
	
}