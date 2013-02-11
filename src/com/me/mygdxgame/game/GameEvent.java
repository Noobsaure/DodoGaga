package com.me.mygdxgame.game;

public class GameEvent extends GameCharacter{

	public GameEvent(){
		
	}
	
	public void update(){
		Game.map.refreshEventPosition((int)tilePosition.x, (int)tilePosition.y, this);
	}
	
}
