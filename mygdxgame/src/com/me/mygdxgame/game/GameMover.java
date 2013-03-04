package com.me.mygdxgame.game;

import com.me.mygdxgame.utils.Cst;
import com.me.mygdxgame.utils.Point2f;
import com.me.mygdxgame.utils.Point2i;
import com.me.mygdxgame.utils.interval.Interval;
import com.me.mygdxgame.utils.interval.interfaces.IntervalPlayable;
import com.me.mygdxgame.utils.interval.interfaces.IntervalTransformable;
import com.me.mygdxgame.utils.interval.transform.PosInterval;

public abstract class GameMover extends GameEvent implements IntervalTransformable {

	private Point2f realPosition;
	private boolean hasChanged = false;

	public GameMover(int id, Point2i tilePosition) {
		super(id,tilePosition);
		this.realPosition = GameMap.tileToIsof(tilePosition.x, tilePosition.y);
		Game.map.addEventToTile(tilePosition, this);
		hasChanged = true;
	}

	public void setTransform(int type, float x, float y){
		switch(type){
		case Interval.TransformType.POS: {
			setPosition(x, y);
			break;
		}
		case Interval.TransformType.ROT: {
			//setPosition(x, y);
			break;
		}
		case Interval.TransformType.SCALE: {
			//setPosition(x, y);
			break;
		}
		case Interval.TransformType.MOVE: {
			setPosition(x, y);
			break;
		}
		
		}
	}

	public Point2f getTransform(int type){
		switch(type){
		case Interval.TransformType.POS: {
			return realPosition;
		}
		case Interval.TransformType.ROT: {
			return null;
		}
		case Interval.TransformType.SCALE: {
			return null;
		}
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
			Point2i pos = GameMap.isoToTile(realPosition.x, realPosition.y);
			if(!pos.equals(tilePosition)) {
				Game.map.removeEventFromTile(tilePosition, this);
				Game.map.addEventToTile(pos, this);
				this.tilePosition = pos;
				System.out.println("Acknowledged");
			}
		}
	}

	/*public void startIntervalToTile(Point2i tile){
		Point2f destination = new Point2f();

		int i = tile.x;
		int j = tile.y;
		float x = i*Cst.TILE_W + Cst.TILE_HW * (j % 2) + Cst.TILE_HW;
		float y = (j + 1)*Cst.TILE_HH;

		destination.x = x;
		destination.y = y;

		IntervalPlayable interval = new PosInterval(this, 0.5f, destination, "linear", "linear");
		interval.start();
	}*/
}
