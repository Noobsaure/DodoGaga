package com.me.mygdxgame.game;

import com.me.mygdxgame.utils.Cst;
import com.me.mygdxgame.utils.Point2f;
import com.me.mygdxgame.utils.Point2i;
import com.me.mygdxgame.utils.interval.Interval;
import com.me.mygdxgame.utils.interval.IntervalTransformable;

public abstract class GameMover extends GameEvent implements IntervalTransformable {

	private Point2f realPosition;
	private boolean hasChanged = false;
	
	//public GameMovable(int id) {
	//	this(id, new Point2i(0,0), new Point2i(0,0));
		//super(id);
		//realPosition = new Point2f(0,0);
	//}
	
	public GameMover(int id, Point2f realPosition) {
		super(id);
		this.realPosition = realPosition;
		setRealPosition(realPosition);
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
	
	public void startIntervalToTile(Point2i tilePos){
		Point2f destination = new Point2f();
		
		//float x = Cst.TILE_HW + Cst.TILE_W * tilePos.x + Cst.TILE_HW * (tilePos.y % 2);
		//float y = Cst.TILE_HH + Cst.TILE_HH * tilePos.y;
		//x += getInnerTilePosition().x * Cst.CELL_HW - getInnerTilePosition().y * Cst.CELL_HW;
		//y += getInnerTilePosition().x * Cst.CELL_HH + getInnerTilePosition().y * Cst.CELL_HH;
		
		destination.x = 0f;//x-Cst.TILE_HW;
		destination.y = 0f;//y+Cst.TILE_HH;
		
		System.out.println(destination.x + " " + destination.y);
		Interval interval = new Interval(this, 0.5f, realPosition, destination, "linear");
		interval.start();
	}
}
