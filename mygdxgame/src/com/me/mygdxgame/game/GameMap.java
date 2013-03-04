package com.me.mygdxgame.game;

import com.me.mygdxgame.data.Data;
import com.me.mygdxgame.data.DataMap;
import com.me.mygdxgame.ia.pathfinding.TileBasedMap;
import com.me.mygdxgame.utils.Point2i;

public class GameMap extends GameMapBase implements TileBasedMap {

	private boolean[][] visited;
	
	public GameMap(){
		super();
	}
	
	public void clearVisited(){
		for (int x=0; x < getMapSize().x; x++){
			for (int y=0; y < getMapSize().y; y++){
				visited[x][y] = false;
			}
		}
	}
	
	@Override
	public void update(){
		super.update();
	}
	
	public boolean visited(int x, int y) {
		return visited[x][y];
	}

	public void pathFinderVisited(int x, int y) {
		if(visited == null)
			visited = new boolean[getMapSize().x][getMapSize().y];
		visited[x][y] = true;
	}

	public boolean blocked(GameMover mover, int x, int y) {
		return getDataMap().isWall(x, y);
	}

	public float getCost(GameMover mover, int sx, int sy, int tx, int ty) {
		//return Math.max(Math.abs(sy-ty),2*Math.abs(sx-tx)-Math.abs((sy % 2) - (ty % 2)));
		return Math.abs(sx-tx) + Math.abs(sy-ty);
	}
	
	public float getCost(GameMover mover, Point2i start, Point2i end) {
		return getCost(mover, start.x, start.y, end.x, end.y);
	}
	
	public DataMap getDataMap() {
		return Data.maps.get(mapId);
	}
}
