package com.me.mygdxgame.game;

import com.me.mygdxgame.utils.Point2i;

public class GameEvent extends GameEntity{

	public GameEvent(int textureId){
		super(textureId);
	}
	
	public GameEvent(int textureId, Point2i tilePosition) {
		super(textureId,tilePosition);
	}
	
	public void update(){
		//Game.map.refreshEventPosition((int)tilePosition.x, (int)tilePosition.y, this);
	}
	
}