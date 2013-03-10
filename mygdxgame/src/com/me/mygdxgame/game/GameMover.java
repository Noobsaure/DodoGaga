package com.me.mygdxgame.game;

import com.badlogic.gdx.math.Vector3;
import com.me.mygdxgame.utils.Cst;
import com.me.mygdxgame.utils.Point2f;
import com.me.mygdxgame.utils.Point2i;

public abstract class GameMover extends GameEvent {

	private Vector3 positionOffsets;
	private Vector3 accumulatedOffsets;
	private float rotation;
	private boolean hasChanged = false;

	public GameMover(int id, Point2i tilePosition) {
		super(id,tilePosition);
		positionOffsets = new Vector3(0,0,0);
		accumulatedOffsets = new Vector3(0,0,0);
		Game.map.addEventToTile(tilePosition, this);
		rotation = 0;
		hasChanged = true;
	}

	public float getRotation() {return rotation;}



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

	public float getAccumulatedOffsetZ() {
		return accumulatedOffsets.z;
	}

	public Vector3 getAccumulatedOffsets() {
		return accumulatedOffsets;
	}

	public void setAccumulatedOffsets(float x, float y, float z) {
		accumulatedOffsets.x = x;
		accumulatedOffsets.y = y;
		accumulatedOffsets.z = z;
		hasChanged = true;
	}

	public void addOffsets(float x, float y) {
		positionOffsets.x = positionOffsets.x + x;
		positionOffsets.y = positionOffsets.y + y;

		accumulatedOffsets.x = accumulatedOffsets.x + x;
		accumulatedOffsets.y = accumulatedOffsets.y + y;

		if(positionOffsets.x > Cst.TILE_QW) {
			if(positionOffsets.y > Cst.TILE_QH) {
				tilePosition.x = tilePosition.x + 1;
				positionOffsets.x = positionOffsets.x - Cst.TILE_HW;
				positionOffsets.y = positionOffsets.y - Cst.TILE_HH;
				if(positionOffsets.z != 0)
					positionOffsets.z = positionOffsets.z
					- Game.map.getHeight(tilePosition.x - 1, tilePosition.y)
					+ Game.map.getHeight(tilePosition.x, tilePosition.y);
				
			} else if(positionOffsets.y < -Cst.TILE_QH) {
				tilePosition.y = tilePosition.y - 1;
				positionOffsets.x = positionOffsets.x - Cst.TILE_HW;
				positionOffsets.y = positionOffsets.y + Cst.TILE_HH;
				if(positionOffsets.z != 0)
					positionOffsets.z = positionOffsets.z
					- Game.map.getHeight(tilePosition.x, tilePosition.y + 1)
					+ Game.map.getHeight(tilePosition.x, tilePosition.y);
			}
			
		} else if (positionOffsets.x < -Cst.TILE_QW) {
			if(positionOffsets.y > Cst.TILE_QH) {
				tilePosition.y = tilePosition.y + 1;
				positionOffsets.x = positionOffsets.x + Cst.TILE_HW;
				positionOffsets.y = positionOffsets.y - Cst.TILE_HH;
				if(positionOffsets.z != 0)
					positionOffsets.z = positionOffsets.z
					- Game.map.getHeight(tilePosition.x, tilePosition.y - 1)
					+ Game.map.getHeight(tilePosition.x, tilePosition.y);
				
			} else if(positionOffsets.y < -Cst.TILE_QH) {
				tilePosition.x = tilePosition.x - 1;
				positionOffsets.x = positionOffsets.x + Cst.TILE_HW;
				positionOffsets.y = positionOffsets.y + Cst.TILE_HH;
				if(positionOffsets.z != 0)
					positionOffsets.z = positionOffsets.z
					- Game.map.getHeight(tilePosition.x + 1, tilePosition.y)
					+ Game.map.getHeight(tilePosition.x, tilePosition.y);
			}
		}
		hasChanged = true;
	}

	public void addOffsetZ(float z) {
		positionOffsets.z = Math.max(positionOffsets.z + z,0);
		accumulatedOffsets.z = accumulatedOffsets.z + z;
		hasChanged = true;
	}

	public void setRotation(float rotation) {this.rotation = rotation; hasChanged = true;}

	public int getZOrder() {
		int i = tilePosition.x;
		int j = tilePosition.y;
		return Game.map.mapData.getZOrder(i,j,Game.map.getHeight(i,j),true);
	}

	public void setOffsetsPosition(float x, float y, float z){
		positionOffsets.x = x;
		positionOffsets.y = y;
		positionOffsets.z = z;
		hasChanged = true;
	}

	public float getOffsetX() {return positionOffsets.x;}
	public float getOffsetY() {return positionOffsets.y;}
	public float getOffsetZ() {return positionOffsets.z;}

	@Override
	public void update() {
		if(hasChanged) {
			hasChanged = false;
			int i = tilePosition.x;
			int j = tilePosition.y;
			getDecal().setPosition(
					(i - j) * Cst.TILE_HW + positionOffsets.x,
					-((i + j - 2) * Cst.TILE_HH - (Game.map.getHeight(i,j)+positionOffsets.z) * Cst.TILE_WALL_H + positionOffsets.y),
					getZOrder() * 0.000001f
					);
		}
	}
}
