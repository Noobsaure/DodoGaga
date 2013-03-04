package com.me.mygdxgame.game;

import com.me.mygdxgame.utils.Cst;
import com.me.mygdxgame.utils.Point2f;
import com.me.mygdxgame.utils.Point2i;
import com.me.mygdxgame.utils.interval.base.TimeBasedInterval;
import com.me.mygdxgame.utils.interval.interfaces.Interval;
import com.me.mygdxgame.utils.interval.interfaces.IntervalTransformable;
import com.me.mygdxgame.utils.interval.transform.IntervalTransformValue;
import com.me.mygdxgame.utils.interval.transform.PosInterval;

public abstract class GameMover extends GameEvent implements IntervalTransformable {

	private Point2f realPosition;
	private boolean hasChanged = false;
	
	public GameMover(int id, Point2i tilePosition) {
		super(id,tilePosition);
		this.realPosition = Game.map.heightTileToIsof(tilePosition.x, tilePosition.y);
		Game.map.addEventToTile(tilePosition, this);
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
	
	public void setPosition(Point2f realPosition){
		setPosition(realPosition.x, realPosition.y);
	}
	
	public void setPosition(float x, float y){
		this.realPosition.x = x;
		this.realPosition.y = y;
		this.hasChanged = true;
	}
	
	@Override
	public void update() {
		if(hasChanged) {
			hasChanged = false;
			Point2i pos = Game.map.heightIsoToTile(realPosition.x, realPosition.y);		
			if(!pos.equals(tilePosition)) {
				Game.map.removeEventFromTile(tilePosition, this);
				Game.map.addEventToTile(pos, this);
				this.tilePosition = pos;
			}
		}
	}
	
	/*public void startIntervalToTile(Point2i tile){
		Point2f destination = GameMap.tileToIsof(tile.x,tile.y);
		Interval interval = new PosInterval(this, 0.5f, null, destination, "linear");
		interval.start();
	}*/
}
