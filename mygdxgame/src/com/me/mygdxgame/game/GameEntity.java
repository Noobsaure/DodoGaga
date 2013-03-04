package com.me.mygdxgame.game;

import com.me.mygdxgame.utils.Point2i;

public class GameEntity{
	
	protected Point2i tilePosition;
	private int spriteId;
	
	public GameEntity(int spriteId){
		this(spriteId, new Point2i(0, 0));
	}
	
	public GameEntity(int spriteId, Point2i tilePosition){
		this.tilePosition = tilePosition;
		this.spriteId = spriteId;
	}
	
	public Point2i getTilePosition(){
		return this.tilePosition;
	}
	
	public void setPosition(int x, int y){
		this.tilePosition.x = x;
		this.tilePosition.y = y;
	}
	
	public void setPosition(Point2i tilePosition, Point2i innerTilePosition){
		this.tilePosition = tilePosition;
	}
	
	public int getSpriteId() {return this.spriteId;}
	
}
