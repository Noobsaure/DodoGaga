package com.me.mygdxgame.game;

import com.me.mygdxgame.utils.Point2i;

public class GameBattler extends GameMover{
	
	private int movementPoints = 10;
	
	public GameBattler(int id, Point2i tilePosition) {
		super(id,tilePosition);
	}
	
	public boolean isTileReachable(Point2i tile) {
		return tile.x >= 0 && tile.y >= 0 && tile.x < Game.map.getMapSize().x && tile.y < Game.map.getMapSize().y
				&& getTilePosition().x >= 0 && getTilePosition().y >= 0
				&& getTilePosition().x < Game.map.getMapSize().x && getTilePosition().y < Game.map.getMapSize().y
				&& Game.map.getCost(this,getTilePosition(),tile) <= movementPoints;
	}
	
	public int getMovementPoints() {return movementPoints;}
	public void setMovementPoints(int movementPoints) {this.movementPoints = movementPoints;}
	
	public void update() {
		super.update();
	}
}
