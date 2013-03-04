package com.me.mygdxgame.game;

import aurelienribon.tweenengine.TweenAccessor;

import com.me.mygdxgame.utils.Point2f;
import com.me.mygdxgame.utils.Point2i;
import com.me.mygdxgame.utils.interval.base.TimeBasedInterval;
import com.me.mygdxgame.utils.interval.interfaces.IntervalTransformable;
import com.me.mygdxgame.utils.interval.transform.IntervalTransformValue;

public abstract class GameMover extends GameEvent implements IntervalTransformable {

	private Point2f realPosition;
	private float rotation;
	private boolean hasChanged = false;
	
	public GameMover(int id, Point2i tilePosition) {
		super(id,tilePosition);
		realPosition = Game.map.heightTileToIsof(tilePosition.x, tilePosition.y);
		Game.map.addEventToTile(tilePosition, this);
		rotation = 0;
		hasChanged = true;
	}

	public void setTransform(int type, IntervalTransformValue value){
		switch(type){
		case TimeBasedInterval.TYPE.POS: 
			setPosition(value.x, value.y);
			break;
		}
	}
	
	public IntervalTransformValue getTransform(int type){
		switch(type){
		case TimeBasedInterval.TYPE.POS: 
			return new IntervalTransformValue(realPosition.x, realPosition.y);
		}
		return null;
	}

	public Point2f getPosition() {return realPosition;}
	public float getRotation() {return rotation;}
	
	public void setPosition(Point2f realPosition){
		setPosition(realPosition.x, realPosition.y);
	}

	public void setPositionX(float x) {realPosition.x = x; hasChanged = true;}
	public void setPositionY(float y) {realPosition.y = y; hasChanged = true;}
	public void setRotation(float rotation) {this.rotation = rotation; hasChanged = true;}
	
	public void setPosition(float x, float y){
		realPosition.x = x;
		realPosition.y = y;
		hasChanged = true;
	}
	
	@Override
	public void update() {
		if(hasChanged) {
			hasChanged = false;
			Point2i pos = Game.map.heightIsoToTile(realPosition.x, realPosition.y, false);
			if(!pos.equals(tilePosition)) {
				Game.map.removeEventFromTile(tilePosition, this);
				Game.map.addEventToTile(pos, this);
				tilePosition = pos;
			}
		}
	}
}
