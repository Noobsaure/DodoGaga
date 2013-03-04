package com.me.mygdxgame.ia.pathfinding.heuristics;

import com.me.mygdxgame.game.GameMover;
import com.me.mygdxgame.ia.pathfinding.AStarHeuristic;
import com.me.mygdxgame.ia.pathfinding.Mover;
import com.me.mygdxgame.ia.pathfinding.TileBasedMap;


/**
 * A heuristic that uses the tile that is closest to the target
 * as the next best tile. In this case the sqrt is removed
 * and the distance squared is used instead
 * 
 * @author Kevin Glass
 */
public class ClosestSquaredHeuristic implements AStarHeuristic {

	/**
	 * @see AStarHeuristic#getCost(TileBasedMap, Mover, int, int, int, int)
	 */
	public float getCost(TileBasedMap map, GameMover mover, int x, int y, int tx, int ty) {		
		float dx = tx - x;
		float dy = ty - y;
		
		return ((dx*dx)+(dy*dy));
	}

}
