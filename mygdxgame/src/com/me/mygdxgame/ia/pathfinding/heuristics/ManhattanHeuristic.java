package com.me.mygdxgame.ia.pathfinding.heuristics;

import com.me.mygdxgame.game.GameMover;
import com.me.mygdxgame.ia.pathfinding.AStarHeuristic;
import com.me.mygdxgame.ia.pathfinding.TileBasedMap;

public class ManhattanHeuristic implements AStarHeuristic {

	private int minimumCost;
	
	public ManhattanHeuristic(int minimumCost) {
		this.minimumCost = minimumCost;
	}

	public float getCost(TileBasedMap map, GameMover mover, int x, int y, int tx, int ty) {
		return minimumCost * (Math.abs(x-tx) + Math.abs(y-ty));
	}
}
