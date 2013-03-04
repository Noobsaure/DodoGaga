package com.me.mygdxgame.ia.pathfinding.heuristics;

import com.me.mygdxgame.game.GameMover;
import com.me.mygdxgame.ia.pathfinding.AStarHeuristic;
import com.me.mygdxgame.ia.pathfinding.TileBasedMap;


/**
 * A heuristic that uses the tile that is closest to the target
 * as the next best tile.
 * 
 * @author Kevin Glass
 */
public class ClosestHeuristic implements AStarHeuristic {
	/**
	 * @see AStarHeuristic#getCost(TileBasedMap, Mover, int, int, int, int)
	 */
	public float getCost(TileBasedMap map, GameMover mover, int x, int y, int tx, int ty) {		
		float dx = tx - x;
		float dy = ty - y;
		
		float result = (float) (Math.sqrt((dx*dx)+(dy*dy)));
		
		return result;
	}

}
