package com.me.mygdxgame.game;

import com.me.mygdxgame.utils.Cst;
import com.me.mygdxgame.utils.Point2f;
import com.me.mygdxgame.utils.Point2i;
import com.me.mygdxgame.utils.interval.IntervalTransformable;

public abstract class GameMovable extends GameEvent implements IntervalTransformable {

	private Point2f realPosition;
	private boolean hasChanged = false;
	
	public GameMovable(int id) {
		super(id);
		realPosition = new Point2f(0,0);
	}
	
	public GameMovable(int id, Point2i tilePosition, Point2i innerTilePosition) {
		super(id,tilePosition,innerTilePosition);
	}

	public Point2f getRealPosition() {return realPosition;}
	public void setRealPosition(Point2f realPosition) {this.realPosition = realPosition;this.hasChanged = true;}
	
	@Override
	public void update() {
		if(hasChanged) {
			hasChanged = false;
			Point2i pos = GameMap.isoToTile(realPosition.x, realPosition.y);
			float x = realPosition.x - (Cst.TILE_W * pos.x + (pos.y % 2) * Cst.TILE_HW);
			float y = realPosition.y - Cst.TILE_HH * pos.y;
			
			this.innerTilePosition = new Point2i((int)(0.5 * (y/Cst.CELL_HH + x/Cst.CELL_HW)),
					(int)(0.5 * (y/Cst.CELL_HH - x/Cst.CELL_HW)));
			
			if(!pos.equals(tilePosition)) {
				Game.map.removeEventFromTile(tilePosition, this);
				Game.map.addEventToTile(pos, this);
				this.tilePosition = pos;
			}
		}
	}
}
