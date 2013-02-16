package com.me.mygdxgame.game;

import com.me.mygdxgame.utils.Cst;
import com.me.mygdxgame.utils.Point2f;
import com.me.mygdxgame.utils.Point2i;
import com.me.mygdxgame.utils.interval.Interval;
import com.me.mygdxgame.utils.interval.IntervalTransformValue;
import com.me.mygdxgame.utils.interval.interfaces.IntervalPlayable;
import com.me.mygdxgame.utils.interval.interfaces.IntervalTransformable;
import com.me.mygdxgame.utils.interval.transform.PosInterval;

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
		setPosition(realPosition);
	}

	public void setTransform(int type, IntervalTransformValue value){
		switch(type){
		case Interval.TransformType.POS: {
			setPosition(value.x, value.y);
			break;
		}
		}
	}
	
	public IntervalTransformValue getTransform(int type){
		switch(type){
		case Interval.TransformType.POS: {
			return new IntervalTransformValue(realPosition.x, realPosition.y);
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
	
	public void startIntervalToTile(Point2i tile, Point2i cell){
		Point2f destination = new Point2f();
		
		float resultX = 0;
		float resultY = 0;
		
		//Je me place en haut au ileu de la tile visuelle
		int i = tile.x;
		int j = tile.y;
		float x = i*Cst.TILE_W + Cst.TILE_HW * (j % 2) + Cst.TILE_HW;
		float y = j*Cst.TILE_HH;
		
		//j'utilise la methode convertToIsodu rendu en diamond pour trouver la bonne cell pos
		float x2 = cell.x;
		float y2 = cell.y;
		resultX = - (y2 * Cst.CELL_HW) + (x2 * Cst.CELL_HW);
		resultY = (y2 * Cst.CELL_HH) + (x2 * Cst.CELL_HH) + Cst.CELL_HH;
		
		destination.x = x+resultX;
		destination.y = y+resultY;
		
		//System.out.println("Deplacement en Tile: " + tile + "   Cell: " + cell);
		IntervalPlayable interval = new PosInterval(this, 0.5f, null, destination, "linear", "linear");
		interval.start();
	}
}
