package com.me.mygdxgame.game;

import com.me.mygdxgame.utils.Point2i;

public class GameEntity{
	
	protected Point2i tilePosition;
	protected Point2i innerTilePosition;
	private int spriteId;
	
	public GameEntity(int spriteId){
		this(spriteId, new Point2i(0, 0), new Point2i(0, 0));
	}
	
	public GameEntity(int spriteId, Point2i tilePosition, Point2i innerTilePosition){
		this.tilePosition = tilePosition;
		this.innerTilePosition = innerTilePosition;
		this.spriteId = spriteId;
	}
	
	public Point2i getTilePosition(){
		return this.tilePosition;
	}
	
	public Point2i getInnerTilePosition(){
		return this.innerTilePosition;
	}
	
	public void setPosition(int x, int y, int xi, int yi){
		this.tilePosition.x = x;
		this.tilePosition.y = y;
		this.innerTilePosition.x = xi;
		this.innerTilePosition.y = yi;
	}
	
	public void setPosition(Point2i tilePosition, Point2i innerTilePosition){
		this.tilePosition = tilePosition;
		this.innerTilePosition = innerTilePosition;
	}
	
	public int getSpriteId() {return this.spriteId;}
	
}
