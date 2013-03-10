package com.me.mygdxgame.game;

import com.me.mygdxgame.utils.Cst;
import com.me.mygdxgame.utils.Point2f;
import com.me.mygdxgame.utils.Point2i;

public abstract class GameMover extends GameEvent {

	private Point2f positionOffsets;
	private Point2f accumulatedOffsets;
	private float height;
	private float rotation;
	private boolean hasChanged = false;

	public GameMover(int id, Point2i tilePosition) {
		super(id,tilePosition);
		positionOffsets = new Point2f(0,0);
		accumulatedOffsets = new Point2f(0,0);
		Game.map.addEventToTile(tilePosition, this);
		rotation = 0;
		hasChanged = true;
	}

	public Point2f getPosition() {return positionOffsets;}
	public float getRotation() {return rotation;}

	public void setPosition(Point2f realPosition){
		setPosition(realPosition.x, realPosition.y);
	}

	@Override
	public void setPosition(Point2i p){
		this.tilePosition.x = p.x;
		this.tilePosition.y = p.y;
		this.hasChanged = true;
	}

	public float getAccumulatedOffsetX() {
		return accumulatedOffsets.x;
	}
	
	public float getAccumulatedOffsetY() {
		return accumulatedOffsets.y;
	}
	
	public Point2f getAccumulatedOffsets() {
		return accumulatedOffsets;
	}
	
	public void setAccumulatedOffsets(float x, float y) {
		accumulatedOffsets.x = x;
		accumulatedOffsets.y = y;
	}
	
	public void addOffsets(float x, float y) {
		positionOffsets.x = positionOffsets.x + x;
		positionOffsets.y = positionOffsets.y + y;
		
		accumulatedOffsets.x = accumulatedOffsets.x + x;
		accumulatedOffsets.y = accumulatedOffsets.y + y;
		
		if(positionOffsets.x > Cst.TILE_QW && positionOffsets.y > Cst.TILE_QH) {
			tilePosition.x = tilePosition.x + 1;
			positionOffsets.x = positionOffsets.x - Cst.TILE_HW;
			positionOffsets.y = positionOffsets.y - Cst.TILE_HH;
			
		} else if(positionOffsets.x < -Cst.TILE_QW && positionOffsets.y > Cst.TILE_QH) {
			tilePosition.y = tilePosition.y + 1;
			positionOffsets.x = positionOffsets.x + Cst.TILE_HW;
			positionOffsets.y = positionOffsets.y - Cst.TILE_HH;
			
		} else if(positionOffsets.x > Cst.TILE_QW && positionOffsets.y < -Cst.TILE_QH) {
			tilePosition.y = tilePosition.y - 1;
			positionOffsets.x = positionOffsets.x - Cst.TILE_HW;
			positionOffsets.y = positionOffsets.y + Cst.TILE_HH;
			
		} else if(positionOffsets.x < -Cst.TILE_QW && positionOffsets.y < -Cst.TILE_QH) {
			tilePosition.x = tilePosition.x - 1;
			positionOffsets.x = positionOffsets.x + Cst.TILE_HW;
			positionOffsets.y = positionOffsets.y + Cst.TILE_HH;
		}
		hasChanged = true;
	}
	
	public void addOffsetY(float y) {positionOffsets.y += y;hasChanged = true;}

	public void setRotation(float rotation) {this.rotation = rotation; hasChanged = true;}

	public int getZOrder() {
		int i = tilePosition.x;
		int j = tilePosition.y;
		return Game.map.mapData.getZOrder(i,j,Game.map.getHeight(i,j),true);
	}

	public void setPosition(float x, float y){positionOffsets.x = x;positionOffsets.y = y;hasChanged = true;}
	public float getOffsetX() {return positionOffsets.x;}
	public float getOffsetY() {return positionOffsets.y;}

	@Override
	public void update() {
		if(hasChanged) {
			hasChanged = false;
			int i = tilePosition.x;
			int j = tilePosition.y;
			getDecal().setPosition(
					(i - j) * Cst.TILE_HW + positionOffsets.x,
					-((i + j - 2) * Cst.TILE_HH - Game.map.getHeight(i,j) * Cst.TILE_WALL_H + positionOffsets.y),
					Game.map.mapData.getZOrder(i,j,Game.map.getHeight(i,j),true) * 0.000001f
					);
		}
	}
}
